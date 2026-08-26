package net.pitan76.mcpitanlib.api.potion.fabric;

import net.fabricmc.fabric.api.registry.FabricBrewingRecipeRegistry;
import net.minecraft.item.Item;
import net.minecraft.item.PotionItem;
import net.minecraft.potion.Potion;
import net.minecraft.recipe.Ingredient;

public class BrewingRecipeUtilImpl {

    public static void registerPotionRecipe(Potion input, Item ingredient, Potion output) {
        FabricBrewingRecipeRegistry.registerPotionRecipe(input, Ingredient.ofItems(ingredient), output);
    }

    public static void registerItemRecipe(Item input, Item ingredient, Item output) {
        if (!(input instanceof PotionItem) || !(output instanceof PotionItem)) return;

        FabricBrewingRecipeRegistry.registerItemRecipe((PotionItem) input, Ingredient.ofItems(ingredient), (PotionItem) output);
    }
}
