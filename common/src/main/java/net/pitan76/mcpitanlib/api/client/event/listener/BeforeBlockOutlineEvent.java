package net.pitan76.mcpitanlib.api.client.event.listener;

import net.minecraft.block.BlockState;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.World;
import net.pitan76.mcpitanlib.api.util.VoxelShapeUtil;

import java.util.Objects;
import java.util.Optional;

public class BeforeBlockOutlineEvent {
    public WorldRenderContext context;
    public HitResult hitResult;

    public BeforeBlockOutlineEvent(WorldRenderContext context, HitResult hitResult) {
        this.context = context;
        this.hitResult = hitResult;
    }

    public HitResult getHitResult() {
        return hitResult;
    }

    public WorldRenderContext getContext() {
        return context;
    }

    public WorldRenderer getWorldRenderer() {
        return context.worldRenderer();
    }

    public Optional<BlockState> getBlockState() {
        return Optional.ofNullable(getWorld().getBlockState(getBlockPos().orElse(null)));
    }

    public World getWorld() {
        return context.world();
    }

    public Optional<BlockPos> getBlockPos() {
        return Optional.ofNullable(((BlockHitResult) hitResult).getBlockPos());
    }

    public boolean isBlockType() {
        return getHitResultType() == HitResult.Type.BLOCK;
    }

    public HitResult.Type getHitResultType() {
        return hitResult.getType();
    }

    public Camera getCamera() {
        return context.camera();
    }

    public Optional<VoxelShape> getOutlineShape() {
        return getBlockState().map(blockState -> blockState.getOutlineShape(getWorld(),
                getBlockPos().orElse(null)));
    }

    public MatrixStack getMatrixStack() {
        return context.matrixStack();
    }

    public void push() {
        getMatrixStack().push();
    }

    public void translate(double x, double y, double z) {
        getMatrixStack().translate(x, y, z);
    }

    public void pop() {
        getMatrixStack().pop();
    }

    public Optional<VertexConsumer> getVertexConsumer() {
        if (context.consumers() == null)
            return Optional.empty();

        return Optional.of(Objects.requireNonNull(context.consumers()).getBuffer(RenderLayer.getLines()));
    }

    public void drawBox(float red, float green, float blue, float alpha) {
        Optional<VoxelShape> outlineShape = getOutlineShape();
        if (!outlineShape.isPresent()) return;

        drawBox(VoxelShapeUtil.getBoundingBox(outlineShape.get()), red, green, blue, alpha);
    }

    public void drawBox(Box box, float red, float green, float blue, float alpha) {
        Optional<VertexConsumer> vertexConsumer = getVertexConsumer();

        if (!vertexConsumer.isPresent())
            return;

        WorldRenderer.drawBox(getMatrixStack(), vertexConsumer.get(), box, red, green, blue, alpha);
    }
}
