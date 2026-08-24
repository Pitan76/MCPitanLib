package net.pitan76.mcpitanlib.api.client.event.listener;

import net.minecraft.client.render.*;
import net.minecraft.client.render.state.WorldRenderState;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.util.math.RotationAxis;
import net.minecraft.util.math.Vec3d;
import net.pitan76.mcpitanlib.api.util.client.ClientUtil;
import org.jetbrains.annotations.Nullable;
import org.joml.Matrix4f;

public class WorldRenderContextImpl implements WorldRenderContext {

    public WorldRenderer worldRenderer;
    public MatrixStack matrixStack;
    public float tickDelta;
    public Camera camera;
    public GameRenderer gameRenderer;
    public LightmapTextureManager lightmapTextureManager;
    public Matrix4f projectionMatrix;
    public ClientWorld world;
    public boolean advancedTranslucency;
    public @Nullable VertexConsumerProvider consumers;
    public @Nullable Frustum frustum;

    @Override
    public WorldRenderer getWorldRenderer() {
        return worldRenderer;
    }

    @Override
    public MatrixStack getMatrixStack() {
        return matrixStack;
    }

    @Override
    public float getTickDelta() {
        return tickDelta;
    }

    @Override
    public Camera getCamera() {
        if (camera == null) {
            return getGameRenderer().getCamera();
        }
        return camera;
    }

    @Override
    public GameRenderer getGameRenderer() {
        if (gameRenderer == null) {
            return ClientUtil.getGameRenderer();
        }
        return gameRenderer;
    }

    @Override
    public LightmapTextureManager getLightmapTextureManager() {
        return lightmapTextureManager;
    }

    @Override
    public Matrix4f getProjectionMatrix() {
        return projectionMatrix;
    }

    @Override
    public ClientWorld getWorld() {
        if (world == null) {
            return ClientUtil.getWorld();
        }
        return world;
    }

    @Override
    public boolean isAdvancedTranslucency() {
        return advancedTranslucency;
    }

    @Override
    public @Nullable VertexConsumerProvider getConsumers() {
        return consumers;
    }

    @Override
    public @Nullable Frustum getFrustum() {
        return frustum;
    }

    public void prepare(GameRenderer gameRenderer, WorldRenderer worldRenderer, WorldRenderState worldRenderState, @Nullable ClientWorld world, RenderTickCounter tickCounter, boolean renderBlockOutline, Camera camera, Matrix4f positionMatrix, Matrix4f matrix4f, Matrix4f projectionMatrix) {
        this.gameRenderer = gameRenderer;
        this.worldRenderer = worldRenderer;
        this.world = world;
        this.camera = camera;
        this.matrixStack = new MatrixStack();
        this.projectionMatrix = projectionMatrix;
        this.tickDelta = tickCounter.getDynamicDeltaTicks();
        this.lightmapTextureManager = gameRenderer.getLightmapTextureManager();
        this.frustum = worldRenderer.getCapturedFrustum();
    }
}
