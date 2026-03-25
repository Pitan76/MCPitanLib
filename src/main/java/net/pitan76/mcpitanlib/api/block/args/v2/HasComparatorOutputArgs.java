package net.pitan76.mcpitanlib.api.block.args.v2;

import net.minecraft.world.level.block.state.BlockState;
import net.pitan76.mcpitanlib.api.event.BaseEvent;

public class HasComparatorOutputArgs extends BaseEvent {
    public BlockState state;

    public HasComparatorOutputArgs(BlockState state) {
        this.state = state;
    }

    public BlockState getState() {
        return state;
    }
}
