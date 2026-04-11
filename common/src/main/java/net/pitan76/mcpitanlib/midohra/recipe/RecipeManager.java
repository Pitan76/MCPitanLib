package net.pitan76.mcpitanlib.midohra.recipe;

public class RecipeManager {
    private final net.minecraft.world.item.crafting.RecipeAccess recipeManager;

    protected RecipeManager(net.minecraft.world.item.crafting.RecipeAccess recipeManager) {
        this.recipeManager = recipeManager;
    }

    public static RecipeManager of(net.minecraft.world.item.crafting.RecipeAccess recipeManager) {
        return new RecipeManager(recipeManager);
    }

    public net.minecraft.world.item.crafting.RecipeAccess getRaw() {
        return recipeManager;
    }

    public net.minecraft.world.item.crafting.RecipeAccess toMinecraft() {
        return getRaw();
    }
}
