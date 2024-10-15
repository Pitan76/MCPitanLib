package net.pitan76.mcpitanlib.api.block.v2;

import net.minecraft.block.Block;
import net.pitan76.mcpitanlib.api.block.ExtendBlockProvider;
import net.pitan76.mcpitanlib.midohra.block.BlockWrapper;

public interface CompatBlockProvider extends ExtendBlockProvider {
    CompatibleBlockSettings getCompatSettings();

    default BlockWrapper getWrapper() {
        return this instanceof Block ? BlockWrapper.of((Block) this) : BlockWrapper.of();
    }
}
