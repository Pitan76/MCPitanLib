package net.pitan76.mcpitanlib.api.util;

import it.unimi.dsi.fastutil.ints.IntArrayList;
import it.unimi.dsi.fastutil.ints.IntList;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.Registries;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.entry.RegistryEntryList;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class IngredientUtil {
    public static Ingredient fromTagByIdentifier(Identifier id) {
        List<Item> items = ItemUtil.getItems(id);

        List<RegistryEntry<Item>> entryList = new ArrayList<>();
        for (Item item : items) {
            entryList.add(Registries.ITEM.getEntry(item));
        }

        RegistryEntryList<Item> entryList2 = RegistryEntryList.of(entryList);

        return Ingredient.fromTag(entryList2);
    }

    public static Ingredient fromTagByString(String id) {
        return fromTagByIdentifier(IdentifierUtil.id(id));
    }

    public static Ingredient fromTagByIdentifier(CompatIdentifier id) {
        return fromTagByIdentifier(id.toMinecraft());
    }

    public static List<Item> getItems(Ingredient ingredient) {
        List<Item> items = new ArrayList<>();

        for (RegistryEntry<Item> entry : ingredient.getMatchingItems()) {
            items.add(entry.value());
        }

        return items;
    }

    public static IntList getMatchingStacksIds(Ingredient ingredient) {
        IntList ids = new IntArrayList();

        for (Item item : getItems(ingredient)) {
            ids.add(ItemUtil.getRawId(item));
        }

        return ids;
    }

    public static List<ItemStack> getMatchingStacksAsList(Ingredient ingredient) {
        return new ArrayList<>(Arrays.asList(getMatchingStacks(ingredient)));
    }

    public static ItemStack[] getMatchingStacks(Ingredient ingredient) {
        List<ItemStack> stacks = new ArrayList<>();
        for (Item item : getItems(ingredient)) {
            stacks.add(new ItemStack(item));
        }

        return stacks.toArray(new ItemStack[0]);
    }

    public static Ingredient empty() {
        return null;
    }
}
