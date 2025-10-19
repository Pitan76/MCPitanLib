package net.pitan76.mcpitanlib.api.client.render.block.entity.event;

import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.BlockRenderLayer;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.state.BlockEntityRenderState;
import net.minecraft.client.render.command.OrderedRenderCommandQueue;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.state.CameraRenderState;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.BlockPos;
import net.pitan76.mcpitanlib.api.client.render.CompatRenderLayer;
import net.pitan76.mcpitanlib.api.client.render.DrawObjectMV;
import net.pitan76.mcpitanlib.api.tile.CompatBlockEntity;
import net.pitan76.mcpitanlib.api.util.MathUtil;
import net.pitan76.mcpitanlib.api.util.client.ClientUtil;
import net.pitan76.mcpitanlib.api.util.client.MatrixStackUtil;
import org.joml.Matrix3f;
import org.joml.Matrix4f;

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

    private BlockEntityRenderState state;
    private OrderedRenderCommandQueue queue;
    private CameraRenderState cameraState;


    public <S extends BlockEntityRenderState> BlockEntityRenderEvent(S state, MatrixStack matrices, OrderedRenderCommandQueue queue, CameraRenderState cameraState) {
        this.state = state;
        this.queue = queue;
        this.cameraState = cameraState;

        this.matrices = matrices;
        this.queue = queue;
        this.cameraState = cameraState;
        this.tickDelta = MinecraftClient.getInstance().getRenderTickCounter().getDynamicDeltaTicks();
        BlockEntity blockEntity = state.type.get(MinecraftClient.getInstance().world, state.pos);
        if (blockEntity instanceof CompatBlockEntity) {
            this.blockEntity = (T) blockEntity;
        } else {
            throw new IllegalArgumentException("BlockEntityRenderEvent: BlockEntity is not an instance of CompatBlockEntity");
        }

        this.vertexConsumers = MinecraftClient.getInstance().getBufferBuilders().getEntityVertexConsumers();
        this.light = state.lightmapCoordinates;
        if (state.crumblingOverlay != null)
            this.overlay = state.crumblingOverlay.progress();
        else
            this.overlay = 0;
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
            matrix4f = matrices.peek().getPositionMatrix();

        return matrix4f;
    }

    public Matrix3f getMatrix3f() {
        if (matrix3f == null)
            matrix3f = matrices.peek().getNormalMatrix();

        return matrix3f;
    }

    public BlockPos getPos() {
        return state.pos;
    }

    public net.pitan76.mcpitanlib.midohra.util.math.BlockPos getMidohraPos() {
        return net.pitan76.mcpitanlib.midohra.util.math.BlockPos.of(getPos());
    }
}
