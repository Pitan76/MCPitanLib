package net.pitan76.mcpitanlib.api.util;

import it.unimi.dsi.fastutil.ints.IntList;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import net.minecraft.tag.ItemTags;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class IngredientUtil {
    public static Ingredient fromTagByIdentifier(Identifier id) {
        return Ingredient.fromTag(ItemTags.getTagGroup().getTagOrEmpty(id));
    }

    public static Ingredient fromTagByString(String id) {
        return fromTagByIdentifier(new Identifier(id));
    }

    public static Ingredient fromTagByIdentifier(CompatIdentifier id) {
        return fromTagByIdentifier(id.toMinecraft());
    }

    public static List<Item> getItems(Ingredient ingredient) {
        List<Item> items = new ArrayList<>();
        for (int rawId : ingredient.getMatchingItemIds()) {
            try {
                items.add(ItemUtil.fromIndex(rawId));
            } catch (Exception ignored) {}
        }
        return items;
    }

    public IntList getMatchingStacksIds(Ingredient ingredient) {
        return ingredient.getMatchingItemIds();
    }

    public List<ItemStack> getMatchingStacksAsList(Ingredient ingredient) {
        List<ItemStack> stacks = new ArrayList<>();

        for (Item item : getItems(ingredient)) {
            stacks.add(new ItemStack(item));
        }

        return stacks;
    }

    public ItemStack[] getMatchingStacks(Ingredient ingredient) {
        return getMatchingStacksAsList(ingredient).toArray(new ItemStack[0]);
    }
}
