package net.pitan76.mcpitanlib.mixin;

import com.mojang.blaze3d.buffers.GpuBufferSlice;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.chunk.ChunkSectionsToRender;
import com.mojang.blaze3d.resource.GraphicsResourceAllocator;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.client.renderer.state.level.LevelRenderState;
import net.pitan76.mcpitanlib.api.client.event.WorldRenderRegistry;
import net.pitan76.mcpitanlib.api.client.event.listener.*;
import org.jetbrains.annotations.Nullable;
import org.joml.Matrix4f;
import org.joml.Matrix4fc;
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
    private void mcpitanlib$beforeRender(GraphicsResourceAllocator allocator, DeltaTracker tickCounter, boolean renderOutline, CameraRenderState cameraRenderState, Matrix4fc modelViewMatrix, GpuBufferSlice terrainFog, Vector4f fogColor, boolean shouldRenderSky, ChunkSectionsToRender chunkSectionsToRender, CallbackInfo ci) {
        mcpitanlib$contextCache.prepare(minecraft.gameRenderer, (LevelRenderer) (Object) this, level, tickCounter, renderOutline, Minecraft.getInstance().gameRenderer.getMainCamera(), new Matrix4f(), new Matrix4f(), new Matrix4f());
    }

//    @ModifyExpressionValue(method = "renderLevel", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/LevelRenderer;cullFrustum(Lorg/joml/Matrix4f;Lorg/joml/Matrix4f;Lnet/minecraft/world/phys/Vec3;)Lnet/minecraft/client/renderer/culling/Frustum;"))
//    private Frustum onSetupFrustum(Frustum frustum) {
//        mcpitanlib$contextCache.frustum = frustum;
//        return frustum;
//    }

    @Inject(method = "renderLevel", at = @At("TAIL"))
    private void mcpitanlib$onWorldRenderEnd(GraphicsResourceAllocator allocator, DeltaTracker tickCounter, boolean renderOutline, CameraRenderState cameraState, Matrix4fc modelViewMatrix, GpuBufferSlice terrainFog, Vector4f fogColor,boolean shouldRenderSky,ChunkSectionsToRender chunkSectionsToRender, CallbackInfo ci) {
        if (WorldRenderRegistry.isEmptyWorldRenderAfterLevelListeners) return;

        for (WorldRenderContextListener listener : WorldRenderRegistry.worldRenderAfterLevelListeners) {
            listener.render(mcpitanlib$contextCache);
        }
    }
}
