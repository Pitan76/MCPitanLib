package net.pitan76.mcpitanlib.api.item.args.tool;

import net.pitan76.mcpitanlib.api.event.BaseEvent;
import net.pitan76.mcpitanlib.midohra.block.BlockState;
import net.pitan76.mcpitanlib.midohra.item.ItemStack;

public class MiningSpeedMultiplierArgs extends BaseEvent {
    public ItemStack stack;
    public BlockState state;

    public MiningSpeedMultiplierArgs(ItemStack stack, BlockState state) {
        this.stack = stack;
        this.state = state;
    }

    public BlockState getState() {
        return state;
    }

    public ItemStack getStack() {
        return stack;
    }
}
