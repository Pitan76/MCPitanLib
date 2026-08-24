package net.pitan76.mcpitanlib.api.client.event.listener;

import com.mojang.blaze3d.vertex.VertexConsumer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Camera;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.culling.Frustum;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.client.Minecraft;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.pitan76.mcpitanlib.api.client.render.CompatMatrixStack;
import net.pitan76.mcpitanlib.api.util.VoxelShapeUtil;
import net.pitan76.mcpitanlib.api.util.client.render.VertexRenderingUtil;
import net.pitan76.mcpitanlib.midohra.client.render.CameraWrapper;
import org.jetbrains.annotations.Nullable;
import org.joml.Matrix4f;

import java.util.Objects;
import java.util.Optional;

public interface WorldRenderContext {

    LevelRenderer getWorldRenderer();

    PoseStack getMatrixStack();

    float getTickDelta();

    Camera getCamera();

    GameRenderer getGameRenderer();

//    LightTexture getLightmapTextureManager();

    @Deprecated
    Matrix4f getProjectionMatrix();

    ClientLevel getWorld();

    @Deprecated
    boolean isAdvancedTranslucency();

    @Nullable MultiBufferSource getConsumers();
    @Nullable Frustum getFrustum();

    @Environment(EnvType.CLIENT)
    interface BlockOutlineContext {
        @Deprecated
        VertexConsumer vertexConsumer();

        Entity entity();

        double cameraX();

        double cameraY();

        double cameraZ();

        BlockPos blockPos();

        BlockState blockState();
    }

    default HitResult getHitResult() {
        return Minecraft.getInstance().hitResult;
    }

    default Optional<BlockState> getBlockState() {
        return Optional.ofNullable(getWorld().getBlockState(getBlockPos().orElse(null)));
    }

    default Optional<BlockPos> getBlockPos() {
        return Optional.ofNullable(((BlockHitResult) getHitResult()).getBlockPos());
    }

    default boolean isBlockType() {
        return getHitResultType() == HitResult.Type.BLOCK;
    }

    default HitResult.Type getHitResultType() {
        return getHitResult().getType();
    }

    default Optional<VoxelShape> getOutlineShape() {
        return getBlockState().map(blockState -> blockState.getShape(getWorld(),
                getBlockPos().orElse(null)));
    }

    default void push() {
        getMatrixStack().pushPose();
    }

    default void translate(double x, double y, double z) {
        getMatrixStack().translate(x, y, z);
    }

    default void pop() {
        getMatrixStack().popPose();
    }

    default CompatMatrixStack getCompatMatrices() {
        return CompatMatrixStack.of(getMatrixStack());
    }

    default Optional<VertexConsumer> getVertexConsumer() {
        MultiBufferSource consumers =
                getConsumers() != null ? getConsumers() : Minecraft.getInstance().renderBuffers().bufferSource();

        if (consumers == null)
            return Optional.empty();

        return Optional.of(consumers.getBuffer(RenderTypes.lines()));
    }

    default void drawBox(float red, float green, float blue, float alpha) {
        Optional<VoxelShape> outlineShape = getOutlineShape();
        if (!outlineShape.isPresent()) return;

        drawBox(VoxelShapeUtil.getBoundingBox(outlineShape.get()), red, green, blue, alpha);
    }

    default void drawBox(AABB box, float red, float green, float blue, float alpha) {
        if (box == null) return;
        drawBox(box.minX, box.minY, box.minZ, box.maxX, box.maxY, box.maxZ, red, green, blue, alpha);
    }

    default void drawBox(net.pitan76.mcpitanlib.midohra.util.math.Box box, float red, float green, float blue, float alpha) {
        if (box == null) return;
        drawBox(box.toMinecraft(), red, green, blue, alpha);
    }

    default void drawBox(double minX, double minY, double minZ, double maxX, double maxY, double maxZ, float red, float green, float blue, float alpha) {
        Optional<VertexConsumer> vertexConsumer = getVertexConsumer();

        if (!vertexConsumer.isPresent())
            return;

        VertexConsumer consumer = vertexConsumer.get();
        VertexRenderingUtil.drawBox(getMatrixStack(), consumer, minX, minY, minZ, maxX, maxY, maxZ, red, green, blue, alpha);
    }

    default CameraWrapper getCameraWrapper() {
        return CameraWrapper.of(getCamera());
    }
}
