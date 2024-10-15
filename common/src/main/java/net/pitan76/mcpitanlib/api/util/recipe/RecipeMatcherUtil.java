package net.pitan76.mcpitanlib.api.util.recipe;

import it.unimi.dsi.fastutil.ints.IntList;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeMatcher;
import net.pitan76.mcpitanlib.api.recipe.CompatibleRecipeEntry;
import net.pitan76.mcpitanlib.api.util.ItemStackUtil;
import net.pitan76.mcpitanlib.api.util.ItemUtil;

public class RecipeMatcherUtil {
    public static ItemStack getStackFromId(int itemId) {
        return ItemStackUtil.create(ItemUtil.fromIndex(itemId));
    }

    public static int getItemId(ItemStack stack) {
        return ItemUtil.getRawId(stack.getItem());
    }

    public static boolean match(RecipeMatcher<?> matcher, CompatibleRecipeEntry entry, IntList output) {
        return match(matcher, entry.getRecipe(), output);
    }

    public static boolean match(RecipeMatcher<?> matcher, CompatibleRecipeEntry entry, IntList output, int multiplier) {
        return match(matcher, entry.getRecipe(), output, multiplier);
    }

    @Deprecated
    public static boolean match(RecipeMatcher<?> matcher, Recipe<?> recipe, IntList output) {
        return false;
        //return matcher.match(recipe, output);
    }

    @Deprecated
    public static boolean match(RecipeMatcher<?> matcher, Recipe<?> recipe, IntList output, int multiplier) {
        return false;
        //return matcher.match(recipe, output, multiplier);
    }

    public static void clear(RecipeMatcher<?> matcher) {
        matcher.clear();
    }
}
