package net.pitan76.mcpitanlib.api.item.args.tool;

import net.pitan76.mcpitanlib.api.event.BaseEvent;
import net.pitan76.mcpitanlib.midohra.block.BlockState;
import net.pitan76.mcpitanlib.midohra.block.BlockWrapper;

public class SuitableForArgs extends BaseEvent {
    public BlockState state;

    public SuitableForArgs(BlockState state) {
        this.state = state;
    }

    public BlockState getState() {
        return state;
    }

    public BlockWrapper getBlock() {
        return state.getBlock();
    }
}
