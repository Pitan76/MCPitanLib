package net.pitan76.mcpitanlib.api.client.event.neoforge;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.*;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.world.ClientWorld;
import net.neoforged.neoforge.client.event.ExtractBlockOutlineRenderStateEvent;
import net.neoforged.neoforge.client.event.RenderFrameEvent;
import net.neoforged.neoforge.client.event.RenderLevelStageEvent;
import net.pitan76.mcpitanlib.api.client.event.listener.BeforeBlockOutlineEvent;
import net.pitan76.mcpitanlib.api.client.event.listener.BeforeBlockOutlineListener;
import net.pitan76.mcpitanlib.api.client.event.listener.WorldRenderContext;
import net.pitan76.mcpitanlib.api.client.event.listener.WorldRenderContextListener;
import net.pitan76.mcpitanlib.api.util.client.ClientUtil;
import org.joml.Matrix4f;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class WorldRenderRegistryImpl {

    public static List<BeforeBlockOutlineListener> beforeBlockOutlineListeners = new CopyOnWriteArrayList<>();
    public static List<WorldRenderContextListener> worldRenderAfterLevelListeners = new CopyOnWriteArrayList<>();

    public static void renderOutlineEventBlock(ExtractBlockOutlineRenderStateEvent event) {
        for (BeforeBlockOutlineListener listener : beforeBlockOutlineListeners) {
            boolean eventContinue = listener.beforeBlockOutline(new BeforeBlockOutlineEvent(new WorldRenderContext() {
                @Override
                public WorldRenderer getWorldRenderer() {
                    return event.getLevelRenderer();
                }

                @Override
                public MatrixStack getMatrixStack() {
                    return new MatrixStack();
                }

                @Override
                public float getTickDelta() {
                    return ClientUtil.getClient().getRenderTickCounter().getDynamicDeltaTicks();
                }

                @Override
                public Camera getCamera() {
                    return event.getCamera();
                }

                @Override
                public GameRenderer getGameRenderer() {
                    return MinecraftClient.getInstance().gameRenderer;
                }

                @Override
                public LightmapTextureManager getLightmapTextureManager() {
                    return MinecraftClient.getInstance().gameRenderer.getLightmapTextureManager();
                }

                @Deprecated
                @Override
                public Matrix4f getProjectionMatrix() {
                    return null;
                }

                @Override
                public ClientWorld getWorld() {
                    return MinecraftClient.getInstance().world;
                }

                @Deprecated
                @Override
                public boolean isAdvancedTranslucency() {
                    return event.isInTranslucentPass();
                }

                @Override
                public VertexConsumerProvider getConsumers() {
                    return ClientUtil.getClient().getBufferBuilders().getOutlineVertexConsumers();
                }

                @Override
                public Frustum getFrustum() {
                    return event.getLevelRenderer().getCapturedFrustum();
                }
            }, event.getLevelRenderState().outlineRenderState));

            if (!eventContinue) {
                event.setCanceled(true);
                break;
            }
        }
    }
//
//    public static void renderOutlineEvent(RenderHighlightEvent.Entity event) {
//        for (BeforeBlockOutlineListener listener : beforeBlockOutlineListeners) {
//            listener.beforeBlockOutline(new BeforeBlockOutlineEvent(new WorldRenderContext() {
//                @Override
//                public WorldRenderer getWorldRenderer() {
//                    return event.getLevelRenderer();
//                }
//
//                @Override
//                public MatrixStack getMatrixStack() {
//                    return event.getPoseStack();
//                }
//
//                @Override
//                public float getTickDelta() {
//                    return event.getDeltaTracker().getDynamicDeltaTicks();
//                }
//
//                @Override
//                public Camera getCamera() {
//                    return event.getCamera();
//                }
//
//                @Override
//                public GameRenderer getGameRenderer() {
//                    return MinecraftClient.getInstance().gameRenderer;
//                }
//
//                @Override
//                public LightmapTextureManager getLightmapTextureManager() {
//                    return MinecraftClient.getInstance().gameRenderer.getLightmapTextureManager();
//                }
//
//                @Deprecated
//                @Override
//                public Matrix4f getProjectionMatrix() {
//                    return null;
//                }
//
//                @Override
//                public ClientWorld getWorld() {
//                    return MinecraftClient.getInstance().world;
//                }
//
//                @Deprecated
//                @Override
//                public boolean isAdvancedTranslucency() {
//                    return event.getLevelRenderer().isTerrainRenderComplete();
//                }
//
//                @Override
//                public VertexConsumerProvider getConsumers() {
//                    return event.getMultiBufferSource();
//                }
//
//                @Override
//                public Frustum getFrustum() {
//                    return event.getLevelRenderer().getFrustum();
//                }
//            }, event.getTarget()));
//        }
//    }

    public static void renderLevelStageEvent(RenderLevelStageEvent.AfterLevel event) {
        for (WorldRenderContextListener listener : worldRenderAfterLevelListeners) {
            listener.render(new WorldRenderContext() {
                @Override
                public WorldRenderer getWorldRenderer() {
                    return event.getLevelRenderer();
                }

                @Override
                public MatrixStack getMatrixStack() {
                    return event.getPoseStack();
                }

                @Override
                public float getTickDelta() {
                    return event.getLevelRenderer().getTicks();
                }

                @Override
                public Camera getCamera() {
                    return MinecraftClient.getInstance().gameRenderer.getCamera();
                }

                @Override
                public GameRenderer getGameRenderer() {
                    return MinecraftClient.getInstance().gameRenderer;
                }

                @Override
                public LightmapTextureManager getLightmapTextureManager() {
                    return MinecraftClient.getInstance().gameRenderer.getLightmapTextureManager();
                }

                @Deprecated
                @Override
                public Matrix4f getProjectionMatrix() {
                    return event.getModelViewMatrix();
                }

                @Override
                public ClientWorld getWorld() {
                    return MinecraftClient.getInstance().world;
                }

                @Deprecated
                @Override
                public boolean isAdvancedTranslucency() {
                    return event.getLevelRenderer().isTerrainRenderComplete();
                }

                @Override
                public VertexConsumerProvider getConsumers() {
                    return MinecraftClient.getInstance().getBufferBuilders().getOutlineVertexConsumers();
                }

                @Override
                public Frustum getFrustum() {
                    return event.getLevelRenderer().getCapturedFrustum();
                }
            });
        }
    }

    public static void _registerWorldRenderBeforeBlockOutline(BeforeBlockOutlineListener listener) {
        beforeBlockOutlineListeners.add(listener);
    }

    public static void _registerWorldRenderAfterLevel(WorldRenderContextListener listener) {
        worldRenderAfterLevelListeners.add(listener);
    }
}

