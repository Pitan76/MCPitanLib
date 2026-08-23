package net.pitan76.mcpitanlib.api.client.render.block.entity;

import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.state.BlockEntityRenderState;
import net.minecraft.client.render.command.OrderedRenderCommandQueue;
import net.minecraft.client.render.state.CameraRenderState;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.Vec3d;
import net.minecraft.block.entity.BlockEntity;
import net.pitan76.mcpitanlib.midohra.util.math.Box;
import net.pitan76.mcpitanlib.api.client.render.block.entity.event.BlockEntityRenderEvent;
import net.pitan76.mcpitanlib.api.tile.CompatBlockEntity;

@Deprecated
public interface CompatBlockEntityRenderer<T extends CompatBlockEntity, S extends BlockEntityRenderState> extends BlockEntityRenderer<T, S> {
    void render(BlockEntityRenderEvent<T> event);

    default void render(T entity, float tickProgress, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay, Vec3d cameraPos) {
        render(new BlockEntityRenderEvent<>(this, entity, tickProgress, matrices, vertexConsumers, light, overlay));
    }

    @Override
    default void render(S state, MatrixStack matrices, OrderedRenderCommandQueue queue, CameraRenderState cameraState) {
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
    default net.minecraft.util.math.Box getRenderBoundingBox(BlockEntity blockEntity) {
        Box box = getRenderBoundingBoxOverride((T) blockEntity);
        if (box != null) return box.toMinecraft();

        if (rendersOutsideBoundingBoxOverride((T) blockEntity)) return INFINITE_RENDER_BOX;

        return new net.minecraft.util.math.Box(blockEntity.getPos());
    }

    // カリングを実質無効にする巨大なAABB
    net.minecraft.util.math.Box INFINITE_RENDER_BOX = new net.minecraft.util.math.Box(-3.0E7D, -3.0E7D, -3.0E7D, 3.0E7D, 3.0E7D, 3.0E7D);

    default boolean rendersOutsideBoundingBoxOverride(T blockEntity) {
        return BlockEntityRenderer.super.rendersOutsideBoundingBox();
    }

    default int getRenderDistanceOverride() {
        return BlockEntityRenderer.super.getRenderDistance();
    }

    @Deprecated
    @Override
    default boolean rendersOutsideBoundingBox() {
        return rendersOutsideBoundingBoxOverride(null);
    }

    @Deprecated
    @Override
    default int getRenderDistance() {
        return getRenderDistanceOverride();
    }
}
