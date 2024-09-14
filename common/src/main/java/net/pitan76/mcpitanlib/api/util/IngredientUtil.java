package net.pitan76.mcpitanlib.api.util;

import it.unimi.dsi.fastutil.ints.IntList;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.Registries;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class IngredientUtil {
    public static Ingredient fromTagByIdentifier(Identifier id) {
        return Ingredient.fromTag(TagKey.of(Registries.ITEM.getKey(), id));
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

    public static IntList getMatchingStacksIds(Ingredient ingredient) {
        return ingredient.getMatchingItemIds();
    }

    public static List<ItemStack> getMatchingStacksAsList(Ingredient ingredient) {
        return new ArrayList<>(Arrays.asList(getMatchingStacks(ingredient)));
    }

    public static ItemStack[] getMatchingStacks(Ingredient ingredient) {
        return ingredient.getMatchingStacks();
    }
}
