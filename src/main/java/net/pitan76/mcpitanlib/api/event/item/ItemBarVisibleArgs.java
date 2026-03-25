package net.pitan76.mcpitanlib.api.event.item;

import net.minecraft.item.ItemStack;

public class ItemBarVisibleArgs {

    public ItemStack stack;

    public ItemBarVisibleArgs(ItemStack stack) {
        this.stack = stack;
    }

    public ItemStack getStack() {
        return stack;
    }
}
