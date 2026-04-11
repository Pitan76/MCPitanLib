package net.pitan76.mcpitanlib.api.util;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.resources.Identifier;
import net.minecraft.core.NonNullList;
import net.pitan76.mcpitanlib.api.recipe.CompatibleRecipeEntry;
import net.pitan76.mcpitanlib.api.recipe.v3.CompatRecipe;
import net.pitan76.mcpitanlib.midohra.recipe.entry.RecipeEntry;

public class CompatibleRecipeEntryUtil {
    public static CompatibleRecipeEntry createShapelessRecipe(Identifier id, String group, RecipeUtil.CompatibilityCraftingRecipeCategory category, ItemStack output, NonNullList<Ingredient> input) {
        return new CompatibleRecipeEntry(id, group, category, RecipeUtil.createShapelessRecipe(id, group, category, output, input));
    }

    public static CompatibleRecipeEntry createShapelessRecipe(CompatIdentifier id, String group, RecipeUtil.CompatibilityCraftingRecipeCategory category, ItemStack output, NonNullList<Ingredient> input) {
        return createShapelessRecipe(id.toMinecraft(), group, category, output, input);
    }

    public static RecipeEntry createShapelessRecipeM(CompatIdentifier id, String group, RecipeUtil.CompatibilityCraftingRecipeCategory category, ItemStack output, NonNullList<Ingredient> input) {
        return RecipeEntry.of(RecipeUtil.createShapelessRecipe(id.toMinecraft(), group, category, output, input), id);
    }

    public static CompatRecipe createShapelessAsCompatRecipe(CompatIdentifier id, String group, RecipeUtil.CompatibilityCraftingRecipeCategory category, ItemStack output, NonNullList<Ingredient> input) {
        return new CompatRecipe(RecipeUtil.createShapelessRecipe(id.toMinecraft(), group, category, output, input), id.toMinecraft());
    }
}
