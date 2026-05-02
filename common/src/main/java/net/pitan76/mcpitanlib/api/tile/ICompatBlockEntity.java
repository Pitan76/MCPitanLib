package net.pitan76.mcpitanlib.api.tile;

import net.minecraft.world.level.block.entity.BlockEntity;
import net.pitan76.mcpitanlib.api.util.BlockEntityUtil;
import net.pitan76.mcpitanlib.midohra.block.entity.BlockEntityTypeWrapper;
import net.pitan76.mcpitanlib.midohra.block.entity.BlockEntityWrapper;

public interface ICompatBlockEntity {
    default BlockEntityWrapper getWrapper() {
        return this instanceof BlockEntity ? BlockEntityWrapper.of((BlockEntity) this) : BlockEntityWrapper.of();
    }

    default BlockEntityTypeWrapper getTypeWrapper() {
        return BlockEntityTypeWrapper.of(BlockEntityUtil.getType((BlockEntity) this));
    }
}
