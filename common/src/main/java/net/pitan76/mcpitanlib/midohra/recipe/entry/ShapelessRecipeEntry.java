package net.pitan76.mcpitanlib.midohra.recipe.entry;

import net.pitan76.mcpitanlib.api.util.CompatIdentifier;
import net.pitan76.mcpitanlib.midohra.recipe.ShapelessRecipe;

public class ShapelessRecipeEntry extends RecipeEntry {
    private final ShapelessRecipe recipe;

    protected ShapelessRecipeEntry(ShapelessRecipe recipe, CompatIdentifier id) {
        super(null, id);
        this.recipe = recipe;
    }

    protected ShapelessRecipeEntry(ShapelessRecipe recipe) {
        this(recipe, CompatIdentifier.fromMinecraft(recipe.getRaw().getId()));
    }

    public static ShapelessRecipeEntry of(net.minecraft.recipe.ShapelessRecipe recipe, CompatIdentifier id) {
        return of(ShapelessRecipe.of(recipe), id);
    }

    public static ShapelessRecipeEntry of(ShapelessRecipe recipe, CompatIdentifier id) {
        return new ShapelessRecipeEntry(recipe, id);
    }

    @Override
    public net.minecraft.recipe.ShapelessRecipe getRaw() {
        return recipe.getRaw();
    }

    @Override
    public net.minecraft.recipe.ShapelessRecipe toMinecraft() {
        return getRaw();
    }

    @Override
    public ShapelessRecipe getRecipe() {
        return ShapelessRecipe.of(getRawRecipe());
    }

    @Override
    public net.minecraft.recipe.ShapelessRecipe getRawRecipe() {
        return getRaw();
    }
}
