package net.pitan76.mcpitanlib.api.event.item;

import net.minecraft.item.ItemStack;

public class ItemBarColorArgs {

    public ItemStack stack;

    public ItemBarColorArgs(ItemStack stack) {
        this.stack = stack;
    }

    public ItemStack getStack() {
        return stack;
    }
}
