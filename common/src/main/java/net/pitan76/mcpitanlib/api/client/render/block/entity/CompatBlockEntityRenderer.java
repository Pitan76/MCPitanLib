package net.pitan76.mcpitanlib.api.client.render.block.entity;

import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderDispatcher;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.pitan76.mcpitanlib.api.client.render.block.entity.event.BlockEntityRenderEvent;
import net.pitan76.mcpitanlib.api.tile.CompatBlockEntity;

// TODO: 1.16.5ではextendsで1.18ではimplementsに変更されているため、ラッパーを作成する必要がある
@Deprecated
public abstract class CompatBlockEntityRenderer<T extends CompatBlockEntity> extends BlockEntityRenderer<T> {

    public CompatBlockEntityRenderer(BlockEntityRenderDispatcher dispatcher) {
        super(dispatcher);
    }

    public abstract void render(BlockEntityRenderEvent<T> event);

    @Override
    public void render(T entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        render(new BlockEntityRenderEvent<>(this, entity, tickDelta, matrices, vertexConsumers, light, overlay));
    }

    public boolean rendersOutsideBoundingBoxOverride(T blockEntity) {
        return super.rendersOutsideBoundingBox(blockEntity);
    }

    public int getRenderDistanceOverride() {
        return 64;
    }

    @Deprecated
    @Override
    public boolean rendersOutsideBoundingBox(T blockEntity) {
        return rendersOutsideBoundingBoxOverride(blockEntity);
    }

}
