package net.pitan76.mcpitanlib.midohra.recipe;

import net.pitan76.mcpitanlib.midohra.item.ItemStack;
import net.pitan76.mcpitanlib.midohra.recipe.input.CraftingRecipeInputOrInventory;

public class ShapelessRecipe extends CraftingRecipe {
    private final net.minecraft.recipe.ShapelessRecipe recipe;

    protected ShapelessRecipe(net.minecraft.recipe.ShapelessRecipe recipe) {
        super(null);
        this.recipe = recipe;
    }

    public static ShapelessRecipe of(net.minecraft.recipe.ShapelessRecipe recipe) {
        return new ShapelessRecipe(recipe);
    }

    public net.minecraft.recipe.ShapelessRecipe getRaw() {
        return recipe;
    }

    public net.minecraft.recipe.ShapelessRecipe toMinecraft() {
        return getRaw();
    }

    public boolean matches(CraftingRecipeInputOrInventory input) {
        return getRaw().matches(input.getRaw(), null);
    }

    public net.minecraft.item.ItemStack craft() {
        return getRaw().craft(null, null);
    }

    public ItemStack craftMidohra() {
        return ItemStack.of(craft());
    }
}
