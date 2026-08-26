package net.pitan76.mcpitanlib.api.potion.forge;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionUtil;
import net.minecraft.recipe.Ingredient;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;
import net.minecraftforge.common.crafting.ingredients.StrictNBTIngredient;

/**
 * このプラットフォームはItemStack同士でレシピを組むため、ポーションのStackに変換して登録する。
 */
public class BrewingRecipeUtilImpl {

    public static void registerPotionRecipe(Potion input, Item ingredient, Potion output) {
        ItemStack inputStack = PotionUtil.setPotion(new ItemStack(Items.POTION), input);
        ItemStack outputStack = PotionUtil.setPotion(new ItemStack(Items.POTION), output);

        BrewingRecipeRegistry.addRecipe(StrictNBTIngredient.of(inputStack),
                Ingredient.ofItems(ingredient), outputStack);
    }

    public static void registerItemRecipe(Item input, Item ingredient, Item output) {
        BrewingRecipeRegistry.addRecipe(Ingredient.ofItems(input), Ingredient.ofItems(ingredient), new ItemStack(output));
    }
}
