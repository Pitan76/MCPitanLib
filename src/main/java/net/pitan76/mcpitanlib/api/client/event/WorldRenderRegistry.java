package net.pitan76.mcpitanlib.api.client.event;

//import dev.architectury.injectables.annotations.ExpectPlatform;
import net.pitan76.mcpitanlib.api.client.event.listener.BeforeBlockOutlineEvent;
import net.pitan76.mcpitanlib.api.client.event.listener.BeforeBlockOutlineListener;
import net.pitan76.mcpitanlib.api.client.event.listener.WorldRenderContext;
import net.pitan76.mcpitanlib.api.client.event.listener.WorldRenderContextListener;
import net.pitan76.mcpitanlib.api.util.client.ClientUtil;
import org.jetbrains.annotations.Nullable;
import org.joml.Matrix4f;

import java.util.ArrayList;
import java.util.List;

public class WorldRenderRegistry {

    public static List<BeforeBlockOutlineListener> beforeBlockOutlineListeners = new ArrayList<>();
    public static List<WorldRenderContextListener> worldRenderAfterLevelListeners = new ArrayList<>();

    public static boolean isEmptyBlockOutlineListeners = true;
    public static boolean isEmptyWorldRenderAfterLevelListeners = true;

    public WorldRenderRegistry() {

    }

    //@ExpectPlatform
    public static void registerWorldRenderBeforeBlockOutline(BeforeBlockOutlineListener listener) {
        beforeBlockOutlineListeners.add(listener);
        isEmptyBlockOutlineListeners = false;
        //throw new AssertionError();
    }

    //@ExpectPlatform
    public static void registerWorldRenderAfterLevel(WorldRenderContextListener listener) {
        worldRenderAfterLevelListeners.add(listener);
        isEmptyWorldRenderAfterLevelListeners = false;
        //throw new AssertionError();
    }
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
                    return context.matrices();
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
