package net.pitan76.mcpitanlib.api.client.event.listener;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.client.Camera;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.client.renderer.state.BlockOutlineRenderState;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.level.Level;
import net.pitan76.mcpitanlib.midohra.client.render.CameraWrapper;
import net.pitan76.mcpitanlib.midohra.util.hit.HitResultType;

import java.util.Optional;

public class BeforeBlockOutlineEvent {
    public WorldRenderContext context;
    public HitResult hitResult;

    public BeforeBlockOutlineEvent(WorldRenderContext context, BlockOutlineRenderState renderState) {
        this.context = context;
        this.hitResult = context.getHitResult();
    }

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

    public LevelRenderer getWorldRenderer() {
        return context.getWorldRenderer();
    }

    public Optional<BlockState> getBlockState() {
        return Optional.ofNullable(getWorld().getBlockState(getBlockPos().orElse(null)));
    }

    public Level getWorld() {
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

    @Deprecated
    public Camera getCamera() {
        return context.getCamera();
    }

    public CameraWrapper getCameraWrapper() {
        return CameraWrapper.of(getCamera());
    }

    public Optional<VoxelShape> getOutlineShape() {
        return context.getOutlineShape();
    }

    public PoseStack getMatrixStack() {
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

    public void drawBox(AABB box, float red, float green, float blue, float alpha) {
        context.drawBox(box, red, green, blue, alpha);
    }

    public net.pitan76.mcpitanlib.midohra.util.hit.HitResult getHitResultM() {
        return net.pitan76.mcpitanlib.midohra.util.hit.HitResult.of(hitResult);
    }

    public HitResultType getHitResultTypeM() {
        return HitResultType.from(hitResult.getType());
    }

    public BlockState getBlockState2() {
        return getWorld().getBlockState(getHitResultM().asBlockHitResult().get().getBlockPos());
    }

    public net.pitan76.mcpitanlib.midohra.block.BlockState getBlockStateM() {
        return net.pitan76.mcpitanlib.midohra.block.BlockState.of(getBlockState2());
    }
}
