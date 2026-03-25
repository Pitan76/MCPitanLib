package net.pitan76.mcpitanlib.api.client.render.block.entity;

import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.state.BlockEntityRenderState;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.state.CameraRenderState;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.world.phys.Vec3;
import net.pitan76.mcpitanlib.api.client.render.block.entity.event.BlockEntityRenderEvent;
import net.pitan76.mcpitanlib.api.tile.CompatBlockEntity;

@Deprecated
public interface CompatBlockEntityRenderer<T extends CompatBlockEntity, S extends BlockEntityRenderState> extends BlockEntityRenderer<T, S> {
    void render(BlockEntityRenderEvent<T> event);

    default void render(T entity, float tickProgress, PoseStack matrices, MultiBufferSource vertexConsumers, int light, int overlay, Vec3 cameraPos) {
        render(new BlockEntityRenderEvent<>(this, entity, tickProgress, matrices, vertexConsumers, light, overlay));
    }

    @Override
    default void submit(S state, PoseStack matrices, SubmitNodeCollector queue, CameraRenderState cameraState) {
        render(new BlockEntityRenderEvent<>(this, state, matrices, queue, cameraState));
    }

    default boolean rendersOutsideBoundingBoxOverride(T blockEntity) {
        return BlockEntityRenderer.super.shouldRenderOffScreen();
    }

    default int getRenderDistanceOverride() {
        return BlockEntityRenderer.super.getViewDistance();
    }

    @Deprecated
    @Override
    default boolean shouldRenderOffScreen() {
        return rendersOutsideBoundingBoxOverride(null);
    }

    @Deprecated
    @Override
    default int getViewDistance() {
        return getRenderDistanceOverride();
    }
}
