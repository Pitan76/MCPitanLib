package net.pitan76.mcpitanlib.api.block;

import net.minecraft.world.level.block.Block;
import net.pitan76.mcpitanlib.midohra.block.BlockWrapper;

public interface ICompatBlock {
    default BlockWrapper getWrapper() {
        return this instanceof Block ? BlockWrapper.of((Block) this) : BlockWrapper.of();
    }
}
