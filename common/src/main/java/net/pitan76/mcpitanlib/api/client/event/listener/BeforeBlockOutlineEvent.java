package net.pitan76.mcpitanlib.api.client.event.listener;

import net.minecraft.block.BlockState;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.World;

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
        return context.getWorldRenderer();
    }

    public Optional<BlockState> getBlockState() {
        return Optional.ofNullable(getWorld().getBlockState(getBlockPos().orElse(null)));
    }

    public World getWorld() {
        return context.getWorld();
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
        return context.getCamera();
    }

    public Optional<VoxelShape> getOutlineShape() {
        return context.getOutlineShape();
    }

    public MatrixStack getMatrixStack() {
        return context.getMatrixStack();
    }

    public void push() {
        context.push();
    }

    public void translate(double x, double y, double z) {
        context.translate(x, y, z);
    }

    public void pop() {
        context.pop();
    }

    public Optional<VertexConsumer> getVertexConsumer() {
        return context.getVertexConsumer();
    }

    public void drawBox(float red, float green, float blue, float alpha) {
        context.drawBox(red, green, blue, alpha);
    }

    public void drawBox(Box box, float red, float green, float blue, float alpha) {
        context.drawBox(box, red, green, blue, alpha);
    }
}
