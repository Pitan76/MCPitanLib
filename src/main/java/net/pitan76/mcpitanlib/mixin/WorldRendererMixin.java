package net.pitan76.mcpitanlib.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.mojang.blaze3d.buffers.GpuBufferSlice;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.*;
import net.minecraft.client.render.state.WorldBorderRenderState;
import net.minecraft.client.render.state.WorldRenderState;
import net.minecraft.client.util.ObjectAllocator;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.border.WorldBorder;
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

// TODO(Ravel): can not resolve target class WorldRenderer
// TODO(Ravel): can not resolve target class WorldRenderer
@Mixin(WorldRenderer.class)
public abstract class WorldRendererMixin {
    // TODO(Ravel): Could not determine a single target
// TODO(Ravel): Could not determine a single target
    @Shadow private int ticks;

    // TODO(Ravel): Could not determine a single target
// TODO(Ravel): Could not determine a single target
    @Shadow public abstract boolean isTerrainRenderComplete();

    // TODO(Ravel): Could not determine a single target
// TODO(Ravel): Could not determine a single target
    @Shadow @Nullable public abstract Frustum getCapturedFrustum();

    // TODO(Ravel): Could not determine a single target
// TODO(Ravel): Could not determine a single target
    @Shadow @Final private MinecraftClient client;
    // TODO(Ravel): Could not determine a single target
// TODO(Ravel): Could not determine a single target
    @Shadow @Final private WorldRenderState worldRenderState;
    // TODO(Ravel): Could not determine a single target
// TODO(Ravel): Could not determine a single target
    @Shadow @Nullable private ClientWorld world;
    @Unique
    private final WorldRenderContextImpl mcpitanlib$contextCache = new WorldRenderContextImpl();

    // TODO(Ravel): no target class
// TODO(Ravel): no target class
    @Inject(method = "renderTargetBlockOutline", at = @At("HEAD"), cancellable = true)
    private void mcpitanlib$onRenderTargetBlockOutline(VertexConsumerProvider.Immediate immediate, MatrixStack matrices, boolean renderBlockOutline, WorldRenderState renderStates, CallbackInfo ci) {
        if (WorldRenderRegistry.isEmptyBlockOutlineListeners) return;

        mcpitanlib$contextCache.worldRenderer = (WorldRenderer)(Object) this;
        mcpitanlib$contextCache.advancedTranslucency = isTerrainRenderComplete();
        mcpitanlib$contextCache.consumers = immediate;
        mcpitanlib$contextCache.matrixStack = matrices;
        mcpitanlib$contextCache.tickDelta = ticks;

        BeforeBlockOutlineEvent event = new BeforeBlockOutlineEvent(mcpitanlib$contextCache, MinecraftClient.getInstance().crosshairTarget);

        for (BeforeBlockOutlineListener listener : WorldRenderRegistry.beforeBlockOutlineListeners) {
            if (!listener.beforeBlockOutline(event)) {
                ci.cancel();
                return;
            }
        }
    }

    // TODO(Ravel): no target class
// TODO(Ravel): no target class
    @Inject(method = "render", at = @At("HEAD"))
    private void beforeRender(ObjectAllocator allocator, RenderTickCounter tickCounter, boolean renderBlockOutline, Camera camera, Matrix4f positionMatrix, Matrix4f matrix4f, Matrix4f projectionMatrix, GpuBufferSlice fogBuffer, Vector4f fogColor, boolean renderSky, CallbackInfo ci) {
        mcpitanlib$contextCache.prepare(client.gameRenderer, (WorldRenderer) (Object) this, worldRenderState, world, tickCounter, renderBlockOutline, camera, positionMatrix, matrix4f, projectionMatrix);
    }

    // TODO(Ravel): no target class
// TODO(Ravel): no target class
    @ModifyExpressionValue(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/WorldRenderer;setupFrustum(Lorg/joml/Matrix4f;Lorg/joml/Matrix4f;Lnet/minecraft/util/math/Vec3d;)Lnet/minecraft/client/render/Frustum;"))
    private Frustum onSetupFrustum(Frustum frustum) {
        mcpitanlib$contextCache.frustum = frustum;
        return frustum;
    }


    // TODO(Ravel): no target class
// TODO(Ravel): no target class
    @WrapOperation(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/WorldBorderRendering;updateRenderState(Lnet/minecraft/world/border/WorldBorder;FLnet/minecraft/util/math/Vec3d;DLnet/minecraft/client/render/state/WorldBorderRenderState;)V"))
    private void mcpitanlib$onWorldBorderExtraction(WorldBorderRendering instance, WorldBorder worldBorder, float tickDelta, Vec3d vec3d, double d, WorldBorderRenderState worldBorderRenderState, Operation<Void> original) {
        original.call(instance, worldBorder, tickDelta, vec3d, d, worldBorderRenderState);
        if (WorldRenderRegistry.isEmptyWorldRenderAfterLevelListeners) return;

        for (WorldRenderContextListener listener : WorldRenderRegistry.worldRenderAfterLevelListeners) {
            listener.render(mcpitanlib$contextCache);
        }
    }
}
