package net.pitan76.mcpitanlib.api.item.args;

import net.minecraft.world.item.ItemStack;

public class UseActionArgs {
    public ItemStack stack;

    public UseActionArgs(ItemStack stack) {
        this.stack = stack;
    }

    public static UseActionArgs of(ItemStack stack) {
        return new UseActionArgs(stack);
    }

    public static UseActionArgs of(net.pitan76.mcpitanlib.midohra.item.ItemStack stack) {
        return of(stack.toMinecraft());
    }

    public ItemStack getStack() {
        return stack;
    }

    public net.pitan76.mcpitanlib.midohra.item.ItemStack getMidohraStack() {
        return net.pitan76.mcpitanlib.midohra.item.ItemStack.of(stack);
    }
}
