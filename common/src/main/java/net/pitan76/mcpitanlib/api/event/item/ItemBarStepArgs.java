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

    public net.pitan76.mcpitanlib.midohra.item.ItemStack getStackM() {
        return net.pitan76.mcpitanlib.midohra.item.ItemStack.of(stack);
    }
}
