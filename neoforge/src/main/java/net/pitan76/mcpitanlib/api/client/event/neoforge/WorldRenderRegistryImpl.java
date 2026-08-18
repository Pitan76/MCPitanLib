package net.pitan76.mcpitanlib.api.client.event.neoforge;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Camera;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.client.renderer.culling.Frustum;
import net.neoforged.neoforge.client.event.ExtractBlockOutlineRenderStateEvent;
import net.neoforged.neoforge.client.event.RenderLevelStageEvent;
import net.pitan76.mcpitanlib.api.client.event.listener.BeforeBlockOutlineEvent;
import net.pitan76.mcpitanlib.api.client.event.listener.BeforeBlockOutlineListener;
import net.pitan76.mcpitanlib.api.client.event.listener.WorldRenderContext;
import net.pitan76.mcpitanlib.api.client.event.listener.WorldRenderContextListener;
import net.pitan76.mcpitanlib.api.util.client.ClientUtil;
import org.jetbrains.annotations.Nullable;
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
                public LevelRenderer getWorldRenderer() {
                    return ClientUtil.getWorldRenderer();
                }

                @Override
                public PoseStack getMatrixStack() {
                    return new PoseStack();
                }

                @Override
                public float getTickDelta() {
                    return ClientUtil.getClient().getDeltaTracker().getGameTimeDeltaTicks();
                }

                @Override
                public Camera getCamera() {
                    return event.getCamera();
                }

                @Override
                public GameRenderer getGameRenderer() {
                    return ClientUtil.getClient().gameRenderer;
                }

                @Deprecated
                @Override
                public Matrix4f getProjectionMatrix() {
                    return new Matrix4f();
                }

                @Override
                public ClientLevel getWorld() {
                    return ClientUtil.getClient().level;
                }

                @Deprecated
                @Override
                public boolean isAdvancedTranslucency() {
                    return event.isInTranslucentPass();
                }

                @Override
                public @Nullable VertexConsumer getConsumers() {
                    return null;
                }

                @Override
                public Frustum getFrustum() {
                    return ClientUtil.getClient().gameRenderer.mainCamera().getCapturedFrustum();
                }
            }, event.getLevelRenderState().blockOutlineRenderState));

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
                public LevelRenderer getWorldRenderer() {
                    return event.getLevelRenderer();
                }

                @Override
                public PoseStack getMatrixStack() {
                    return event.getPoseStack();
                }

                @Override
                public float getTickDelta() {
                    return event.getLevelRenderState().gameTime;
                }

                @Override
                public Camera getCamera() {
                    return ClientUtil.getGameRenderer().mainCamera();
                }

                @Override
                public GameRenderer getGameRenderer() {
                    return ClientUtil.getGameRenderer();
                }

                @Override
                public Matrix4f getProjectionMatrix() {
                    return new Matrix4f();
                }

                @Override
                public ClientLevel getWorld() {
                    return ClientUtil.getWorld();
                }

                @Deprecated
                @Override
                public boolean isAdvancedTranslucency() {
                    return false;
                }

                @Override
                public @Nullable VertexConsumer getConsumers() {
                    return null;
                }

                @Override
                public Frustum getFrustum() {
                    return ClientUtil.getGameRenderer().mainCamera().getCapturedFrustum();
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

