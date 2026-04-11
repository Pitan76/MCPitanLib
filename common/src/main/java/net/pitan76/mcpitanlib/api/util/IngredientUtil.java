package net.pitan76.mcpitanlib.api.util;

import it.unimi.dsi.fastutil.ints.IntArrayList;
import it.unimi.dsi.fastutil.ints.IntList;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.resources.Identifier;
import net.minecraft.core.NonNullList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class IngredientUtil {
    public static Ingredient fromTagByIdentifier(Identifier id) {
        List<Item> items = ItemUtil.getItems(id);

        List<Holder<Item>> entryList = new ArrayList<>();
        for (Item item : items) {
            entryList.add(BuiltInRegistries.ITEM.wrapAsHolder(item));
        }

        HolderSet<Item> entryList2 = HolderSet.direct(entryList);

        return Ingredient.of(entryList2);
    }

    public static Ingredient fromTagByString(String id) {
        return fromTagByIdentifier(IdentifierUtil.id(id));
    }

    public static Ingredient fromTagByIdentifier(CompatIdentifier id) {
        return fromTagByIdentifier(id.toMinecraft());
    }

    public static List<Item> getItems(Ingredient ingredient) {
        List<Item> items = new ArrayList<>();

        for (Holder<Item> entry : ingredient.items().toList()) {
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

    public static Ingredient ofItems(ItemLike... items) {
        return Ingredient.of(items);
    }

    public static NonNullList<Ingredient> buildInput(Object[] input) {
        NonNullList<Ingredient> list = NonNullList.create();
        for (Object obj : input) {
            if (obj instanceof Ingredient) {
                list.add((Ingredient) obj);
                continue;
            }

            if (obj instanceof ItemLike) {
                list.add(ofItems((ItemLike) obj));
            }
        }
        return list;
    }
}
