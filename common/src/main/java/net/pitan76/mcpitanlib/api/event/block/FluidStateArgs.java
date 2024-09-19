package net.pitan76.mcpitanlib.api.event.block;

import net.minecraft.block.BlockState;

public class FluidStateArgs {
    public BlockState state;

    public FluidStateArgs(BlockState state) {
        this.state = state;
    }

    public BlockState getState() {
        return state;
    }
}
