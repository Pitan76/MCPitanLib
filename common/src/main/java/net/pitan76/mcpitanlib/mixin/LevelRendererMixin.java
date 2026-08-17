package net.pitan76.mcpitanlib.mixin;

import com.mojang.blaze3d.buffers.GpuBufferSlice;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.client.renderer.SubmitNodeCollector;
import com.mojang.blaze3d.resource.GraphicsResourceAllocator;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.state.level.BlockOutlineRenderState;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.client.renderer.state.level.LevelRenderState;
import net.pitan76.mcpitanlib.api.client.event.WorldRenderRegistry;
import net.pitan76.mcpitanlib.api.client.event.listener.*;
import org.joml.Matrix4f;
import org.joml.Matrix4fc;
import org.joml.Vector4f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LevelRenderer.class)
public abstract class LevelRendererMixin {
    @Shadow public abstract boolean hasRenderedAllSections();

    @Unique
    private final WorldRenderContextImpl mcpitanlib$contextCache = new WorldRenderContextImpl();

    @Inject(method = "submitBlockOutline", at = @At("HEAD"), cancellable = true)
    private void mcpitanlib$onSubmitBlockOutline(PoseStack poseStack, SubmitNodeCollector submitNodeCollector, LevelRenderState levelRenderState, CallbackInfo ci) {
        if (WorldRenderRegistry.isEmptyBlockOutlineListeners) return;

        BlockOutlineRenderState state = levelRenderState.blockOutlineRenderState; // Shadowで取得する想定
        if (state == null) return;

        RenderType renderType = RenderTypes.lines(); // 代表的なRenderTypeを仮で使う

        submitNodeCollector.submitCustomGeometry(poseStack, renderType, (pose, vertexConsumer) -> {
            mcpitanlib$contextCache.worldRenderer = (LevelRenderer)(Object) this;
            mcpitanlib$contextCache.advancedTranslucency = hasRenderedAllSections();
            mcpitanlib$contextCache.consumers = vertexConsumer;
            mcpitanlib$contextCache.matrixStack = poseStack;

            BeforeBlockOutlineEvent event = new BeforeBlockOutlineEvent(mcpitanlib$contextCache, Minecraft.getInstance().hitResult);

            try {
                for (BeforeBlockOutlineListener listener : WorldRenderRegistry.beforeBlockOutlineListeners) {
                    if (!listener.beforeBlockOutline(event)) {
                        ci.cancel();
                        return;
                    }
                }
            } finally {
                // vertexConsumerはこのコールバックのスコープ内でのみ有効。
                // 同フレーム後半のAfterLevelイベントに漏らさないよう必ず捨てる。
                mcpitanlib$contextCache.consumers = null;
            }
        });
    }

    @Inject(method = "render", at = @At("HEAD"))
    private void mcpitanlib$beforeRender(GraphicsResourceAllocator allocator, DeltaTracker tickCounter, boolean renderOutline, CameraRenderState cameraRenderState, Matrix4fc modelViewMatrix, GpuBufferSlice terrainFog, Vector4f fogColor, boolean shouldRenderSky, CallbackInfo ci) {
        Minecraft minecraft = Minecraft.getInstance();
        mcpitanlib$contextCache.prepare(minecraft.gameRenderer, (LevelRenderer) (Object) this, minecraft.level, tickCounter, renderOutline, minecraft.gameRenderer.mainCamera(), new Matrix4f(), new Matrix4f(), new Matrix4f());
    }

//    @ModifyExpressionValue(method = "renderLevel", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/LevelRenderer;cullFrustum(Lorg/joml/Matrix4f;Lorg/joml/Matrix4f;Lnet/minecraft/world/phys/Vec3;)Lnet/minecraft/client/renderer/culling/Frustum;"))
//    private Frustum onSetupFrustum(Frustum frustum) {
//        mcpitanlib$contextCache.frustum = frustum;
//        return frustum;
//    }

    // renderLevel -> submitBlockDestroyAnimation 26.1-
    @Inject(method = "submitBlockDestroyAnimation", at = @At("TAIL"))
    private void mcpitanlib$onWorldRenderEnd(PoseStack poseStack, SubmitNodeCollector submitNodeCollector, LevelRenderState levelRenderState, CallbackInfo ci) {
        if (WorldRenderRegistry.isEmptyWorldRenderAfterLevelListeners) return;

        submitNodeCollector.submitCustomGeometry(poseStack, RenderTypes.lines(), (pose, vertexConsumer) -> {
            PoseStack matrices = new PoseStack();
            matrices.mulPose(pose.pose());

            mcpitanlib$contextCache.worldRenderer = (LevelRenderer) (Object) this;
            mcpitanlib$contextCache.advancedTranslucency = hasRenderedAllSections();
            mcpitanlib$contextCache.matrixStack = matrices;
            mcpitanlib$contextCache.consumers = vertexConsumer;

            try {
                for (WorldRenderContextListener listener : WorldRenderRegistry.worldRenderAfterLevelListeners) {
                    listener.render(mcpitanlib$contextCache);
                }
            } finally {
                mcpitanlib$contextCache.consumers = null;
            }
        });
    }
}
