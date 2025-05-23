package net.pitan76.mcpitanlib.api.client.render.block.entity;

import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderDispatcher;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.pitan76.mcpitanlib.api.client.render.block.entity.event.BlockEntityRenderEvent;
import net.pitan76.mcpitanlib.api.tile.CompatBlockEntity;

@Deprecated
public abstract class CompatBlockEntityRenderer<T extends CompatBlockEntity> extends BlockEntityRenderer<T> {

    public CompatBlockEntityRenderer(BlockEntityRenderDispatcher dispatcher) {
        super(dispatcher);
    }

    abstract void render(BlockEntityRenderEvent<T> event);

    @Override
    public void render(T entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        render(new BlockEntityRenderEvent<>(entity, tickDelta, matrices, vertexConsumers, light, overlay));
    }

    default boolean rendersOutsideBoundingBoxOverride(T blockEntity) {
        return BlockEntityRenderer.super.rendersOutsideBoundingBox(blockEntity);
    }

    default int getRenderDistanceOverride() {
        return BlockEntityRenderer.super.getRenderDistance();
    }

    @Deprecated
    @Override
    default boolean rendersOutsideBoundingBox(T blockEntity) {
        return rendersOutsideBoundingBoxOverride(blockEntity);
    }

    @Deprecated
    @Override
    default int getRenderDistance() {
        return getRenderDistanceOverride();
    }
}
