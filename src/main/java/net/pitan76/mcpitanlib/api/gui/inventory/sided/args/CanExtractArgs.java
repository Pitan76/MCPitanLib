package net.pitan76.mcpitanlib.api.gui.inventory.sided.args;

import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Direction;

public class CanExtractArgs {
    public int slot;
    public ItemStack stack;
    public Direction dir;

    public CanExtractArgs(int slot, ItemStack stack, Direction dir) {
        this.slot = slot;
        this.stack = stack;
        this.dir = dir;
    }

    public int getSlot() {
        return slot;
    }

    public ItemStack getStack() {
        return stack;
    }

    public Direction getDir() {
        return dir;
    }

    public net.pitan76.mcpitanlib.midohra.item.ItemStack getStack_midohra() {
        return net.pitan76.mcpitanlib.midohra.item.ItemStack.of(getStack());
    }
}
