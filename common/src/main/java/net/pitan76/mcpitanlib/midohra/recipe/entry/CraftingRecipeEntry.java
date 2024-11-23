package net.pitan76.mcpitanlib.midohra.recipe.entry;

import net.pitan76.mcpitanlib.api.util.CompatIdentifier;
import net.pitan76.mcpitanlib.midohra.recipe.CraftingRecipe;

public class CraftingRecipeEntry extends RecipeEntry {
    private final CraftingRecipe recipe;

    protected CraftingRecipeEntry(CraftingRecipe recipe, CompatIdentifier id) {
        super(null, id);
        this.recipe = recipe;
    }

    protected CraftingRecipeEntry(CraftingRecipe recipe) {
        this(recipe, CompatIdentifier.fromMinecraft(recipe.getRaw().getId()));
    }

    public static CraftingRecipeEntry of(net.minecraft.recipe.CraftingRecipe recipe, CompatIdentifier id) {
        return of(CraftingRecipe.of(recipe), id);
    }

    public static CraftingRecipeEntry of(CraftingRecipe recipe, CompatIdentifier id) {
        return new CraftingRecipeEntry(recipe, id);
    }

    @Override
    public net.minecraft.recipe.CraftingRecipe getRaw() {
        return recipe.getRaw();
    }

    @Override
    public net.minecraft.recipe.CraftingRecipe toMinecraft() {
        return getRaw();
    }

    @Override
    public CraftingRecipe getRecipe() {
        return CraftingRecipe.of(getRawRecipe());
    }

    @Override
    public net.minecraft.recipe.CraftingRecipe getRawRecipe() {
        return getRaw();
    }
}
