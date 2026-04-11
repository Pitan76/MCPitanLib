package net.pitan76.mcpitanlib.midohra.recipe;

import net.minecraft.core.registries.BuiltInRegistries;
import net.pitan76.mcpitanlib.api.util.CompatIdentifier;

public class RecipeType {
    private final net.minecraft.world.item.crafting.RecipeType recipeType;

    public static final RecipeType CRAFTING = new RecipeType(net.minecraft.world.item.crafting.RecipeType.CRAFTING);
    public static final RecipeType SMELTING = new RecipeType(net.minecraft.world.item.crafting.RecipeType.SMELTING);
    public static final RecipeType BLASTING = new RecipeType(net.minecraft.world.item.crafting.RecipeType.BLASTING);
    public static final RecipeType SMOKING = new RecipeType(net.minecraft.world.item.crafting.RecipeType.SMOKING);
    public static final RecipeType CAMPFIRE_COOKING = new RecipeType(net.minecraft.world.item.crafting.RecipeType.CAMPFIRE_COOKING);
    public static final RecipeType STONECUTTING = new RecipeType(net.minecraft.world.item.crafting.RecipeType.STONECUTTING);
    public static final RecipeType SMITHING = new RecipeType(net.minecraft.world.item.crafting.RecipeType.SMITHING);

    protected RecipeType(net.minecraft.world.item.crafting.RecipeType recipeType) {
        this.recipeType = recipeType;
    }

    public static RecipeType of(net.minecraft.world.item.crafting.RecipeType recipeType) {
        if (recipeType == net.minecraft.world.item.crafting.RecipeType.CRAFTING) {
            return CRAFTING;
        } else if (recipeType == net.minecraft.world.item.crafting.RecipeType.SMELTING) {
            return SMELTING;
        } else if (recipeType == net.minecraft.world.item.crafting.RecipeType.BLASTING) {
            return BLASTING;
        } else if (recipeType == net.minecraft.world.item.crafting.RecipeType.SMOKING) {
            return SMOKING;
        } else if (recipeType == net.minecraft.world.item.crafting.RecipeType.CAMPFIRE_COOKING) {
            return CAMPFIRE_COOKING;
        } else if (recipeType == net.minecraft.world.item.crafting.RecipeType.STONECUTTING) {
            return STONECUTTING;
        } else if (recipeType == net.minecraft.world.item.crafting.RecipeType.SMITHING) {
            return SMITHING;
        }

        return new RecipeType(recipeType);
    }

    public static RecipeType of(CompatIdentifier id) {
        return of(BuiltInRegistries.RECIPE_TYPE.getValue(id.toMinecraft()));
    }

    public net.minecraft.world.item.crafting.RecipeType getRaw() {
        return recipeType;
    }

    public net.minecraft.world.item.crafting.RecipeType toMinecraft() {
        return getRaw();
    }

    public CompatIdentifier getId() {
        return CompatIdentifier.fromMinecraft(BuiltInRegistries.RECIPE_TYPE.getKey(recipeType));
    }

}
