package net.pitan76.mcpitanlib.midohra.recipe.entry;

import net.pitan76.mcpitanlib.api.recipe.v2.CompatRecipeEntry;
import net.pitan76.mcpitanlib.api.util.CompatIdentifier;
import net.pitan76.mcpitanlib.midohra.recipe.CraftingRecipe;
import net.pitan76.mcpitanlib.midohra.recipe.Recipe;
import net.pitan76.mcpitanlib.midohra.recipe.RecipeType;

public class RecipeEntry {
    private final Recipe recipe;
    private final CompatIdentifier id;

    protected RecipeEntry(Recipe recipe, CompatIdentifier id) {
        this.recipe = recipe;
        this.id = id;
    }

    protected RecipeEntry(Recipe recipe) {
        this(recipe, CompatIdentifier.fromMinecraft(recipe.getRaw().getId()));
    }

    public static RecipeEntry of(net.minecraft.recipe.Recipe<?> recipe, CompatIdentifier id) {
        return of(Recipe.of(recipe), id);
    }

    public static RecipeEntry of(Recipe recipe, CompatIdentifier id) {
        return new RecipeEntry(recipe, id);
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

    public net.minecraft.recipe.Recipe<?> getRaw() {
        return recipe.getRaw();
    }

    public net.minecraft.recipe.Recipe<?> toMinecraft() {
        return getRaw();
    }

    public CompatIdentifier getId() {
        if (id == null)
            return CompatIdentifier.fromMinecraft(getRaw().getId());

        return id;
    }

    public CompatRecipeEntry<?> toCompatRecipeEntry() {
        return new CompatRecipeEntry(getRaw());
    }

    public net.minecraft.recipe.Recipe<?> getRawRecipe() {
        return getRaw();
    }

    public Recipe getRecipe() {
        return Recipe.of(getRaw());
    }

    public RecipeType getRecipeType() {
        return RecipeType.of(getRawRecipeType());
    }

    public net.minecraft.recipe.RecipeType getRawRecipeType() {
        return getRaw().getType();
    }
}
