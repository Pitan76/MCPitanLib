package net.pitan76.mcpitanlib.midohra.recipe.entry;

import net.minecraft.recipe.Recipe;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.pitan76.mcpitanlib.api.util.CompatIdentifier;
import net.pitan76.mcpitanlib.midohra.recipe.CraftingRecipe;

public class CraftingRecipeEntry extends RecipeEntry {
    private final net.minecraft.recipe.RecipeEntry<net.minecraft.recipe.CraftingRecipe> recipeEntry;

    protected CraftingRecipeEntry(net.minecraft.recipe.RecipeEntry<net.minecraft.recipe.CraftingRecipe> recipeEntry) {
        super(null);
        this.recipeEntry = recipeEntry;
    }

    public static CraftingRecipeEntry _of(net.minecraft.recipe.RecipeEntry<net.minecraft.recipe.CraftingRecipe> recipeEntry) {
        return new CraftingRecipeEntry(recipeEntry);
    }

    public static CraftingRecipeEntry of(net.minecraft.recipe.RecipeEntry<?> recipeEntry) {
        return _of((net.minecraft.recipe.RecipeEntry<net.minecraft.recipe.CraftingRecipe>) recipeEntry);
    }

    public static CraftingRecipeEntry of(net.minecraft.recipe.CraftingRecipe recipe, CompatIdentifier id) {
        RegistryKey<Recipe<?>> key = RegistryKey.of(RegistryKeys.RECIPE, id.toMinecraft());
        net.minecraft.recipe.RecipeEntry<net.minecraft.recipe.CraftingRecipe> recipeEntry = new net.minecraft.recipe.RecipeEntry<>(key, recipe);

        return of(recipeEntry);
    }

    public static CraftingRecipeEntry of(CraftingRecipe recipe, CompatIdentifier id) {
        return of(recipe.toMinecraft(), id);
    }

    @Override
    public net.minecraft.recipe.RecipeEntry<net.minecraft.recipe.CraftingRecipe> getRaw() {
        return recipeEntry;
    }

    @Override
    public net.minecraft.recipe.RecipeEntry<net.minecraft.recipe.CraftingRecipe> toMinecraft() {
        return getRaw();
    }

    @Override
    public CraftingRecipe getRecipe() {
        return CraftingRecipe.of(getRawRecipe());
    }

    @Override
    public net.minecraft.recipe.CraftingRecipe getRawRecipe() {
        return getRaw().value();
    }
}
