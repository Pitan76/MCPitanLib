package net.pitan76.mcpitanlib.api.recipe.input;

import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;

public class CompatRecipeInput<I extends Inventory> {
    protected I input;

    public CompatRecipeInput(I input) {
        this.input = input;
    }

    public I getInput() {
        return input;
    }

    public ItemStack getStack(int slot) {
        return input.getStack(slot);
    }

    public net.pitan76.mcpitanlib.midohra.item.ItemStack getMidohraStack(int slot) {
        return net.pitan76.mcpitanlib.midohra.item.ItemStack.of(getStack(slot));
    }

    public int getSize() {
        return input.size();
    }

    public boolean isEmpty() {
        return input.isEmpty();
    }
}
