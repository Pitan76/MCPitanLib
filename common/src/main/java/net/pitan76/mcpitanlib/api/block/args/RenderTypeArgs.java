package net.pitan76.mcpitanlib.api.block.args;

import net.minecraft.block.BlockState;

public class RenderTypeArgs {
    public BlockState state;

    public RenderTypeArgs(BlockState state) {
        this.state = state;
    }

    public BlockState getState() {
        return state;
    }

    public net.pitan76.mcpitanlib.midohra.block.BlockState getMidohraState() {
        return net.pitan76.mcpitanlib.midohra.block.BlockState.of(getState());
    }
}
