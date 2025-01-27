package net.pitan76.mcpitanlib.midohra.recipe;

import net.minecraft.registry.Registries;
import net.pitan76.mcpitanlib.api.util.CompatIdentifier;

public class RecipeType {
    private final net.minecraft.recipe.RecipeType<?> recipeType;

    public static final RecipeType CRAFTING = new RecipeType(net.minecraft.recipe.RecipeType.CRAFTING);
    public static final RecipeType SMELTING = new RecipeType(net.minecraft.recipe.RecipeType.SMELTING);
    public static final RecipeType BLASTING = new RecipeType(net.minecraft.recipe.RecipeType.BLASTING);
    public static final RecipeType SMOKING = new RecipeType(net.minecraft.recipe.RecipeType.SMOKING);
    public static final RecipeType CAMPFIRE_COOKING = new RecipeType(net.minecraft.recipe.RecipeType.CAMPFIRE_COOKING);
    public static final RecipeType STONECUTTING = new RecipeType(net.minecraft.recipe.RecipeType.STONECUTTING);
    public static final RecipeType SMITHING = new RecipeType(net.minecraft.recipe.RecipeType.SMITHING);

    protected RecipeType(net.minecraft.recipe.RecipeType<?> recipeType) {
        this.recipeType = recipeType;
    }

    public static RecipeType of(net.minecraft.recipe.RecipeType<?> recipeType) {
        if (recipeType == net.minecraft.recipe.RecipeType.CRAFTING) {
            return CRAFTING;
        } else if (recipeType == net.minecraft.recipe.RecipeType.SMELTING) {
            return SMELTING;
        } else if (recipeType == net.minecraft.recipe.RecipeType.BLASTING) {
            return BLASTING;
        } else if (recipeType == net.minecraft.recipe.RecipeType.SMOKING) {
            return SMOKING;
        } else if (recipeType == net.minecraft.recipe.RecipeType.CAMPFIRE_COOKING) {
            return CAMPFIRE_COOKING;
        } else if (recipeType == net.minecraft.recipe.RecipeType.STONECUTTING) {
            return STONECUTTING;
        } else if (recipeType == net.minecraft.recipe.RecipeType.SMITHING) {
            return SMITHING;
        }

        return new RecipeType(recipeType);
    }

    public static RecipeType of(CompatIdentifier id) {
        return of(Registries.RECIPE_TYPE.get(id.toMinecraft()));
    }

    public net.minecraft.recipe.RecipeType<?> getRaw() {
        return recipeType;
    }

    public net.minecraft.recipe.RecipeType<?> toMinecraft() {
        return getRaw();
    }

    public CompatIdentifier getId() {
        return CompatIdentifier.fromMinecraft(Registries.RECIPE_TYPE.getId(recipeType));
    }

}
