package net.pitan76.mcpitanlib.api.item.args;

import net.minecraft.item.ItemStack;

public class RarityArgs {
    public ItemStack stack;

    public RarityArgs(ItemStack stack) {
        this.stack = stack;
    }

    public ItemStack getStack() {
        return stack;
    }

    public net.pitan76.mcpitanlib.midohra.item.ItemStack getMidohraStack() {
        return net.pitan76.mcpitanlib.midohra.item.ItemStack.of(stack);
    }
}
