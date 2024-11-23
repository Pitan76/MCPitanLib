package net.pitan76.mcpitanlib.midohra.recipe.entry;

import net.pitan76.mcpitanlib.api.util.CompatIdentifier;
import net.pitan76.mcpitanlib.midohra.recipe.ShapedRecipe;

public class ShapedRecipeEntry extends RecipeEntry {
    private final net.minecraft.recipe.RecipeEntry<net.minecraft.recipe.ShapedRecipe> recipeEntry;

    protected ShapedRecipeEntry(net.minecraft.recipe.RecipeEntry<net.minecraft.recipe.ShapedRecipe> recipeEntry) {
        super(null);
        this.recipeEntry = recipeEntry;
    }

    public static ShapedRecipeEntry _of(net.minecraft.recipe.RecipeEntry<net.minecraft.recipe.ShapedRecipe> recipeEntry) {
        return new ShapedRecipeEntry(recipeEntry);
    }

    public static ShapedRecipeEntry of(net.minecraft.recipe.RecipeEntry<?> recipeEntry) {
        return _of((net.minecraft.recipe.RecipeEntry<net.minecraft.recipe.ShapedRecipe>) recipeEntry);
    }

    public static ShapedRecipeEntry of(net.minecraft.recipe.ShapedRecipe recipe, CompatIdentifier id) {
        net.minecraft.recipe.RecipeEntry<net.minecraft.recipe.ShapedRecipe> recipeEntry = new net.minecraft.recipe.RecipeEntry<>(id.toMinecraft(), recipe);

        return of(recipeEntry);
    }

    public static ShapedRecipeEntry of(ShapedRecipe recipe, CompatIdentifier id) {
        return of(recipe.toMinecraft(), id);
    }

    @Override
    public net.minecraft.recipe.RecipeEntry<net.minecraft.recipe.ShapedRecipe> getRaw() {
        return recipeEntry;
    }

    @Override
    public net.minecraft.recipe.RecipeEntry<net.minecraft.recipe.ShapedRecipe> toMinecraft() {
        return getRaw();
    }

    @Override
    public ShapedRecipe getRecipe() {
        return ShapedRecipe.of(getRawRecipe());
    }

    @Override
    public net.minecraft.recipe.ShapedRecipe getRawRecipe() {
        return getRaw().value();
    }
}
