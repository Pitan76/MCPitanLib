package net.pitan76.mcpitanlib.api.client.render.block.entity.event;

import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Matrix3f;
import net.minecraft.util.math.Matrix4f;
import net.pitan76.mcpitanlib.api.client.registry.CompatRegistryClient;
import net.pitan76.mcpitanlib.api.client.render.CompatRenderLayer;
import net.pitan76.mcpitanlib.api.client.render.DrawObjectMV;
import net.pitan76.mcpitanlib.api.client.render.block.entity.CompatBlockEntityRenderer;
import net.pitan76.mcpitanlib.api.tile.CompatBlockEntity;
import net.pitan76.mcpitanlib.api.util.MathUtil;
import net.pitan76.mcpitanlib.api.util.client.ClientUtil;
import net.pitan76.mcpitanlib.api.util.client.MatrixStackUtil;
import net.pitan76.mcpitanlib.api.util.client.render.CompatItemRenderUtil;

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

    public VertexConsumer getVertexConsumer(CompatRenderLayer layer) {
        return getVertexConsumer(layer.raw());
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

    public DrawObjectMV getDrawObject(CompatRenderLayer layer) {
        return new DrawObjectMV(getMatrices(), getVertexConsumer(layer));
    }

    public Matrix4f matrix4f;
    public Matrix3f matrix3f;

    public Matrix4f getMatrix4f() {
        if (matrix4f == null)
            matrix4f = matrices.peek().getModel();

        return matrix4f;
    }

    public Matrix3f getMatrix3f() {
        if (matrix3f == null)
            matrix3f = matrices.peek().getNormal();

        return matrix3f;
    }

    public BlockPos getPos() {
        return blockEntity.callGetPos();
    }

    public net.pitan76.mcpitanlib.midohra.util.math.BlockPos getMidohraPos() {
        return net.pitan76.mcpitanlib.midohra.util.math.BlockPos.of(getPos());
    }

    //----

    @Deprecated
    public CompatRegistryClient.BlockEntityRendererFactory.Context ctx;

    public BlockEntityRenderEvent(CompatBlockEntityRenderer renderer, T blockEntity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        this(blockEntity, tickDelta, matrices, vertexConsumers, light, overlay);
        if (renderer instanceof net.pitan76.mcpitanlib.api.client.render.block.entity.v2.CompatBlockEntityRenderer<?>) {
            this.ctx = ((net.pitan76.mcpitanlib.api.client.render.block.entity.v2.CompatBlockEntityRenderer<?>) renderer).ctx;
        }
    }

    public void renderItemFixed(ItemStack stack) {
        CompatItemRenderUtil.renderItemFixed(stack, this, blockEntity.callGetWorld());
    }
}
