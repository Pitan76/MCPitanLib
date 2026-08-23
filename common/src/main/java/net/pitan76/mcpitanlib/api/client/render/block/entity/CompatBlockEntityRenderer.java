package net.pitan76.mcpitanlib.api.client.render.block.entity;

import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.state.BlockEntityRenderState;
import net.minecraft.client.renderer.SubmitNodeCollector;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.pitan76.mcpitanlib.midohra.util.math.Box;
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

    /**
     * カリングに使う描画範囲。BlockEntityより大きい範囲を描画するときに実際の範囲を返す。
     * NeoForge専用 (バニラ/Fabricは描画範囲でカリングしない)。
     * @return nullならrendersOutsideBoundingBoxOverrideに従う
     */
    default Box getRenderBoundingBoxOverride(T blockEntity) {
        return null;
    }

    // NeoForgeのIBlockEntityRendererExtension#getRenderBoundingBoxを実行時にオーバーライドする。
    // 引数がTでなく生BlockEntityなのは、消去後のシグネチャを一致させないとオーバーライドにならないため。
    @SuppressWarnings("unchecked")
    default AABB getRenderBoundingBox(BlockEntity blockEntity) {
        Box box = getRenderBoundingBoxOverride((T) blockEntity);
        if (box != null) return box.toMinecraft();

        if (rendersOutsideBoundingBoxOverride((T) blockEntity)) return INFINITE_RENDER_BOX;

        return new AABB(blockEntity.getBlockPos());
    }

    // カリングを実質無効にする巨大なAABB
    AABB INFINITE_RENDER_BOX = new AABB(-3.0E7D, -3.0E7D, -3.0E7D, 3.0E7D, 3.0E7D, 3.0E7D);

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
