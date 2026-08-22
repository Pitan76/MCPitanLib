package net.pitan76.mcpitanlib.api.client.render.block.entity.event;

import net.minecraft.client.renderer.item.ItemModelResolver;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.rendertype.RenderType;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.blockentity.state.BlockEntityRenderState;
import net.minecraft.client.renderer.SubmitNodeCollector;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.world.item.ItemStack;
import net.minecraft.core.BlockPos;
import net.pitan76.mcpitanlib.api.client.registry.CompatRegistryClient;
import net.pitan76.mcpitanlib.api.client.render.CompatMatrixStack;
import net.pitan76.mcpitanlib.api.client.render.CompatRenderLayer;
import net.pitan76.mcpitanlib.api.client.render.DrawObjectMV;
import net.pitan76.mcpitanlib.api.client.render.RecordingVertexConsumer;
import net.pitan76.mcpitanlib.api.client.render.block.entity.CompatBlockEntityRenderer;
import net.pitan76.mcpitanlib.api.tile.CompatBlockEntity;
import net.pitan76.mcpitanlib.api.util.MathUtil;
import net.pitan76.mcpitanlib.api.util.client.ClientUtil;
import net.pitan76.mcpitanlib.api.util.client.MatrixStackUtil;
import net.pitan76.mcpitanlib.api.util.client.render.CompatItemRenderUtil;
import org.joml.Matrix3f;
import org.joml.Matrix4f;

import java.util.HashMap;
import java.util.Map;

public class BlockEntityRenderEvent<T extends CompatBlockEntity> {
    public T blockEntity;
    public float tickDelta;
    public PoseStack matrices;
    public VertexConsumer vertexConsumers;
    int light;
    int overlay;

    public BlockEntityRenderEvent(T blockEntity, float tickDelta, PoseStack matrices, VertexConsumer vertexConsumers, int light, int overlay) {
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

    private final Map<RenderType, RecordingVertexConsumer> recordedLayers = new HashMap<>();

    public <S extends BlockEntityRenderState> BlockEntityRenderEvent(CompatBlockEntityRenderer renderer, S state, PoseStack matrices, VertexConsumer vertexConsumers, SubmitNodeCollector queue, CameraRenderState cameraState) {
        this(state, matrices, vertexConsumers, queue, cameraState);
        if (renderer instanceof net.pitan76.mcpitanlib.api.client.render.block.entity.v2.CompatBlockEntityRenderer<?>) {
            this.ctx = ((net.pitan76.mcpitanlib.api.client.render.block.entity.v2.CompatBlockEntityRenderer<?>) renderer).ctx;
        }
    }

    public <S extends BlockEntityRenderState> BlockEntityRenderEvent(S state, PoseStack matrices, VertexConsumer vertexConsumers, SubmitNodeCollector queue, CameraRenderState cameraState) {
        this.state = state;
        this.queue = queue;
        this.cameraState = cameraState;

        this.matrices = matrices;
        this.tickDelta = Minecraft.getInstance().getDeltaTracker().getGameTimeDeltaTicks();

        BlockEntity blockEntity = state.blockEntityType.getBlockEntity(Minecraft.getInstance().level, state.blockPos);
        if (blockEntity instanceof CompatBlockEntity) {
            this.blockEntity = (T) blockEntity;
        } else {
            //throw new IllegalArgumentException("BlockEntityRenderEvent: BlockEntity is not an instance of CompatBlockEntity");
        }

        // submitCustomGeometryのコールバック内から渡されるVertexConsumerをそのまま保持する。
        // 旧世代のMinecraft.getInstance().renderBuffers().bufferSource()相当の即時取得は
        // この世代では構造的に不可能なため廃止した。
        this.vertexConsumers = vertexConsumers;

        this.light = state.lightCoords;
        if (state.breakProgress != null)
            this.overlay = state.breakProgress.progress();
        else
            this.overlay = OverlayTexture.NO_OVERLAY;
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
        if (vertexConsumers != null) return vertexConsumers; // 旧世代、またはすでに取得済みのキャッシュ

        if (queue == null) {
            throw new IllegalStateException("No SubmitNodeCollector available to resolve VertexConsumer for " + layer);
        }

        RecordingVertexConsumer recorded = recordedLayers.get(layer);
        if (recorded != null) return recorded;

        RecordingVertexConsumer recorder = new RecordingVertexConsumer();
        recordedLayers.put(layer, recorder);

        queue.submitCustomGeometry(matrices, layer, (pose, vertexConsumer) -> recorder.replay(vertexConsumer));

        return recorder;
    }

    public VertexConsumer getVertexConsumer(CompatRenderLayer layer) {
        return getVertexConsumer(layer.raw());
    }

    public VertexConsumer getVertexConsumers() {
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

    public CompatMatrixStack getCompatMatrices() {
        return CompatMatrixStack.of(matrices);
    }

    public ItemModelResolver getItemRenderer() {
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
    public CompatRegistryClient.BlockEntityRendererFactory.Context ctx;

    public BlockEntityRenderEvent(CompatBlockEntityRenderer renderer, T blockEntity, float tickDelta, PoseStack matrices, VertexConsumer vertexConsumers, int light, int overlay) {
        this(blockEntity, tickDelta, matrices, vertexConsumers, light, overlay);
        if (renderer instanceof net.pitan76.mcpitanlib.api.client.render.block.entity.v2.CompatBlockEntityRenderer<?>) {
            this.ctx = ((net.pitan76.mcpitanlib.api.client.render.block.entity.v2.CompatBlockEntityRenderer<?>) renderer).ctx;
        }
    }

    @Deprecated
    public BlockEntityRenderEvent(CompatBlockEntityRenderer renderer, BlockEntityRenderState state, PoseStack matrices, SubmitNodeCollector queue, CameraRenderState cameraState) {
        this(state, matrices, null, queue, cameraState);
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
