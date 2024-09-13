package net.pitan76.mcpitanlib.api.util;

import it.unimi.dsi.fastutil.ints.IntList;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import net.minecraft.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class IngredientUtil {
    public static Ingredient fromTagByIdentifier(Identifier id) {
        return Ingredient.fromTag(TagKey.of(Registry.ITEM_KEY, id));
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
        return new ArrayList<>(Arrays.asList(getMatchingStacks(ingredient)));
    }

    public ItemStack[] getMatchingStacks(Ingredient ingredient) {
        return ingredient.getMatchingStacks();
    }
}
