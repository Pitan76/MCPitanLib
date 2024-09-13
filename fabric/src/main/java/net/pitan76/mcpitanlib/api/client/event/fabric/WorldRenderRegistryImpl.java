package net.pitan76.mcpitanlib.api.client.event.fabric;

import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderEvents;
import net.minecraft.client.render.*;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.util.profiler.Profiler;
import net.pitan76.mcpitanlib.api.client.event.listener.BeforeBlockOutlineEvent;
import net.pitan76.mcpitanlib.api.client.event.listener.BeforeBlockOutlineListener;
import net.pitan76.mcpitanlib.api.client.event.listener.WorldRenderContext;
import net.pitan76.mcpitanlib.api.client.event.listener.WorldRenderContextListener;
import org.jetbrains.annotations.Nullable;
import org.joml.Matrix4f;

public class WorldRenderRegistryImpl {
    public static void registerWorldRenderBeforeBlockOutline(BeforeBlockOutlineListener listener) {
        WorldRenderEvents.BEFORE_BLOCK_OUTLINE.register(((worldRenderContext, hitResult) -> listener.beforeBlockOutline(new BeforeBlockOutlineEvent(
        new WorldRenderContext() {
            @Override
            public WorldRenderer getWorldRenderer() {
                return worldRenderContext.worldRenderer();
            }

            @Override
            public MatrixStack getMatrixStack() {
                return worldRenderContext.matrixStack();
            }

            @Override
            public float getTickDelta() {
                return worldRenderContext.tickCounter().getTickDelta(true);
            }

            @Override
            public Camera getCamera() {
                return worldRenderContext.camera();
            }

            @Override
            public GameRenderer getGameRenderer() {
                return worldRenderContext.gameRenderer();
            }

            @Override
            public LightmapTextureManager getLightmapTextureManager() {
                return worldRenderContext.lightmapTextureManager();
            }

            @Override
            public Matrix4f getProjectionMatrix() {
                return worldRenderContext.projectionMatrix();
            }

            @Override
            public ClientWorld getWorld() {
                return worldRenderContext.world();
            }

            @Override
            public Profiler getProfiler() {
                return worldRenderContext.profiler();
            }

            @Override
            public boolean isAdvancedTranslucency() {
                return worldRenderContext.advancedTranslucency();
            }

            @Override
            public @Nullable VertexConsumerProvider getConsumers() {
                return worldRenderContext.consumers();
            }

            @Override
            public @Nullable Frustum getFrustum() {
                return worldRenderContext.frustum();
            }
        }, hitResult))));
    }

    public static void registerWorldRenderAfterLevel(WorldRenderContextListener listener) {
        WorldRenderEvents.END.register((context -> {
            listener.renderer(new WorldRenderContext() {
                @Override
                public WorldRenderer getWorldRenderer() {
                    return context.worldRenderer();
                }

                @Override
                public MatrixStack getMatrixStack() {
                    return context.matrixStack();
                }

                @Override
                public float getTickDelta() {
                    return context.tickCounter().getTickDelta(true);
                }

                @Override
                public Camera getCamera() {
                    return context.camera();
                }

                @Override
                public GameRenderer getGameRenderer() {
                    return context.gameRenderer();
                }

                @Override
                public LightmapTextureManager getLightmapTextureManager() {
                    return context.lightmapTextureManager();
                }

                @Override
                public Matrix4f getProjectionMatrix() {
                    return context.projectionMatrix();
                }

                @Override
                public ClientWorld getWorld() {
                    return context.world();
                }

                @Override
                public Profiler getProfiler() {
                    return context.profiler();
                }

                @Override
                public boolean isAdvancedTranslucency() {
                    return context.advancedTranslucency();
                }

                @Override
                public @Nullable VertexConsumerProvider getConsumers() {
                    return context.consumers();
                }

                @Override
                public @Nullable Frustum getFrustum() {
                    return context.frustum();
                }
            });
        }));
    }
}
