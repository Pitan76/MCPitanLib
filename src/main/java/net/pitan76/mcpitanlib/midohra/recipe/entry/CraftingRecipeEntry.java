package net.pitan76.mcpitanlib.midohra.recipe.entry;

import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.resources.ResourceKey;
import net.minecraft.core.registries.Registries;
import net.pitan76.mcpitanlib.api.util.CompatIdentifier;
import net.pitan76.mcpitanlib.midohra.recipe.CraftingRecipe;

public class CraftingRecipeEntry extends RecipeEntry {
    private final net.minecraft.world.item.crafting.RecipeHolder<net.minecraft.world.item.crafting.CraftingRecipe> recipeEntry;

    protected CraftingRecipeEntry(net.minecraft.world.item.crafting.RecipeHolder<net.minecraft.world.item.crafting.CraftingRecipe> recipeEntry) {
        super(null);
        this.recipeEntry = recipeEntry;
    }

    public static CraftingRecipeEntry _of(net.minecraft.world.item.crafting.RecipeHolder<net.minecraft.world.item.crafting.CraftingRecipe> recipeEntry) {
        return new CraftingRecipeEntry(recipeEntry);
    }

    public static CraftingRecipeEntry of(net.minecraft.world.item.crafting.RecipeHolder<?> recipeEntry) {
        return _of((net.minecraft.world.item.crafting.RecipeHolder<net.minecraft.world.item.crafting.CraftingRecipe>) recipeEntry);
    }

    public static CraftingRecipeEntry of(net.minecraft.world.item.crafting.CraftingRecipe recipe, CompatIdentifier id) {
        ResourceKey<Recipe<?>> key = ResourceKey.create(Registries.RECIPE, id.toMinecraft());
        net.minecraft.world.item.crafting.RecipeHolder<net.minecraft.world.item.crafting.CraftingRecipe> recipeEntry = new net.minecraft.world.item.crafting.RecipeHolder<>(key, recipe);

        return of(recipeEntry);
    }

    public static CraftingRecipeEntry of(CraftingRecipe recipe, CompatIdentifier id) {
        return of(recipe.toMinecraft(), id);
    }

    @Override
    public net.minecraft.world.item.crafting.RecipeHolder<net.minecraft.world.item.crafting.CraftingRecipe> getRaw() {
        return recipeEntry;
    }

    @Override
    public net.minecraft.world.item.crafting.RecipeHolder<net.minecraft.world.item.crafting.CraftingRecipe> toMinecraft() {
        return getRaw();
    }

    @Override
    public CraftingRecipe getRecipe() {
        return CraftingRecipe.of(getRawRecipe());
    }

    @Override
    public net.minecraft.world.item.crafting.CraftingRecipe getRawRecipe() {
        return getRaw().value();
    }
}
