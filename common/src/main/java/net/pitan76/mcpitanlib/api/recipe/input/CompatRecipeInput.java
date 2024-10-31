package net.pitan76.mcpitanlib.api.recipe.input;

import net.minecraft.item.ItemStack;
import net.minecraft.recipe.input.RecipeInput;

public class CompatRecipeInput<I extends RecipeInput> {
    protected I input;

    public CompatRecipeInput(I input) {
        this.input = input;
    }

    public I getInput() {
        return input;
    }

    public ItemStack getStack(int slot) {
        return input.getStackInSlot(slot);
    }

    public net.pitan76.mcpitanlib.midohra.item.ItemStack getMidohraStack(int slot) {
        return net.pitan76.mcpitanlib.midohra.item.ItemStack.of(getStack(slot));
    }

    public int getSize() {
        return input.getSize();
    }

    public boolean isEmpty() {
        return input.isEmpty();
    }
}
