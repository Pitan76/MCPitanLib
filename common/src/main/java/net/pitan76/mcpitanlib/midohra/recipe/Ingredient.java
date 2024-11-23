package net.pitan76.mcpitanlib.midohra.recipe;

import net.minecraft.item.Item;
import net.pitan76.mcpitanlib.api.util.CompatIdentifier;
import net.pitan76.mcpitanlib.api.util.IngredientUtil;
import net.pitan76.mcpitanlib.midohra.item.ItemStack;
import net.pitan76.mcpitanlib.midohra.item.ItemWrapper;

import java.util.ArrayList;
import java.util.List;

public class Ingredient {
    private final net.minecraft.recipe.Ingredient ingredient;

    protected Ingredient(net.minecraft.recipe.Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    public static Ingredient of(net.minecraft.recipe.Ingredient ingredient) {
        return new Ingredient(ingredient);
    }

    public static Ingredient ofItems(Item... items) {
        return of(net.minecraft.recipe.Ingredient.ofItems(items));
    }

    public static Ingredient ofItem(Item items) {
        return of(net.minecraft.recipe.Ingredient.ofItem(items));
    }

    public static Ingredient fromTagById(CompatIdentifier id) {
        return of(IngredientUtil.fromTagByIdentifier(id));
    }

    public static Ingredient fromTagByString(String id) {
        return of(IngredientUtil.fromTagByString(id));
    }

    public net.minecraft.recipe.Ingredient getRaw() {
        return ingredient;
    }

    public net.minecraft.recipe.Ingredient toMinecraft() {
        return getRaw();
    }

    public boolean test(net.minecraft.item.ItemStack stack) {
        return ingredient.test(stack);
    }

    public boolean equals(Ingredient other) {
        return ingredient.equals(other.getRaw());
    }

    public List<Item> getMatchingItems() {
        return IngredientUtil.getItems(ingredient);
    }

    public it.unimi.dsi.fastutil.ints.IntList getMatchingStacksIds() {
        return IngredientUtil.getMatchingStacksIds(ingredient);
    }

    public List<net.minecraft.item.ItemStack> getMatchingStacksAsList() {
        return IngredientUtil.getMatchingStacksAsList(ingredient);
    }

    public net.minecraft.item.ItemStack[] getMatchingStacks() {
        return IngredientUtil.getMatchingStacks(ingredient);
    }

    public List<ItemWrapper> getMatchingItemWrappers() {
        List<ItemWrapper> wrappers = new ArrayList<>();

        for (Item item : getMatchingItems()) {
            wrappers.add(ItemWrapper.of(item));
        }

        return wrappers;
    }

    public List<ItemStack> getMatchingStacksAsMidohraList() {
        List<ItemStack> stacks = new ArrayList<>();

        for (net.minecraft.item.ItemStack stack : getMatchingStacksAsList()) {
            stacks.add(ItemStack.of(stack));
        }

        return stacks;
    }

    public ItemStack[] getMatchingStacksAsMidohra() {
        List<ItemStack> stacks = getMatchingStacksAsMidohraList();
        return stacks.toArray(new ItemStack[0]);
    }
}
