package net.pitan76.mcpitanlib.api.gui.inventory.sided.args;

import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Direction;
import org.jetbrains.annotations.Nullable;

public class CanInsertArgs {
    public int slot;
    public ItemStack stack;

    @Nullable
    public Direction dir;

    public CanInsertArgs(int slot, ItemStack stack, @Nullable Direction dir) {
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

    public @Nullable Direction getDir() {
        return dir;
    }

    public net.pitan76.mcpitanlib.midohra.item.ItemStack getStack_midohra() {
        return net.pitan76.mcpitanlib.midohra.item.ItemStack.of(getStack());
    }
}
