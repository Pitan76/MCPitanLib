package net.pitan76.mcpitanlib.midohra.recipe;

public class RecipeManager {
    private final net.minecraft.recipe.RecipeManager recipeManager;

    protected RecipeManager(net.minecraft.recipe.RecipeManager recipeManager) {
        this.recipeManager = recipeManager;
    }

    public static RecipeManager of(net.minecraft.recipe.RecipeManager recipeManager) {
        return new RecipeManager(recipeManager);
    }

    public net.minecraft.recipe.RecipeManager getRaw() {
        return recipeManager;
    }

    public net.minecraft.recipe.RecipeManager toMinecraft() {
        return getRaw();
    }
}
