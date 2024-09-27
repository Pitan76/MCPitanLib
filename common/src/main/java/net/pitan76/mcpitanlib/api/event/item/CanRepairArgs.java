package net.pitan76.mcpitanlib.api.event.item;

import net.minecraft.item.ItemStack;
import net.pitan76.mcpitanlib.api.event.BaseEvent;

public class CanRepairArgs extends BaseEvent {
    public ItemStack stack;
    public ItemStack ingredient;

    public CanRepairArgs(ItemStack stack, ItemStack ingredient) {
        this.stack = stack;
        this.ingredient = ingredient;
    }

    public ItemStack getStack() {
        return stack;
    }

    public ItemStack getIngredient() {
        return ingredient;
    }
}
