package net.pitan76.mcpitanlib.midohra.recipe;

import net.pitan76.mcpitanlib.midohra.item.ItemStack;
import net.pitan76.mcpitanlib.midohra.recipe.input.CraftingRecipeInputOrInventory;

public class ShapedRecipe extends CraftingRecipe {
    private final net.minecraft.recipe.ShapedRecipe recipe;

    protected ShapedRecipe(net.minecraft.recipe.ShapedRecipe recipe) {
        super(null);
        this.recipe = recipe;
    }

    public static ShapedRecipe of(net.minecraft.recipe.ShapedRecipe recipe) {
        return new ShapedRecipe(recipe);
    }

    public net.minecraft.recipe.ShapedRecipe getRaw() {
        return recipe;
    }

    public net.minecraft.recipe.ShapedRecipe toMinecraft() {
        return getRaw();
    }

    public boolean matches(CraftingRecipeInputOrInventory input) {
        return getRaw().matches(input.getRaw(), null);
    }

    public net.minecraft.item.ItemStack craft() {
        return getRaw().craft(null);
    }

    public ItemStack craftMidohra() {
        return ItemStack.of(craft());
    }

    public int getWidth() {
        return getRaw().getWidth();
    }

    public int getHeight() {
        return getRaw().getHeight();
    }
}
