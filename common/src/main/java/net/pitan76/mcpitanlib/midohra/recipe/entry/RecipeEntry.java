package net.pitan76.mcpitanlib.midohra.recipe.entry;

import net.minecraft.resources.ResourceKey;
import net.minecraft.core.registries.Registries;
import net.pitan76.mcpitanlib.api.recipe.v2.CompatRecipeEntry;
import net.pitan76.mcpitanlib.api.util.CompatIdentifier;
import net.pitan76.mcpitanlib.midohra.recipe.CraftingRecipe;
import net.pitan76.mcpitanlib.midohra.recipe.Recipe;
import net.pitan76.mcpitanlib.midohra.recipe.RecipeType;

public class RecipeEntry {
    private final net.minecraft.world.item.crafting.RecipeHolder<?> recipeEntry;

    protected RecipeEntry(net.minecraft.world.item.crafting.RecipeHolder<?> recipeEntry) {
        this.recipeEntry = recipeEntry;
    }

    public static RecipeEntry of(net.minecraft.world.item.crafting.RecipeHolder<?> recipeEntry) {
        return new RecipeEntry(recipeEntry);
    }

    public static RecipeEntry of(net.minecraft.world.item.crafting.Recipe<?> recipe, CompatIdentifier id) {
        ResourceKey<net.minecraft.world.item.crafting.Recipe<?>> key = ResourceKey.create(Registries.RECIPE, id.toMinecraft());
        net.minecraft.world.item.crafting.RecipeHolder<?> recipeEntry = new net.minecraft.world.item.crafting.RecipeHolder<>(key, recipe);
        return of(recipeEntry);
    }

    public static RecipeEntry of(Recipe recipe, CompatIdentifier id) {
        return of(recipe.toMinecraft(), id);
    }

    public static CraftingRecipeEntry of(net.minecraft.world.item.crafting.CraftingRecipe recipe, CompatIdentifier id) {
        return CraftingRecipeEntry.of(recipe, id);
    }

    public static CraftingRecipeEntry of(CraftingRecipe recipe, CompatIdentifier id) {
        return CraftingRecipeEntry.of(recipe, id);
    }

    public static ShapedRecipeEntry of(net.minecraft.world.item.crafting.ShapedRecipe recipe, CompatIdentifier id) {
        return ShapedRecipeEntry.of(recipe, id);
    }

    public static ShapedRecipeEntry of(net.pitan76.mcpitanlib.midohra.recipe.ShapedRecipe recipe, CompatIdentifier id) {
        return ShapedRecipeEntry.of(recipe, id);
    }

    public static ShapelessRecipeEntry of(net.minecraft.world.item.crafting.ShapelessRecipe recipe, CompatIdentifier id) {
        return ShapelessRecipeEntry.of(recipe, id);
    }

    public static ShapelessRecipeEntry of(net.pitan76.mcpitanlib.midohra.recipe.ShapelessRecipe recipe, CompatIdentifier id) {
        return ShapelessRecipeEntry.of(recipe, id);
    }

    public net.minecraft.world.item.crafting.RecipeHolder<?> getRaw() {
        return recipeEntry;
    }

    public net.minecraft.world.item.crafting.RecipeHolder<?> toMinecraft() {
        return getRaw();
    }

    public CompatIdentifier getId() {
        return CompatIdentifier.fromMinecraft(getRaw().id().identifier());
    }

    public CompatRecipeEntry<?> toCompatRecipeEntry() {
        return new CompatRecipeEntry(getRaw());
    }

    public net.minecraft.world.item.crafting.Recipe<?> getRawRecipe() {
        return getRaw().value();
    }

    public Recipe getRecipe() {
        return Recipe.of(getRaw().value());
    }

    public RecipeType getRecipeType() {
        return RecipeType.of(getRawRecipeType());
    }

    public net.minecraft.world.item.crafting.RecipeType getRawRecipeType() {
        return getRaw().value().getType();
    }
}
