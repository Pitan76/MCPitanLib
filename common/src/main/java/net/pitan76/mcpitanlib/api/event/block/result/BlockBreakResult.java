package net.pitan76.mcpitanlib.api.event.block.result;

import net.minecraft.world.level.block.state.BlockState;

public class BlockBreakResult {
    public BlockState state;

    public BlockBreakResult(BlockState state) {
        this.state = state;
    }

    public BlockState getState() {
        return state;
    }
}
