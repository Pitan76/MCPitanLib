package net.pitan76.mcpitanlib.api.potion.forge;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.Potion;
import net.minecraft.recipe.Ingredient;

/**
 * このプラットフォームはItemStack同士でレシピを組むため、ポーションのStackに変換して登録する。
 */
public class BrewingRecipeUtilImpl {

    public static void registerPotionRecipe(Potion input, Item ingredient, Potion output) {
        net.minecraftforge.common.brewing.BrewingRecipeRegistry.addRecipe(
                Ingredient.ofStacks(net.minecraft.potion.PotionUtil.setPotion(new ItemStack(Items.POTION), input)),
                Ingredient.ofItems(ingredient),
                net.minecraft.potion.PotionUtil.setPotion(new ItemStack(Items.POTION), output));
    }

    public static void registerItemRecipe(Item input, Item ingredient, Item output) {
        net.minecraftforge.common.brewing.BrewingRecipeRegistry.addRecipe(Ingredient.ofItems(input), Ingredient.ofItems(ingredient), new ItemStack(output));
    }
}
