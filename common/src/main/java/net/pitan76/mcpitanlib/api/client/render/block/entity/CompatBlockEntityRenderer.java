package net.pitan76.mcpitanlib.api.client.render.block.entity;

import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.state.BlockEntityRenderState;
import net.minecraft.client.renderer.SubmitNodeCollector;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.world.phys.Vec3;
import net.pitan76.mcpitanlib.api.client.render.block.entity.event.BlockEntityRenderEvent;
import net.pitan76.mcpitanlib.api.tile.CompatBlockEntity;

@Deprecated
public interface CompatBlockEntityRenderer<T extends CompatBlockEntity, S extends BlockEntityRenderState> extends BlockEntityRenderer<T, S> {
    void render(BlockEntityRenderEvent<T> event);

    default void render(T entity, float tickProgress, PoseStack matrices, VertexConsumer vertexConsumer, int light, int overlay, Vec3 cameraPos) {
        render(new BlockEntityRenderEvent<>(this, entity, tickProgress, matrices, vertexConsumer, light, overlay));
    }

    @Override
    default void submit(S state, PoseStack matrices, SubmitNodeCollector queue, CameraRenderState cameraState) {
        render(new BlockEntityRenderEvent<>(this, state, matrices, null, queue, cameraState));
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
