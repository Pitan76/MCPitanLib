package net.pitan76.mcpitanlib.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.mojang.blaze3d.buffers.GpuBufferSlice;
import net.minecraft.client.Camera;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.WorldBorderRenderer;
import net.minecraft.client.renderer.culling.Frustum;
import com.mojang.blaze3d.resource.GraphicsResourceAllocator;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.state.level.LevelRenderState;
import net.minecraft.client.renderer.state.level.WorldBorderRenderState;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.level.border.WorldBorder;
import net.pitan76.mcpitanlib.api.client.event.WorldRenderRegistry;
import net.pitan76.mcpitanlib.api.client.event.listener.*;
import org.jetbrains.annotations.Nullable;
import org.joml.Matrix4f;
import org.joml.Vector4f;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LevelRenderer.class)
public abstract class LevelRendererMixin {
    @Shadow private int ticks;

    @Shadow public abstract boolean hasRenderedAllSections();

    @Shadow @Nullable public abstract Frustum getCapturedFrustum();

    @Shadow @Final private Minecraft minecraft;
    @Shadow @Nullable private ClientLevel level;
    @Unique
    private final WorldRenderContextImpl mcpitanlib$contextCache = new WorldRenderContextImpl();

    @Inject(method = "renderBlockOutline", at = @At("HEAD"), cancellable = true)
    private void mcpitanlib$onRenderTargetBlockOutline(MultiBufferSource.BufferSource immediate, PoseStack matrices, boolean renderBlockOutline, LevelRenderState renderStates, CallbackInfo ci) {
        if (WorldRenderRegistry.isEmptyBlockOutlineListeners) return;

        mcpitanlib$contextCache.worldRenderer = (LevelRenderer)(Object) this;
        mcpitanlib$contextCache.advancedTranslucency = hasRenderedAllSections();
        mcpitanlib$contextCache.consumers = immediate;
        mcpitanlib$contextCache.matrixStack = matrices;
        mcpitanlib$contextCache.tickDelta = ticks;

        BeforeBlockOutlineEvent event = new BeforeBlockOutlineEvent(mcpitanlib$contextCache, Minecraft.getInstance().hitResult);

        for (BeforeBlockOutlineListener listener : WorldRenderRegistry.beforeBlockOutlineListeners) {
            if (!listener.beforeBlockOutline(event)) {
                ci.cancel();
                return;
            }
        }
    }

    @Inject(method = "renderLevel", at = @At("HEAD"))
    private void beforeRender(GraphicsResourceAllocator allocator, DeltaTracker tickCounter, boolean renderBlockOutline, Camera camera, Matrix4f positionMatrix, Matrix4f matrix4f, Matrix4f projectionMatrix, GpuBufferSlice fogBuffer, Vector4f fogColor, boolean renderSky, CallbackInfo ci) {
        mcpitanlib$contextCache.prepare(minecraft.gameRenderer, (LevelRenderer) (Object) this, level, tickCounter, renderBlockOutline, camera, positionMatrix, matrix4f, projectionMatrix);
    }

    @ModifyExpressionValue(method = "renderLevel", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/LevelRenderer;prepareCullFrustum(Lorg/joml/Matrix4f;Lorg/joml/Matrix4f;Lnet/minecraft/world/phys/Vec3;)Lnet/minecraft/client/renderer/culling/Frustum;"))
    private Frustum onSetupFrustum(Frustum frustum) {
        mcpitanlib$contextCache.frustum = frustum;
        return frustum;
    }


    @WrapOperation(method = "renderLevel", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/WorldBorderRenderer;extract(Lnet/minecraft/world/level/border/WorldBorder;FLnet/minecraft/world/phys/Vec3;DLnet/minecraft/client/renderer/state/WorldBorderRenderState;)V"))
    private void mcpitanlib$onWorldBorderExtraction(WorldBorderRenderer instance, WorldBorder worldBorder, float tickDelta, Vec3 vec3d, double d, WorldBorderRenderState worldBorderRenderState, Operation<Void> original) {
        original.call(instance, worldBorder, tickDelta, vec3d, d, worldBorderRenderState);
        if (WorldRenderRegistry.isEmptyWorldRenderAfterLevelListeners) return;

        for (WorldRenderContextListener listener : WorldRenderRegistry.worldRenderAfterLevelListeners) {
            listener.render(mcpitanlib$contextCache);
        }
    }
}
