package net.pitan76.mcpitanlib.api.client.event.forge;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.*;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.util.math.Matrix4f;
import net.minecraft.util.profiler.Profiler;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.DrawHighlightEvent;
import net.minecraftforge.client.event.RenderLevelStageEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.pitan76.mcpitanlib.MCPitanLib;
import net.pitan76.mcpitanlib.api.client.event.listener.BeforeBlockOutlineEvent;
import net.pitan76.mcpitanlib.api.client.event.listener.BeforeBlockOutlineListener;
import net.pitan76.mcpitanlib.api.client.event.listener.WorldRenderContext;
import net.pitan76.mcpitanlib.api.client.event.listener.WorldRenderContextListener;

import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber(modid = MCPitanLib.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class WorldRenderRegistryImpl {

    public static List<BeforeBlockOutlineListener> beforeBlockOutlineListeners = new ArrayList<>();
    public static List<WorldRenderContextListener> worldRenderAfterLevelListeners = new ArrayList<>();

    @SubscribeEvent
    public static void renderOutlineEventBlock(DrawHighlightEvent event) {
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
                    return event.getPartialTicks();
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
                    return null;
                }
            }, event.getTarget()));

            if (!eventContinue) {
                event.setCanceled(true);
                break;
            }
        }
    }

    @SubscribeEvent
    public static void registerWorldRenderAfterLevel(RenderLevelStageEvent event) {
        if (!event.getStage().equals(RenderLevelStageEvent.Stage.AFTER_SKY)) return;

        for (WorldRenderContextListener listener : worldRenderAfterLevelListeners) {
            listener.renderer(new WorldRenderContext() {
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
                    return event.getPartialTick();
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
                    return null;
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
