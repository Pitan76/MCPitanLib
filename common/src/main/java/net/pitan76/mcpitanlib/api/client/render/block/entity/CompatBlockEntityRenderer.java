package net.pitan76.mcpitanlib.api.client.render.block.entity;

import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.state.BlockEntityRenderState;
import net.minecraft.client.render.command.OrderedRenderCommandQueue;
import net.minecraft.client.render.state.CameraRenderState;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.Vec3d;
import net.pitan76.mcpitanlib.api.client.render.block.entity.event.BlockEntityRenderEvent;
import net.pitan76.mcpitanlib.api.tile.CompatBlockEntity;

@Deprecated
public interface CompatBlockEntityRenderer<T extends CompatBlockEntity, S extends BlockEntityRenderState> extends BlockEntityRenderer<T, S> {
    void render(BlockEntityRenderEvent<T> event);

    default void render(T entity, float tickProgress, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay, Vec3d cameraPos) {
        render(new BlockEntityRenderEvent<>(this, entity, tickProgress, matrices, vertexConsumers, light, overlay));
    }

    @Override
    default void render(S state, MatrixStack matrices, OrderedRenderCommandQueue queue, CameraRenderState cameraState) {
        render(new BlockEntityRenderEvent<>(this, state, matrices, queue, cameraState));
    }

    default boolean rendersOutsideBoundingBoxOverride(T blockEntity) {
        return BlockEntityRenderer.super.rendersOutsideBoundingBox();
    }

    default int getRenderDistanceOverride() {
        return BlockEntityRenderer.super.getRenderDistance();
    }

    @Deprecated
    @Override
    default boolean rendersOutsideBoundingBox() {
        return rendersOutsideBoundingBoxOverride(null);
    }

    @Deprecated
    @Override
    default int getRenderDistance() {
        return getRenderDistanceOverride();
    }
}
