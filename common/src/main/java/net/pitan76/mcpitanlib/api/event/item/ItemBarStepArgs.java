package net.pitan76.mcpitanlib.api.event.item;

import net.minecraft.world.item.ItemStack;

public class ItemBarStepArgs {

    public ItemStack stack;

    public ItemBarStepArgs(ItemStack stack) {
        this.stack = stack;
    }

    public ItemStack getStack() {
        return stack;
    }
}
