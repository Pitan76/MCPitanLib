package net.pitan76.mcpitanlib.api.client.render.block.entity;

import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.block.entity.BlockEntity;
import net.pitan76.mcpitanlib.midohra.util.math.Box;
import net.pitan76.mcpitanlib.api.client.render.block.entity.event.BlockEntityRenderEvent;
import net.pitan76.mcpitanlib.api.tile.CompatBlockEntity;

@Deprecated
public interface CompatBlockEntityRenderer<T extends CompatBlockEntity> extends BlockEntityRenderer<T> {
    void render(BlockEntityRenderEvent<T> event);

    @Override
    default void render(T entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        render(new BlockEntityRenderEvent<>(this, entity, tickDelta, matrices, vertexConsumers, light, overlay));
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
        return BlockEntityRenderer.super.rendersOutsideBoundingBox(blockEntity);
    }

    default int getRenderDistanceOverride() {
        return BlockEntityRenderer.super.getRenderDistance();
    }

    @Deprecated
    @Override
    default boolean rendersOutsideBoundingBox(T blockEntity) {
        return rendersOutsideBoundingBoxOverride(blockEntity);
    }

    @Deprecated
    @Override
    default int getRenderDistance() {
        return getRenderDistanceOverride();
    }
}
