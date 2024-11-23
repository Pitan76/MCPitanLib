package net.pitan76.mcpitanlib.midohra.recipe;

import net.pitan76.mcpitanlib.api.util.CompatIdentifier;
import net.pitan76.mcpitanlib.midohra.recipe.entry.CraftingRecipeEntry;
import net.pitan76.mcpitanlib.midohra.recipe.entry.RecipeEntry;
import net.pitan76.mcpitanlib.midohra.recipe.entry.ShapedRecipeEntry;
import net.pitan76.mcpitanlib.midohra.recipe.entry.ShapelessRecipeEntry;
import net.pitan76.mcpitanlib.midohra.world.ServerWorld;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ServerRecipeManager extends RecipeManager {

    protected ServerRecipeManager(net.minecraft.recipe.RecipeManager recipeManager) {
        super(recipeManager);
    }

    public static ServerRecipeManager of(net.minecraft.recipe.RecipeManager serverRecipeManager) {
        return new ServerRecipeManager(serverRecipeManager);
    }

    public static ServerRecipeManager of(net.minecraft.server.world.ServerWorld world) {
        return of(world.getRecipeManager());
    }

    public static ServerRecipeManager of(ServerWorld world) {
        return of(world.getRaw());
    }
    
    public Collection<RecipeEntry> getRecipeEntries() {
        List<RecipeEntry> recipes = new ArrayList<>();
        for (net.minecraft.recipe.Recipe<?> recipe : getRaw().values()) {
            recipes.add(RecipeEntry.of(recipe, CompatIdentifier.fromMinecraft(recipe.getId())));
        }

        return recipes;
    }

    public Collection<CraftingRecipeEntry> getCraftingRecipeEntries() {
        List<CraftingRecipeEntry> recipes = new ArrayList<>();
        for (net.minecraft.recipe.Recipe<?> recipe : getRaw().values()) {
            if (recipe instanceof net.minecraft.recipe.CraftingRecipe) {
                recipes.add(CraftingRecipeEntry.of((net.minecraft.recipe.CraftingRecipe) recipe, CompatIdentifier.fromMinecraft(recipe.getId())));
            }
        }

        return recipes;
    }

    public Collection<ShapelessRecipeEntry> getShapelessRecipeEntries() {
        List<ShapelessRecipeEntry> recipes = new ArrayList<>();
        for (net.minecraft.recipe.Recipe<?> recipe : getRaw().values()) {
            if (recipe instanceof net.minecraft.recipe.ShapelessRecipe) {
                recipes.add(ShapelessRecipeEntry.of((net.minecraft.recipe.ShapelessRecipe) recipe, CompatIdentifier.fromMinecraft(recipe.getId())));
            }
        }

        return recipes;
    }

    public Collection<ShapedRecipeEntry> getShapedRecipeEntries() {
        List<ShapedRecipeEntry> recipes = new ArrayList<>();
        for (net.minecraft.recipe.Recipe<?> recipe : getRaw().values()) {
            if (recipe instanceof net.minecraft.recipe.ShapedRecipe) {
                recipes.add(ShapedRecipeEntry.of((net.minecraft.recipe.ShapedRecipe) recipe, CompatIdentifier.fromMinecraft(recipe.getId())));
            }
        }

        return recipes;
    }

    public Collection<RecipeEntry> getNormalRecipeEntries() {
        List<RecipeEntry> recipes = new ArrayList<>();
        for (net.minecraft.recipe.Recipe<?> recipe : getRaw().values()) {
            if (recipe instanceof net.minecraft.recipe.ShapelessRecipe) {
                recipes.add(ShapelessRecipeEntry.of((net.minecraft.recipe.ShapelessRecipe) recipe, CompatIdentifier.fromMinecraft(recipe.getId())));
            } else if (recipe instanceof net.minecraft.recipe.ShapedRecipe) {
                recipes.add(ShapedRecipeEntry.of((net.minecraft.recipe.ShapedRecipe) recipe, CompatIdentifier.fromMinecraft(recipe.getId())));
            }
        }

        return recipes;
    }

    public Collection<Recipe> getRecipes() {
        List<Recipe> recipes = new ArrayList<>();
        for (net.minecraft.recipe.Recipe<?> recipe : getRaw().values()) {
            recipes.add(Recipe.of(recipe));
        }

        return recipes;
    }

    public Collection<CraftingRecipe> getCraftingRecipes() {
        List<CraftingRecipe> recipes = new ArrayList<>();
        for (net.minecraft.recipe.Recipe<?> recipe : getRaw().values()) {
            if (recipe instanceof net.minecraft.recipe.CraftingRecipe) {
                recipes.add(CraftingRecipe.of((net.minecraft.recipe.CraftingRecipe) recipe));
            }
        }

        return recipes;
    }

    public Collection<ShapelessRecipe> getShapelessRecipes() {
        List<ShapelessRecipe> recipes = new ArrayList<>();
        for (net.minecraft.recipe.Recipe<?> recipe : getRaw().values()) {
            if (recipe instanceof net.minecraft.recipe.ShapelessRecipe) {
                recipes.add(ShapelessRecipe.of((net.minecraft.recipe.ShapelessRecipe) recipe));
            }
        }

        return recipes;
    }

    public Collection<ShapedRecipe> getShapedRecipes() {
        List<ShapedRecipe> recipes = new ArrayList<>();
        for (net.minecraft.recipe.Recipe<?> recipe : getRaw().values()) {
            if (recipe instanceof net.minecraft.recipe.ShapedRecipe) {
                recipes.add(ShapedRecipe.of((net.minecraft.recipe.ShapedRecipe) recipe));
            }
        }

        return recipes;
    }

    public Collection<Recipe> getNormalRecipes() {
        List<Recipe> recipes = new ArrayList<>();
        for (net.minecraft.recipe.Recipe<?> recipe : getRaw().values()) {
            if (recipe instanceof net.minecraft.recipe.ShapelessRecipe) {
                recipes.add(ShapelessRecipe.of((net.minecraft.recipe.ShapelessRecipe) recipe));
            } else if (recipe instanceof net.minecraft.recipe.ShapedRecipe) {
                recipes.add(ShapedRecipe.of((net.minecraft.recipe.ShapedRecipe) recipe));
            }
        }

        return recipes;
    }
}
