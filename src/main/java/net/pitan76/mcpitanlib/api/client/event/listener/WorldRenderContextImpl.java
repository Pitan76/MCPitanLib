package net.pitan76.mcpitanlib.api.client.event.listener;

import net.minecraft.client.Camera;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.culling.Frustum;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.multiplayer.ClientLevel;
import com.mojang.math.Axis;
import net.minecraft.world.phys.Vec3;
import net.pitan76.mcpitanlib.api.util.client.ClientUtil;
import org.jetbrains.annotations.Nullable;
import org.joml.Matrix4f;

public class WorldRenderContextImpl implements WorldRenderContext {

    public LevelRenderer worldRenderer;
    public PoseStack matrixStack;
    public float tickDelta;
    public Camera camera;
    public GameRenderer gameRenderer;
//    public LightTexture lightmapTextureManager;
    public Matrix4f projectionMatrix;
    public ClientLevel world;
    public boolean advancedTranslucency;
    public @Nullable MultiBufferSource consumers;
    public @Nullable Frustum frustum;

    @Override
    public LevelRenderer getWorldRenderer() {
        return worldRenderer;
    }

    @Override
    public PoseStack getMatrixStack() {
        return matrixStack;
    }

    @Override
    public float getTickDelta() {
        return tickDelta;
    }

    @Override
    public Camera getCamera() {
        if (camera == null) {
            return getGameRenderer().getMainCamera();
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

//    @Override
//    public LightTexture getLightmapTextureManager() {
//        return lightmapTextureManager;
//    }

    @Override
    public Matrix4f getProjectionMatrix() {
        return projectionMatrix;
    }

    @Override
    public ClientLevel getWorld() {
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
    public @Nullable MultiBufferSource getConsumers() {
        return consumers;
    }

    @Override
    public @Nullable Frustum getFrustum() {
        return frustum;
    }

    public void prepare(GameRenderer gameRenderer, LevelRenderer worldRenderer, @Nullable ClientLevel world, DeltaTracker tickCounter, boolean renderBlockOutline, Camera camera, Matrix4f positionMatrix, Matrix4f matrix4f, Matrix4f projectionMatrix) {
        this.gameRenderer = gameRenderer;
        this.worldRenderer = worldRenderer;
        this.world = world;
        this.camera = camera;
        this.matrixStack = new PoseStack();
        this.matrixStack.mulPose(projectionMatrix);
        this.matrixStack.pushPose();

        matrixStack.mulPose(Axis.YP.rotationDegrees(-camera.yRot()));
        matrixStack.mulPose(Axis.XP.rotationDegrees(camera.xRot()));

        Vec3 camPos = camera.position();
        matrixStack.translate(-camPos.x, -camPos.y, -camPos.z);

        this.matrixStack.popPose();

        this.projectionMatrix = projectionMatrix;;
        this.tickDelta = tickCounter.getGameTimeDeltaTicks();
        this.frustum = gameRenderer.getMainCamera().getCapturedFrustum();
    }
}
