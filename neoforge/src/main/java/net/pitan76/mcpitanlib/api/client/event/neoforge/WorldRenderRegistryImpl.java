package net.pitan76.mcpitanlib.api.client.event.neoforge;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.*;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.util.profiler.Profiler;
import net.neoforged.neoforge.client.event.RenderHighlightEvent;
import net.neoforged.neoforge.client.event.RenderLevelStageEvent;
import net.pitan76.mcpitanlib.api.client.event.listener.BeforeBlockOutlineEvent;
import net.pitan76.mcpitanlib.api.client.event.listener.BeforeBlockOutlineListener;
import net.pitan76.mcpitanlib.api.client.event.listener.WorldRenderContext;
import net.pitan76.mcpitanlib.api.client.event.listener.WorldRenderContextListener;
import org.joml.Matrix4f;

import java.util.ArrayList;
import java.util.List;

public class WorldRenderRegistryImpl {

    public static List<BeforeBlockOutlineListener> beforeBlockOutlineListeners = new ArrayList<>();
    public static List<WorldRenderContextListener> worldRenderAfterLevelListeners = new ArrayList<>();

    public static void renderOutlineEventBlock(RenderHighlightEvent.Block event) {
        for (BeforeBlockOutlineListener listener : beforeBlockOutlineListeners) {
            boolean eventContinue = listener.beforeBlockOutline(new BeforeBlockOutlineEvent(new WorldRenderContext() {
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
                    return event.getDeltaTracker().getTickDelta(true);
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

                @Override
                public Profiler getProfiler() {
                    return MinecraftClient.getInstance().getProfiler();
                }

                @Deprecated
                @Override
                public boolean isAdvancedTranslucency() {
                    return event.getLevelRenderer().isTerrainRenderComplete();
                }

                @Override
                public VertexConsumerProvider getConsumers() {
                    return event.getMultiBufferSource();
                }

                @Override
                public Frustum getFrustum() {
                    return event.getLevelRenderer().getFrustum();
                }
            }, event.getTarget()));

            if (!eventContinue) {
                event.setCanceled(true);
                break;
            }
        }
    }

    public static void renderOutlineEvent(RenderHighlightEvent.Entity event) {
        for (BeforeBlockOutlineListener listener : beforeBlockOutlineListeners) {
            listener.beforeBlockOutline(new BeforeBlockOutlineEvent(new WorldRenderContext() {
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
                    return event.getDeltaTracker().getTickDelta(true);
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

                @Override
                public Profiler getProfiler() {
                    return MinecraftClient.getInstance().getProfiler();
                }

                @Deprecated
                @Override
                public boolean isAdvancedTranslucency() {
                    return event.getLevelRenderer().isTerrainRenderComplete();
                }

                @Override
                public VertexConsumerProvider getConsumers() {
                    return event.getMultiBufferSource();
                }

                @Override
                public Frustum getFrustum() {
                    return event.getLevelRenderer().getFrustum();
                }
            }, event.getTarget()));
        }
    }

    public static void renderLevelStageEvent(RenderLevelStageEvent event) {
        if (!event.getStage().equals(RenderLevelStageEvent.Stage.AFTER_LEVEL)) return;

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
                    return event.getPartialTick().getTickDelta(true);
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
                    return event.getProjectionMatrix();
                }

                @Override
                public ClientWorld getWorld() {
                    return MinecraftClient.getInstance().world;
                }

                @Override
                public Profiler getProfiler() {
                    return MinecraftClient.getInstance().getProfiler();
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
                    return event.getLevelRenderer().getFrustum();
                }
            });
        }
    }

    public static void registerWorldRenderBeforeBlockOutline(BeforeBlockOutlineListener listener) {
        beforeBlockOutlineListeners.add(listener);
    }

    public static void registerWorldRenderAfterLevel(WorldRenderContextListener listener) {
        worldRenderAfterLevelListeners.add(listener);
    }
}

