package net.pitan76.mcpitanlib.api.block.v2;

import net.pitan76.mcpitanlib.api.block.CompatibleBlockSettings;
import net.pitan76.mcpitanlib.api.block.ExtendBlock;
import net.pitan76.mcpitanlib.midohra.block.BlockWrapper;

public class CompatBlock extends ExtendBlock {

    public CompatibleBlockSettings settings;

    public CompatBlock(CompatibleBlockSettings settings) {
        super(settings);
        this.settings = settings;
    }

    public CompatibleBlockSettings getCompatSettings() {
        return settings;
    }

    public BlockWrapper getWrapper() {
        return BlockWrapper.of(this);
    }
}
