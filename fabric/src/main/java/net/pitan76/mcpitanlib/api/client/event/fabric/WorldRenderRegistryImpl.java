package net.pitan76.mcpitanlib.api.client.event.fabric;

import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderEvents;
import net.minecraft.client.render.*;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.util.profiler.Profiler;
import net.pitan76.mcpitanlib.api.client.event.listener.BeforeBlockOutlineEvent;
import net.pitan76.mcpitanlib.api.client.event.listener.BeforeBlockOutlineListener;
import net.pitan76.mcpitanlib.api.client.event.listener.WorldRenderContext;
import org.jetbrains.annotations.Nullable;
import org.joml.Matrix4f;

public class WorldRenderRegistryImpl {
    public static void registerWorldRenderBeforeBlockOutline(BeforeBlockOutlineListener listener) {
        WorldRenderEvents.BEFORE_BLOCK_OUTLINE.register(((worldRenderContext, hitResult) -> listener.beforeBlockOutline(new BeforeBlockOutlineEvent(
        new WorldRenderContext() {
            @Override
            public WorldRenderer worldRenderer() {
                return worldRenderContext.worldRenderer();
            }

            @Override
            public MatrixStack matrixStack() {
                return worldRenderContext.matrixStack();
            }

            @Override
            public float tickDelta() {
                return worldRenderContext.tickDelta();
            }

            @Override
            public long limitTime() {
                return worldRenderContext.limitTime();
            }

            @Override
            public boolean blockOutlines() {
                return worldRenderContext.blockOutlines();
            }

            @Override
            public Camera camera() {
                return worldRenderContext.camera();
            }

            @Override
            public GameRenderer gameRenderer() {
                return worldRenderContext.gameRenderer();
            }

            @Override
            public LightmapTextureManager lightmapTextureManager() {
                return worldRenderContext.lightmapTextureManager();
            }

            @Override
            public Matrix4f projectionMatrix() {
                return worldRenderContext.projectionMatrix();
            }

            @Override
            public ClientWorld world() {
                return worldRenderContext.world();
            }

            @Override
            public Profiler profiler() {
                return worldRenderContext.profiler();
            }

            @Override
            public boolean advancedTranslucency() {
                return worldRenderContext.advancedTranslucency();
            }

            @Override
            public @Nullable VertexConsumerProvider consumers() {
                return worldRenderContext.consumers();
            }

            @Override
            public @Nullable Frustum frustum() {
                return worldRenderContext.frustum();
            }
        }, hitResult))));
    }
}
