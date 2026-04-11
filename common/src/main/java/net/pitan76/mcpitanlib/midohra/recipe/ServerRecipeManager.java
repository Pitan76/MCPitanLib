package net.pitan76.mcpitanlib.midohra.recipe;

import net.pitan76.mcpitanlib.midohra.recipe.entry.CraftingRecipeEntry;
import net.pitan76.mcpitanlib.midohra.recipe.entry.RecipeEntry;
import net.pitan76.mcpitanlib.midohra.recipe.entry.ShapedRecipeEntry;
import net.pitan76.mcpitanlib.midohra.recipe.entry.ShapelessRecipeEntry;
import net.pitan76.mcpitanlib.midohra.world.ServerWorld;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ServerRecipeManager extends RecipeManager {
    private net.minecraft.world.item.crafting.RecipeManager serverRecipeManager;

    protected ServerRecipeManager(net.minecraft.world.item.crafting.RecipeManager serverRecipeManager) {
        super(null);
        this.serverRecipeManager = serverRecipeManager;
    }

    public static ServerRecipeManager of(net.minecraft.world.item.crafting.RecipeManager serverRecipeManager) {
        return new ServerRecipeManager(serverRecipeManager);
    }

    public static ServerRecipeManager of(net.minecraft.server.level.ServerLevel world) {
        return of(world.recipeAccess());
    }

    public static ServerRecipeManager of(ServerWorld world) {
        return of(world.getRaw());
    }

    @Override
    public net.minecraft.world.item.crafting.RecipeManager getRaw() {
        return serverRecipeManager;
    }

    @Override
    public net.minecraft.world.item.crafting.RecipeManager toMinecraft() {
        return getRaw();
    }

    @Deprecated
    public Collection<net.minecraft.world.item.crafting.RecipeHolder<?>> getRawRecipes() {
        return serverRecipeManager.getRecipes();
    }

    public Collection<RecipeEntry> getRecipeEntries() {
        List<RecipeEntry> recipes = new ArrayList<>();
        for (net.minecraft.world.item.crafting.RecipeHolder<?> recipe : serverRecipeManager.getRecipes()) {
            recipes.add(RecipeEntry.of(recipe));
        }

        return recipes;
    }

    public Collection<CraftingRecipeEntry> getCraftingRecipeEntries() {
        List<CraftingRecipeEntry> recipes = new ArrayList<>();
        for (net.minecraft.world.item.crafting.RecipeHolder<?> recipe : serverRecipeManager.getRecipes()) {
            if (recipe.value() instanceof net.minecraft.world.item.crafting.CraftingRecipe) {
                recipes.add(CraftingRecipeEntry.of(recipe));
            }
        }

        return recipes;
    }

    public Collection<ShapelessRecipeEntry> getShapelessRecipeEntries() {
        List<ShapelessRecipeEntry> recipes = new ArrayList<>();
        for (net.minecraft.world.item.crafting.RecipeHolder<?> recipe : serverRecipeManager.getRecipes()) {
            if (recipe.value() instanceof net.minecraft.world.item.crafting.ShapelessRecipe) {
                recipes.add(ShapelessRecipeEntry.of(recipe));
            }
        }

        return recipes;
    }

    public Collection<ShapedRecipeEntry> getShapedRecipeEntries() {
        List<ShapedRecipeEntry> recipes = new ArrayList<>();
        for (net.minecraft.world.item.crafting.RecipeHolder<?> recipe : serverRecipeManager.getRecipes()) {
            if (recipe.value() instanceof net.minecraft.world.item.crafting.ShapedRecipe) {
                recipes.add(ShapedRecipeEntry.of(recipe));
            }
        }

        return recipes;
    }

    public Collection<RecipeEntry> getNormalRecipeEntries() {
        List<RecipeEntry> recipes = new ArrayList<>();
        for (net.minecraft.world.item.crafting.RecipeHolder<?> recipe : serverRecipeManager.getRecipes()) {
            if (recipe.value() instanceof net.minecraft.world.item.crafting.ShapelessRecipe) {
                recipes.add(ShapelessRecipeEntry.of(recipe));
            } else if (recipe.value() instanceof net.minecraft.world.item.crafting.ShapedRecipe) {
                recipes.add(ShapedRecipeEntry.of(recipe));
            }
        }

        return recipes;
    }

    public Collection<Recipe> getRecipes() {
        List<Recipe> recipes = new ArrayList<>();
        for (net.minecraft.world.item.crafting.RecipeHolder<?> recipe : serverRecipeManager.getRecipes()) {
            recipes.add(Recipe.of(recipe.value()));
        }

        return recipes;
    }

    public Collection<CraftingRecipe> getCraftingRecipes() {
        List<CraftingRecipe> recipes = new ArrayList<>();
        for (net.minecraft.world.item.crafting.RecipeHolder<?> recipe : serverRecipeManager.getRecipes()) {
            if (recipe.value() instanceof net.minecraft.world.item.crafting.CraftingRecipe) {
                recipes.add(CraftingRecipe.of((net.minecraft.world.item.crafting.CraftingRecipe) recipe.value()));
            }
        }

        return recipes;
    }

    public Collection<ShapelessRecipe> getShapelessRecipes() {
        List<ShapelessRecipe> recipes = new ArrayList<>();
        for (net.minecraft.world.item.crafting.RecipeHolder<?> recipe : serverRecipeManager.getRecipes()) {
            if (recipe.value() instanceof net.minecraft.world.item.crafting.ShapelessRecipe) {
                recipes.add(ShapelessRecipe.of((net.minecraft.world.item.crafting.ShapelessRecipe) recipe.value()));
            }
        }

        return recipes;
    }

    public Collection<ShapedRecipe> getShapedRecipes() {
        List<ShapedRecipe> recipes = new ArrayList<>();
        for (net.minecraft.world.item.crafting.RecipeHolder<?> recipe : serverRecipeManager.getRecipes()) {
            if (recipe.value() instanceof net.minecraft.world.item.crafting.ShapedRecipe) {
                recipes.add(ShapedRecipe.of((net.minecraft.world.item.crafting.ShapedRecipe) recipe.value()));
            }
        }

        return recipes;
    }

    public Collection<Recipe> getNormalRecipes() {
        List<Recipe> recipes = new ArrayList<>();
        for (net.minecraft.world.item.crafting.RecipeHolder<?> recipe : serverRecipeManager.getRecipes()) {
            if (recipe.value() instanceof net.minecraft.world.item.crafting.ShapelessRecipe) {
                recipes.add(ShapelessRecipe.of((net.minecraft.world.item.crafting.ShapelessRecipe) recipe.value()));
            } else if (recipe.value() instanceof net.minecraft.world.item.crafting.ShapedRecipe) {
                recipes.add(ShapedRecipe.of((net.minecraft.world.item.crafting.ShapedRecipe) recipe.value()));
            }
        }

        return recipes;
    }
}
