package net.pitan76.mcpitanlib.midohra.recipe.entry;

import net.minecraft.recipe.Recipe;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.pitan76.mcpitanlib.api.util.CompatIdentifier;
import net.pitan76.mcpitanlib.midohra.recipe.ShapelessRecipe;

public class ShapelessRecipeEntry extends RecipeEntry {
    private final net.minecraft.recipe.RecipeEntry<net.minecraft.recipe.ShapelessRecipe> recipeEntry;

    protected ShapelessRecipeEntry(net.minecraft.recipe.RecipeEntry<net.minecraft.recipe.ShapelessRecipe> recipeEntry) {
        super(null);
        this.recipeEntry = recipeEntry;
    }

    public static ShapelessRecipeEntry _of(net.minecraft.recipe.RecipeEntry<net.minecraft.recipe.ShapelessRecipe> recipeEntry) {
        return new ShapelessRecipeEntry(recipeEntry);
    }

    public static ShapelessRecipeEntry of(net.minecraft.recipe.RecipeEntry<?> recipeEntry) {
        return _of((net.minecraft.recipe.RecipeEntry<net.minecraft.recipe.ShapelessRecipe>) recipeEntry);
    }

    public static ShapelessRecipeEntry of(net.minecraft.recipe.ShapelessRecipe recipe, CompatIdentifier id) {
        RegistryKey<Recipe<?>> key = RegistryKey.of(RegistryKeys.RECIPE, id.toMinecraft());
        net.minecraft.recipe.RecipeEntry<net.minecraft.recipe.ShapelessRecipe> recipeEntry = new net.minecraft.recipe.RecipeEntry<>(key, recipe);

        return of(recipeEntry);
    }

    public static ShapelessRecipeEntry of(ShapelessRecipe recipe, CompatIdentifier id) {
        return of(recipe.toMinecraft(), id);
    }

    @Override
    public net.minecraft.recipe.RecipeEntry<net.minecraft.recipe.ShapelessRecipe> getRaw() {
        return recipeEntry;
    }

    @Override
    public net.minecraft.recipe.RecipeEntry<net.minecraft.recipe.ShapelessRecipe> toMinecraft() {
        return getRaw();
    }

    @Override
    public ShapelessRecipe getRecipe() {
        return ShapelessRecipe.of(getRawRecipe());
    }

    @Override
    public net.minecraft.recipe.ShapelessRecipe getRawRecipe() {
        return getRaw().value();
    }
}
