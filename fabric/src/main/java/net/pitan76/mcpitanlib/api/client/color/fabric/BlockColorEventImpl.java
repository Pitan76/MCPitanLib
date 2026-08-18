package net.pitan76.mcpitanlib.api.client.color.fabric;

import net.minecraft.block.entity.BlockEntity;
import net.pitan76.mcpitanlib.api.tile.RenderAttachmentBlockEntity;

public class BlockColorEventImpl {
    public static Object getRenderDataD(BlockEntity blockEntity) {
        if (blockEntity instanceof RenderAttachmentBlockEntity) {
            return ((RenderAttachmentBlockEntity) blockEntity).getCompatRenderData();
        }

        if (blockEntity instanceof net.pitan76.mcpitanlib.api.tile.RenderDataBlockEntity) {
            return ((net.pitan76.mcpitanlib.api.tile.RenderDataBlockEntity) blockEntity).getCompatRenderData();
        }

        if (blockEntity instanceof net.fabricmc.fabric.api.blockview.v2.RenderDataBlockEntity) {
            return ((net.fabricmc.fabric.api.blockview.v2.RenderDataBlockEntity) blockEntity).getRenderData();
        }

        return null;
    }
}