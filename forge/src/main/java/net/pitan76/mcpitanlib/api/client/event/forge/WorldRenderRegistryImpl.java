package net.pitan76.mcpitanlib.api.client.event.forge;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.*;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.profiler.Profiler;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderHighlightEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.pitan76.mcpitanlib.MCPitanLib;
import net.pitan76.mcpitanlib.api.client.event.listener.BeforeBlockOutlineEvent;
import net.pitan76.mcpitanlib.api.client.event.listener.BeforeBlockOutlineListener;
import net.pitan76.mcpitanlib.api.client.event.listener.WorldRenderContext;
import org.joml.Matrix4f;

import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber(modid = MCPitanLib.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class WorldRenderRegistryImpl {

    public static List<BeforeBlockOutlineListener> beforeBlockOutlineListeners = new ArrayList<>();

    @SubscribeEvent
    public static void renderOutlineEvent(RenderHighlightEvent event) {
        if (MinecraftClient.getInstance().world != null && MinecraftClient.getInstance().world.getTime() % 20 == 0)
            System.out.println("renderOutlineEvent");

        for (BeforeBlockOutlineListener listener : beforeBlockOutlineListeners) {
            boolean eventContinue = listener.beforeBlockOutline(new BeforeBlockOutlineEvent(new WorldRenderContext() {
                @Override
                public WorldRenderer worldRenderer() {
                    return event.getLevelRenderer();
                }

                @Override
                public MatrixStack matrixStack() {
                    return event.getPoseStack();
                }

                @Override
                public float tickDelta() {
                    return event.getPartialTick();
                }

                @Override
                public long limitTime() {
                    return (long) event.getPartialTick();
                }

                @Override
                public boolean blockOutlines() {
                    return event.getTarget().getType() == HitResult.Type.BLOCK;
                }

                @Override
                public Camera camera() {
                    return event.getCamera();
                }

                @Override
                public GameRenderer gameRenderer() {
                    return MinecraftClient.getInstance().gameRenderer;
                }

                @Override
                public LightmapTextureManager lightmapTextureManager() {
                    return MinecraftClient.getInstance().gameRenderer.getLightmapTextureManager();
                }

                @Deprecated
                @Override
                public Matrix4f projectionMatrix() {
                    return null;
                }

                @Override
                public ClientWorld world() {
                    return MinecraftClient.getInstance().world;
                }

                @Override
                public Profiler profiler() {
                    return MinecraftClient.getInstance().getProfiler();
                }

                @Deprecated
                @Override
                public boolean advancedTranslucency() {
                    return event.getLevelRenderer().isTerrainRenderComplete();
                }

                @Override
                public VertexConsumerProvider consumers() {
                    return event.getMultiBufferSource();
                }

                @Override
                public Frustum frustum() {
                    return event.getLevelRenderer().getFrustum();
                }
            }, event.getTarget()));

            if (!eventContinue) {
                event.setCanceled(true);
                break;
            }
        }
    }

    public static void registerWorldRenderBeforeBlockOutline(BeforeBlockOutlineListener listener) {
        beforeBlockOutlineListeners.add(listener);
    }
}
