package net.pitan76.mcpitanlib.api.client.event.fabric;

import net.fabricmc.fabric.api.client.rendering.v1.world.WorldRenderEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.*;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.world.ClientWorld;
import net.pitan76.mcpitanlib.api.client.event.listener.BeforeBlockOutlineEvent;
import net.pitan76.mcpitanlib.api.client.event.listener.BeforeBlockOutlineListener;
import net.pitan76.mcpitanlib.api.client.event.listener.WorldRenderContext;
import net.pitan76.mcpitanlib.api.client.event.listener.WorldRenderContextListener;
import net.pitan76.mcpitanlib.api.util.client.ClientUtil;
import org.jetbrains.annotations.Nullable;
import org.joml.Matrix4f;

public class WorldRenderRegistryImpl {
    public static void _registerWorldRenderBeforeBlockOutline(BeforeBlockOutlineListener listener) {
        WorldRenderEvents.BEFORE_BLOCK_OUTLINE.register(((worldRenderContext, renderState) -> listener.beforeBlockOutline(new BeforeBlockOutlineEvent(
        new WorldRenderContext() {
            @Override
            public WorldRenderer getWorldRenderer() {
                return worldRenderContext.worldRenderer();
            }

            @Override
            public MatrixStack getMatrixStack() {
                return worldRenderContext.matrices();
            }

            @Override
            public float getTickDelta() {
                return MinecraftClient.getInstance().getRenderTickCounter().getDynamicDeltaTicks();
            }

            @Override
            public Camera getCamera() {
                return worldRenderContext.gameRenderer().getCamera();
            }

            @Override
            public GameRenderer getGameRenderer() {
                return worldRenderContext.gameRenderer();
            }

            @Override
            public LightmapTextureManager getLightmapTextureManager() {
                return getGameRenderer().getLightmapTextureManager();
            }

            @Override
            public Matrix4f getProjectionMatrix() {
                return worldRenderContext.gameRenderer().getBasicProjectionMatrix(0);
            }

            @Override
            public ClientWorld getWorld() {
                return ClientUtil.getWorld();
            }

            @Override
            public boolean isAdvancedTranslucency() {
                return renderState.isTranslucent();
            }

            @Override
            public VertexConsumerProvider getConsumers() {
                return worldRenderContext.consumers();
            }

            @Override
            public Frustum getFrustum() {
                return worldRenderContext.worldRenderer().getCapturedFrustum();
            }
        }, renderState))));
    }

    public static void _registerWorldRenderAfterLevel(WorldRenderContextListener listener) {
        WorldRenderEvents.END_MAIN.register((context -> {
            listener.render(new WorldRenderContext() {
                @Override
                public WorldRenderer getWorldRenderer() {
                    return context.worldRenderer();
                }

                @Override
                public MatrixStack getMatrixStack() {
                    return new MatrixStack();
                }

                @Override
                public float getTickDelta() {
                    return MinecraftClient.getInstance().getRenderTickCounter().getDynamicDeltaTicks();
                }

                @Override
                public Camera getCamera() {
                    return context.gameRenderer().getCamera();
                }

                @Override
                public GameRenderer getGameRenderer() {
                    return context.gameRenderer();
                }

                @Override
                public LightmapTextureManager getLightmapTextureManager() {
                    return getGameRenderer().getLightmapTextureManager();
                }

                @Override
                public Matrix4f getProjectionMatrix() {
                    return context.gameRenderer().getBasicProjectionMatrix(0);
                }

                @Override
                public ClientWorld getWorld() {
                    return ClientUtil.getWorld();
                }

                @Override
                public boolean isAdvancedTranslucency() {
                    return true;
                }

                @Override
                public @Nullable VertexConsumerProvider getConsumers() {
                    return context.consumers();
                }

                @Override
                public @Nullable Frustum getFrustum() {
                    return context.worldRenderer().getCapturedFrustum();
                }
            });
        }));
    }
}
