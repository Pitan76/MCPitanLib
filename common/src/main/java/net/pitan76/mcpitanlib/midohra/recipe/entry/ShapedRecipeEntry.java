package net.pitan76.mcpitanlib.midohra.recipe.entry;

import net.pitan76.mcpitanlib.api.util.CompatIdentifier;
import net.pitan76.mcpitanlib.midohra.recipe.ShapedRecipe;

public class ShapedRecipeEntry extends RecipeEntry {
    private final ShapedRecipe recipe;

    protected ShapedRecipeEntry(ShapedRecipe recipe, CompatIdentifier id) {
        super(null, id);
        this.recipe = recipe;
    }

    protected ShapedRecipeEntry(ShapedRecipe recipe) {
        this(recipe, CompatIdentifier.fromMinecraft(recipe.getRaw().getId()));
    }

    public static ShapedRecipeEntry of(net.minecraft.recipe.ShapedRecipe recipe, CompatIdentifier id) {
        return of(ShapedRecipe.of(recipe), id);
    }

    public static ShapedRecipeEntry of(ShapedRecipe recipe, CompatIdentifier id) {
        return new ShapedRecipeEntry(recipe, id);
    }

    @Override
    public net.minecraft.recipe.ShapedRecipe getRaw() {
        return recipe.getRaw();
    }

    @Override
    public net.minecraft.recipe.ShapedRecipe toMinecraft() {
        return getRaw();
    }

    @Override
    public ShapedRecipe getRecipe() {
        return ShapedRecipe.of(getRawRecipe());
    }

    @Override
    public net.minecraft.recipe.ShapedRecipe getRawRecipe() {
        return getRaw();
    }
}
