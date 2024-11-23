package net.pitan76.mcpitanlib.midohra.recipe.entry;

import net.pitan76.mcpitanlib.api.recipe.v2.CompatRecipeEntry;
import net.pitan76.mcpitanlib.api.util.CompatIdentifier;
import net.pitan76.mcpitanlib.midohra.recipe.CraftingRecipe;
import net.pitan76.mcpitanlib.midohra.recipe.Recipe;
import net.pitan76.mcpitanlib.midohra.recipe.RecipeType;

public class RecipeEntry {
    private final net.minecraft.recipe.RecipeEntry<?> recipeEntry;

    protected RecipeEntry(net.minecraft.recipe.RecipeEntry<?> recipeEntry) {
        this.recipeEntry = recipeEntry;
    }

    public static RecipeEntry of(net.minecraft.recipe.RecipeEntry<?> recipeEntry) {
        return new RecipeEntry(recipeEntry);
    }

    public static RecipeEntry of(net.minecraft.recipe.Recipe<?> recipe, CompatIdentifier id) {
        net.minecraft.recipe.RecipeEntry<?> recipeEntry = new net.minecraft.recipe.RecipeEntry<>(id.toMinecraft(), recipe);
        return of(recipeEntry);
    }

    public static RecipeEntry of(Recipe recipe, CompatIdentifier id) {
        return of(recipe.toMinecraft(), id);
    }

    public static CraftingRecipeEntry of(net.minecraft.recipe.CraftingRecipe recipe, CompatIdentifier id) {
        return CraftingRecipeEntry.of(recipe, id);
    }

    public static CraftingRecipeEntry of(CraftingRecipe recipe, CompatIdentifier id) {
        return CraftingRecipeEntry.of(recipe, id);
    }

    public static ShapedRecipeEntry of(net.minecraft.recipe.ShapedRecipe recipe, CompatIdentifier id) {
        return ShapedRecipeEntry.of(recipe, id);
    }

    public static ShapedRecipeEntry of(net.pitan76.mcpitanlib.midohra.recipe.ShapedRecipe recipe, CompatIdentifier id) {
        return ShapedRecipeEntry.of(recipe, id);
    }

    public static ShapelessRecipeEntry of(net.minecraft.recipe.ShapelessRecipe recipe, CompatIdentifier id) {
        return ShapelessRecipeEntry.of(recipe, id);
    }

    public static ShapelessRecipeEntry of(net.pitan76.mcpitanlib.midohra.recipe.ShapelessRecipe recipe, CompatIdentifier id) {
        return ShapelessRecipeEntry.of(recipe, id);
    }

    public net.minecraft.recipe.RecipeEntry<?> getRaw() {
        return recipeEntry;
    }

    public net.minecraft.recipe.RecipeEntry<?> toMinecraft() {
        return getRaw();
    }

    public CompatIdentifier getId() {
        return CompatIdentifier.fromMinecraft(getRaw().id());
    }

    public CompatRecipeEntry<?> toCompatRecipeEntry() {
        return new CompatRecipeEntry(getRaw());
    }

    public net.minecraft.recipe.Recipe<?> getRawRecipe() {
        return getRaw().value();
    }

    public Recipe getRecipe() {
        return Recipe.of(getRaw().value());
    }

    public RecipeType getRecipeType() {
        return RecipeType.of(getRawRecipeType());
    }

    public net.minecraft.recipe.RecipeType getRawRecipeType() {
        return getRaw().value().getType();
    }
}
