package net.pitan76.mcpitanlib.api.client.event.listener;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.*;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.profiler.Profiler;
import net.minecraft.util.shape.VoxelShape;
import net.pitan76.mcpitanlib.api.util.VoxelShapeUtil;
import org.jetbrains.annotations.Nullable;
import org.joml.Matrix4f;

import java.util.Objects;
import java.util.Optional;

public interface WorldRenderContext {

    WorldRenderer getWorldRenderer();

    MatrixStack getMatrixStack();

    float getTickDelta();

    Camera getCamera();

    GameRenderer getGameRenderer();

    LightmapTextureManager getLightmapTextureManager();

    @Deprecated
    Matrix4f getProjectionMatrix();

    ClientWorld getWorld();

    Profiler getProfiler();

    @Deprecated
    boolean isAdvancedTranslucency();

    @Nullable VertexConsumerProvider getConsumers();
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
        return MinecraftClient.getInstance().crosshairTarget;
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
        return getBlockState().map(blockState -> blockState.getOutlineShape(getWorld(),
                getBlockPos().orElse(null)));
    }

    default void push() {
        getMatrixStack().push();
    }

    default void translate(double x, double y, double z) {
        getMatrixStack().translate(x, y, z);
    }

    default void pop() {
        getMatrixStack().pop();
    }

    default Optional<VertexConsumer> getVertexConsumer() {
        if (getConsumers() == null)
            return Optional.empty();

        return Optional.of(Objects.requireNonNull(getConsumers()).getBuffer(RenderLayer.getLines()));
    }

    default void drawBox(float red, float green, float blue, float alpha) {
        Optional<VoxelShape> outlineShape = getOutlineShape();
        if (!outlineShape.isPresent()) return;

        drawBox(VoxelShapeUtil.getBoundingBox(outlineShape.get()), red, green, blue, alpha);
    }

    default void drawBox(Box box, float red, float green, float blue, float alpha) {
        Optional<VertexConsumer> vertexConsumer = getVertexConsumer();

        if (!vertexConsumer.isPresent())
            return;

        WorldRenderer.drawBox(getMatrixStack(), vertexConsumer.get(), box, red, green, blue, alpha);
    }
}
