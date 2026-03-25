package net.pitan76.mcpitanlib.api.util.recipe;

import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeType;
import net.minecraft.recipe.ServerRecipeManager;
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
        Collection<Recipe<?>> recipes = new ArrayList<>();

        for (net.minecraft.recipe.RecipeEntry<?> entry : getMCRecipeEntries(world)) {
            recipes.add(entry.value());
        }

        return recipes;
    }

    public static Collection<net.minecraft.recipe.RecipeEntry<?>> getMCRecipeEntries(World world) {
        if (world.getRecipeManager() instanceof ServerRecipeManager) {
            return ((ServerRecipeManager) world.getRecipeManager()).values();
        }

        // Client is not supported... TODO: Implement client support for 1.21.3~
        return new ArrayList<>();
    }

    public static Collection<CompatRecipe> getRecipes(World world) {
        List<CompatRecipe> recipes = new ArrayList<>();

        for (net.minecraft.recipe.RecipeEntry<?> entry : getMCRecipeEntries(world)) {
            recipes.add(new CompatRecipe(entry));
        }

        return recipes;
    }

    public static Collection<CompatRecipe> getRecipesByType(World world, RecipeType<?> type) {
        List<CompatRecipe> recipes = new ArrayList<>();

        for (net.minecraft.recipe.RecipeEntry<?> entry : getMCRecipeEntries(world)) {
            if (entry.value().getType() == type) {
                recipes.add(new CompatRecipe(entry));
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

        for (net.minecraft.recipe.RecipeEntry<?> entry : getMCRecipeEntries(world.getRaw())) {
            entries.add(RecipeEntry.of(entry.value(), CompatIdentifier.fromMinecraft(entry.id().getValue())));
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

        for (net.minecraft.recipe.RecipeEntry<?> entry : getMCRecipeEntries(world.getRaw())) {
            if (entry.value() instanceof net.minecraft.recipe.CraftingRecipe) {
                entries.add(CraftingRecipeEntry.of((net.minecraft.recipe.CraftingRecipe) entry.value(), CompatIdentifier.fromMinecraft(entry.id().getValue())));
            }
        }

        return entries;
    }
}
