package net.pitan76.mcpitanlib.api.util.recipe;

import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeType;
import net.minecraft.world.World;
import net.pitan76.mcpitanlib.api.recipe.v3.CompatRecipe;
import net.pitan76.mcpitanlib.api.util.CompatIdentifier;
import net.pitan76.mcpitanlib.midohra.recipe.CraftingRecipe;
import net.pitan76.mcpitanlib.midohra.recipe.entry.CraftingRecipeEntry;
import net.pitan76.mcpitanlib.midohra.recipe.entry.RecipeEntry;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class RecipeUtil {
    public static Collection<Recipe<?>> getMCRecipes(World world) {
        return world.getRecipeManager().values();
    }

    public static Collection<CompatRecipe> getRecipes(World world) {
        List<CompatRecipe> recipes = new ArrayList<>();

        for (Recipe<?> recipe : getMCRecipes(world)) {
            recipes.add(new CompatRecipe(recipe, recipe.getId()));
        }

        return recipes;
    }

    public static Collection<CompatRecipe> getRecipesByType(World world, RecipeType<?> type) {
        List<CompatRecipe> recipes = new ArrayList<>();

        for (Recipe<?> recipe : getMCRecipes(world)) {
            if (recipe.getType() == type) {
                recipes.add(new CompatRecipe(recipe, recipe.getId()));
            }
        }

        return recipes;
    }

    // MidohraAPI
    public static Collection<CompatRecipe> getRecipes(net.pitan76.mcpitanlib.midohra.world.World world) {
        return getRecipes(world.getRaw());
    }

    public static Collection<CompatRecipe> getRecipesByType(net.pitan76.mcpitanlib.midohra.world.World world, net.pitan76.mcpitanlib.midohra.recipe.RecipeType type) {
        return getRecipesByType(world.getRaw(), type.getRaw());
    }

    public static Collection<RecipeEntry> getRecipeEntries(net.pitan76.mcpitanlib.midohra.world.World world) {
        List<RecipeEntry> entries = new ArrayList<>();

        for (Recipe<?> recipe : getMCRecipes(world.getRaw())) {
            entries.add(RecipeEntry.of(recipe, CompatIdentifier.fromMinecraft(recipe.getId())));
        }

        return entries;
    }

    public static Collection<CraftingRecipe> getCraftingRecipes(net.pitan76.mcpitanlib.midohra.world.World world) {
        List<CraftingRecipe> recipes = new ArrayList<>();

        for (CompatRecipe recipe : getRecipesByType(world, net.pitan76.mcpitanlib.midohra.recipe.RecipeType.CRAFTING)) {
            if (recipe.getRecipe() instanceof net.minecraft.recipe.CraftingRecipe) {
                recipes.add(CraftingRecipe.of((net.minecraft.recipe.CraftingRecipe) recipe.getRecipe()));
            }
        }

        return recipes;
    }

    public static Collection<CraftingRecipeEntry> getCraftingRecipeEntries(net.pitan76.mcpitanlib.midohra.world.World world) {
        List<CraftingRecipeEntry> entries = new ArrayList<>();

        for (Recipe<?> recipe : getMCRecipes(world.getRaw())) {
            if (recipe instanceof net.minecraft.recipe.CraftingRecipe) {
                entries.add(CraftingRecipeEntry.of((net.minecraft.recipe.CraftingRecipe) recipe, CompatIdentifier.fromMinecraft(recipe.getId())));
            }
        }

        return entries;
    }
}
