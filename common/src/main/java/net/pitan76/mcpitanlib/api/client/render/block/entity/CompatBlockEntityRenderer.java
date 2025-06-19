package net.pitan76.mcpitanlib.api.client.render.block.entity;

import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.Vec3d;
import net.pitan76.mcpitanlib.api.client.render.block.entity.event.BlockEntityRenderEvent;
import net.pitan76.mcpitanlib.api.tile.CompatBlockEntity;

@Deprecated
public interface CompatBlockEntityRenderer<T extends CompatBlockEntity> extends BlockEntityRenderer<T> {
    void render(BlockEntityRenderEvent<T> event);

    @Override
    default void render(T entity, float tickProgress, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay, Vec3d cameraPos) {
        render(new BlockEntityRenderEvent<>(entity, tickProgress, matrices, vertexConsumers, light, overlay));
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
