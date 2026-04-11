package net.pitan76.mcpitanlib.midohra.recipe.entry;

import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.resources.ResourceKey;
import net.minecraft.core.registries.Registries;
import net.pitan76.mcpitanlib.api.util.CompatIdentifier;
import net.pitan76.mcpitanlib.midohra.recipe.ShapedRecipe;

public class ShapedRecipeEntry extends RecipeEntry {
    private final net.minecraft.world.item.crafting.RecipeHolder<net.minecraft.world.item.crafting.ShapedRecipe> recipeEntry;

    protected ShapedRecipeEntry(net.minecraft.world.item.crafting.RecipeHolder<net.minecraft.world.item.crafting.ShapedRecipe> recipeEntry) {
        super(null);
        this.recipeEntry = recipeEntry;
    }

    public static ShapedRecipeEntry _of(net.minecraft.world.item.crafting.RecipeHolder<net.minecraft.world.item.crafting.ShapedRecipe> recipeEntry) {
        return new ShapedRecipeEntry(recipeEntry);
    }

    public static ShapedRecipeEntry of(net.minecraft.world.item.crafting.RecipeHolder<?> recipeEntry) {
        return _of((net.minecraft.world.item.crafting.RecipeHolder<net.minecraft.world.item.crafting.ShapedRecipe>) recipeEntry);
    }

    public static ShapedRecipeEntry of(net.minecraft.world.item.crafting.ShapedRecipe recipe, CompatIdentifier id) {
        ResourceKey<Recipe<?>> key = ResourceKey.create(Registries.RECIPE, id.toMinecraft());
        net.minecraft.world.item.crafting.RecipeHolder<net.minecraft.world.item.crafting.ShapedRecipe> recipeEntry = new net.minecraft.world.item.crafting.RecipeHolder<>(key, recipe);

        return of(recipeEntry);
    }

    public static ShapedRecipeEntry of(ShapedRecipe recipe, CompatIdentifier id) {
        return of(recipe.toMinecraft(), id);
    }

    @Override
    public net.minecraft.world.item.crafting.RecipeHolder<net.minecraft.world.item.crafting.ShapedRecipe> getRaw() {
        return recipeEntry;
    }

    @Override
    public net.minecraft.world.item.crafting.RecipeHolder<net.minecraft.world.item.crafting.ShapedRecipe> toMinecraft() {
        return getRaw();
    }

    @Override
    public ShapedRecipe getRecipe() {
        return ShapedRecipe.of(getRawRecipe());
    }

    @Override
    public net.minecraft.world.item.crafting.ShapedRecipe getRawRecipe() {
        return getRaw().value();
    }
}
