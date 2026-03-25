package net.pitan76.mcpitanlib.api.client.render.block.entity.event;

import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.chunk.ChunkSectionLayer;
import net.minecraft.client.renderer.rendertype.RenderType;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.state.BlockEntityRenderState;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.state.CameraRenderState;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.world.item.ItemStack;
import net.minecraft.core.BlockPos;
import net.pitan76.mcpitanlib.api.client.registry.CompatRegistryClient2;
import net.pitan76.mcpitanlib.api.client.render.CompatRenderLayer;
import net.pitan76.mcpitanlib.api.client.render.DrawObjectMV;
import net.pitan76.mcpitanlib.api.client.render.block.entity.CompatBlockEntityRenderer;
import net.pitan76.mcpitanlib.api.tile.CompatBlockEntity;
import net.pitan76.mcpitanlib.api.util.MathUtil;
import net.pitan76.mcpitanlib.api.util.client.ClientUtil;
import net.pitan76.mcpitanlib.api.util.client.MatrixStackUtil;
import net.pitan76.mcpitanlib.api.util.client.render.CompatItemRenderUtil;
import org.joml.Matrix3f;
import org.joml.Matrix4f;

public class BlockEntityRenderEvent<T extends CompatBlockEntity> {
    public T blockEntity;
    public float tickDelta;
    public PoseStack matrices;
    public MultiBufferSource vertexConsumers;
    int light;
    int overlay;

    public BlockEntityRenderEvent(T blockEntity, float tickDelta, PoseStack matrices, MultiBufferSource vertexConsumers, int light, int overlay) {
        this.blockEntity = blockEntity;
        this.tickDelta = tickDelta;
        this.matrices = matrices;
        this.vertexConsumers = vertexConsumers;
        this.light = light;
        this.overlay = overlay;
    }

    private BlockEntityRenderState state;
    private SubmitNodeCollector queue;
    private CameraRenderState cameraState;


    public <S extends BlockEntityRenderState> BlockEntityRenderEvent(S state, PoseStack matrices, SubmitNodeCollector queue, CameraRenderState cameraState) {
        this.state = state;
        this.queue = queue;
        this.cameraState = cameraState;

        this.matrices = matrices;
        this.queue = queue;
        this.cameraState = cameraState;
        this.tickDelta = Minecraft.getInstance().getDeltaTracker().getGameTimeDeltaTicks();
        BlockEntity blockEntity = state.blockEntityType.getBlockEntity(Minecraft.getInstance().level, state.blockPos);
        if (blockEntity instanceof CompatBlockEntity) {
            this.blockEntity = (T) blockEntity;
        } else {
            //throw new IllegalArgumentException("BlockEntityRenderEvent: BlockEntity is not an instance of CompatBlockEntity");
        }

        this.vertexConsumers = Minecraft.getInstance().renderBuffers().bufferSource();
        this.light = state.lightCoords;
        if (state.breakProgress != null)
            this.overlay = state.breakProgress.progress();
        else
            this.overlay = 0;
    }

    public T getBlockEntity() {
        return blockEntity;
    }

    public PoseStack getMatrices() {
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

    public VertexConsumer getVertexConsumer(RenderType layer) {
        return vertexConsumers.getBuffer(layer);
    }

    public VertexConsumer getVertexConsumer(CompatRenderLayer layer) {
        return getVertexConsumer(layer.raw());
    }

    public MultiBufferSource getVertexConsumers() {
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
            matrix4f = matrices.last().pose();

        return matrix4f;
    }

    public Matrix3f getMatrix3f() {
        if (matrix3f == null)
            matrix3f = matrices.last().normal();

        return matrix3f;
    }

    public BlockPos getPos() {
        return state.blockPos;
    }

    public net.pitan76.mcpitanlib.midohra.util.math.BlockPos getMidohraPos() {
        return net.pitan76.mcpitanlib.midohra.util.math.BlockPos.of(getPos());
    }

    //----

    @Deprecated
    public CompatRegistryClient2.BlockEntityRendererFactory.Context ctx;

    public BlockEntityRenderEvent(CompatBlockEntityRenderer renderer, T blockEntity, float tickDelta, PoseStack matrices, MultiBufferSource vertexConsumers, int light, int overlay) {
        this(blockEntity, tickDelta, matrices, vertexConsumers, light, overlay);
        if (renderer instanceof net.pitan76.mcpitanlib.api.client.render.block.entity.v2.CompatBlockEntityRenderer<?>) {
            this.ctx = ((net.pitan76.mcpitanlib.api.client.render.block.entity.v2.CompatBlockEntityRenderer<?>) renderer).ctx;
        }
    }

    public BlockEntityRenderEvent(CompatBlockEntityRenderer renderer, BlockEntityRenderState state, PoseStack matrices, SubmitNodeCollector queue, CameraRenderState cameraState) {
        this(state, matrices, queue, cameraState);
        if (renderer instanceof net.pitan76.mcpitanlib.api.client.render.block.entity.v2.CompatBlockEntityRenderer<?>) {
            this.ctx = ((net.pitan76.mcpitanlib.api.client.render.block.entity.v2.CompatBlockEntityRenderer<?>) renderer).ctx;
        }
    }

    public void renderItemFixed(ItemStack stack) {
        CompatItemRenderUtil.renderItemFixed(stack, this, blockEntity.callGetWorld());
    }

    @Deprecated
    public SubmitNodeCollector getQueue() {
        return queue;
    }

    @Deprecated
    public BlockEntityRenderState getState() {
        return state;
    }

    @Deprecated
    public CameraRenderState getCameraState() {
        return cameraState;
    }
}
