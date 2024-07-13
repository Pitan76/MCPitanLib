package net.pitan76.mcpitanlib.api.util.inventory.args;

import net.pitan76.mcpitanlib.midohra.item.ItemStack;

public class CanInsertArgs {
    public ItemStack stack;

    public CanInsertArgs(ItemStack stack) {
        this.stack = stack;
    }

    public CanInsertArgs(net.minecraft.item.ItemStack stack) {
        this(ItemStack.of(stack));
    }

    public ItemStack getStack() {
        return stack;
    }

    public net.minecraft.item.ItemStack getMcStack() {
        return stack.toMinecraft();
    }
}
