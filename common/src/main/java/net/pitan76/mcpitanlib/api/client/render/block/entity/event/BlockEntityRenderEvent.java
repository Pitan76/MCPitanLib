package net.pitan76.mcpitanlib.api.client.render.block.entity.event;

import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.pitan76.mcpitanlib.api.tile.CompatBlockEntity;
import net.pitan76.mcpitanlib.api.util.MathUtil;
import net.pitan76.mcpitanlib.api.util.client.ClientUtil;
import net.pitan76.mcpitanlib.api.util.client.MatrixStackUtil;

public class BlockEntityRenderEvent<T extends CompatBlockEntity> {
    public T blockEntity;
    public float tickDelta;
    public MatrixStack matrices;
    public VertexConsumerProvider vertexConsumers;
    int light;
    int overlay;

    public BlockEntityRenderEvent(T blockEntity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        this.blockEntity = blockEntity;
        this.tickDelta = tickDelta;
        this.matrices = matrices;
        this.vertexConsumers = vertexConsumers;
        this.light = light;
        this.overlay = overlay;
    }

    public T getBlockEntity() {
        return blockEntity;
    }

    public MatrixStack getMatrices() {
        return matrices;
    }

    public float getTickDelta() {
        return tickDelta;
    }

    public int getLight() {
        return light;
    }

    public int getOverlay() {
        return overlay;
    }

    public VertexConsumer getVertexConsumer(RenderLayer layer) {
        return vertexConsumers.getBuffer(layer);
    }

    public VertexConsumerProvider getVertexConsumers() {
        return vertexConsumers;
    }

    public void push() {
        MatrixStackUtil.push(matrices);
    }

    public void translate(double x, double y, double z) {
        MatrixStackUtil.translate(matrices, x, y, z);
    }

    public void pop() {
        MatrixStackUtil.pop(matrices);
    }

    public void multiply(MathUtil.RotationAxisType type, float deg) {
        MatrixStackUtil.multiply(matrices, type, deg);
    }

    public void scale(float x, float y, float z) {
        MatrixStackUtil.scale(matrices, x, y, z);
    }

    public ItemRenderer getItemRenderer() {
        return ClientUtil.getItemRenderer();
    }

    public boolean isRemoved() {
        return blockEntity.isRemoved();
    }
}
