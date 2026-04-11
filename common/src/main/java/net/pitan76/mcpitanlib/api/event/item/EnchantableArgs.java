package net.pitan76.mcpitanlib.api.event.item;

import net.minecraft.world.item.ItemStack;

public class EnchantableArgs {

    public ItemStack stack;

    public EnchantableArgs(ItemStack stack) {
        this.stack = stack;
    }

    public ItemStack getStack() {
        return stack;
    }
}
