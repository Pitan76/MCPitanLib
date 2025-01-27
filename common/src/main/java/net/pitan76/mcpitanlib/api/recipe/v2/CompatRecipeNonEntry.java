package net.pitan76.mcpitanlib.api.recipe.v2;

import net.minecraft.inventory.Inventory;
import net.minecraft.recipe.Recipe;
import net.pitan76.mcpitanlib.api.util.CompatIdentifier;
import net.pitan76.mcpitanlib.api.util.RecipeUtil;

@Deprecated
public class CompatRecipeNonEntry<T extends Inventory> extends CompatRecipeEntry<T> {
    public CompatRecipeNonEntry(Recipe<T> recipe) {
        super(CompatIdentifier.EMPTY, "", RecipeUtil.CompatibilityCraftingRecipeCategory.MISC, recipe);
    }

    public static CompatRecipeNonEntry<?> create(Recipe<?> recipe) {
        return new CompatRecipeNonEntry<>(recipe);
    }

    @Override
    public Recipe<T> getRecipe() {
        return super.getRecipe();
    }

    public Recipe<?> getRawRecipe() {
        return super.getRecipe();
    }
}
