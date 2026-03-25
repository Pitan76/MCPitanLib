package net.pitan76.mcpitanlib.midohra.recipe.entry;

import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.resources.ResourceKey;
import net.minecraft.core.registries.Registries;
import net.pitan76.mcpitanlib.api.util.CompatIdentifier;
import net.pitan76.mcpitanlib.midohra.recipe.ShapelessRecipe;

public class ShapelessRecipeEntry extends RecipeEntry {
    private final net.minecraft.world.item.crafting.RecipeHolder<net.minecraft.world.item.crafting.ShapelessRecipe> recipeEntry;

    protected ShapelessRecipeEntry(net.minecraft.world.item.crafting.RecipeHolder<net.minecraft.world.item.crafting.ShapelessRecipe> recipeEntry) {
        super(null);
        this.recipeEntry = recipeEntry;
    }

    public static ShapelessRecipeEntry _of(net.minecraft.world.item.crafting.RecipeHolder<net.minecraft.world.item.crafting.ShapelessRecipe> recipeEntry) {
        return new ShapelessRecipeEntry(recipeEntry);
    }

    public static ShapelessRecipeEntry of(net.minecraft.world.item.crafting.RecipeHolder<?> recipeEntry) {
        return _of((net.minecraft.world.item.crafting.RecipeHolder<net.minecraft.world.item.crafting.ShapelessRecipe>) recipeEntry);
    }

    public static ShapelessRecipeEntry of(net.minecraft.world.item.crafting.ShapelessRecipe recipe, CompatIdentifier id) {
        ResourceKey<Recipe<?>> key = ResourceKey.create(Registries.RECIPE, id.toMinecraft());
        net.minecraft.world.item.crafting.RecipeHolder<net.minecraft.world.item.crafting.ShapelessRecipe> recipeEntry = new net.minecraft.world.item.crafting.RecipeHolder<>(key, recipe);

        return of(recipeEntry);
    }

    public static ShapelessRecipeEntry of(ShapelessRecipe recipe, CompatIdentifier id) {
        return of(recipe.toMinecraft(), id);
    }

    @Override
    public net.minecraft.world.item.crafting.RecipeHolder<net.minecraft.world.item.crafting.ShapelessRecipe> getRaw() {
        return recipeEntry;
    }

    @Override
    public net.minecraft.world.item.crafting.RecipeHolder<net.minecraft.world.item.crafting.ShapelessRecipe> toMinecraft() {
        return getRaw();
    }

    @Override
    public ShapelessRecipe getRecipe() {
        return ShapelessRecipe.of(getRawRecipe());
    }

    @Override
    public net.minecraft.world.item.crafting.ShapelessRecipe getRawRecipe() {
        return getRaw().value();
    }
}
