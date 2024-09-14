package net.pitan76.mcpitanlib.api.util;

import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DefaultedList;
import net.pitan76.mcpitanlib.api.recipe.CompatibleRecipeEntry;

public class CompatibleRecipeEntryUtil {
    public static CompatibleRecipeEntry createShapelessRecipe(Identifier id, String group, RecipeUtil.CompatibilityCraftingRecipeCategory category, ItemStack output, DefaultedList<Ingredient> input) {
        return new CompatibleRecipeEntry(id, group, category, RecipeUtil.createShapelessRecipe(id, group, category, output, input));
    }

    public static CompatibleRecipeEntry createShapelessRecipe(CompatIdentifier id, String group, RecipeUtil.CompatibilityCraftingRecipeCategory category, ItemStack output, DefaultedList<Ingredient> input) {
        return createShapelessRecipe(id.toMinecraft(), group, category, output, input);
    }
}
