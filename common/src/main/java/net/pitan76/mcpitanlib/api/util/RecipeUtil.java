package net.pitan76.mcpitanlib.api.util;

import net.minecraft.inventory.CraftingInventory;
import com.mojang.datafixers.util.Pair;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;
import net.pitan76.mcpitanlib.api.recipe.CompatRecipeType;
import net.pitan76.mcpitanlib.api.recipe.MatchGetter;
import net.pitan76.mcpitanlib.api.recipe.input.CompatRecipeInput;
import net.pitan76.mcpitanlib.api.recipe.v2.CompatRecipeEntry;
import net.pitan76.mcpitanlib.api.registry.CompatRegistryLookup;
import net.pitan76.mcpitanlib.api.util.collection.ItemStackList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class RecipeUtil {
    public static ShapelessRecipe createShapelessRecipe(Identifier id, String group, CompatibilityCraftingRecipeCategory category, ItemStack output, DefaultedList<Ingredient> input) {
        return createShapelessRecipe(id, group, output, input);
    }

    public static ShapelessRecipe createShapelessRecipe(Identifier id, String group, ItemStack output, DefaultedList<Ingredient> input) {
        return new ShapelessRecipe(id, group, output, input);
    }

    public static <C extends Inventory> ItemStack craft_2(Recipe<C> recipe, C inventory, World world) {
        return recipe.craft(inventory);
    }

    public static <C extends Inventory> ItemStack getOutput_2(Recipe<C> recipe, World world) {
        return recipe.getOutput();
    }

    public static ItemStack craft(Recipe<?> recipe, Inventory inventory, World world) {
        if (inventory instanceof CraftingInventory) {
            Recipe<CraftingInventory> inputRecipe = (Recipe<CraftingInventory>) recipe;
            return inputRecipe.craft((CraftingInventory) inventory);
        }

        return ItemStack.EMPTY;
    }

    public static ItemStack getOutput(Recipe<?> recipe, World world) {
        return recipe.getOutput();
    }

    public static List<Recipe<?>> getAllRecipes(World world) {
        Collection<Recipe<?>> recipes = world.getRecipeManager().values();
        return new ArrayList<>(recipes);
    }

    public static RecipeType<?> getType(Recipe<?> recipe) {
        return recipe.getType();
    }

    public static Identifier getId(Recipe<?> recipe) {
        return IdentifierUtil.id(recipe.getClass().hashCode() + "");
    }

    public static <I extends Inventory, T extends Recipe<I>> CompatRecipeEntry<I> getFirstMatch(RecipeManager recipeManager, CompatRecipeType<T> type, CompatRecipeInput<I> input, World world) {
        Optional<T> recipe = recipeManager.getFirstMatch(type.getType(), input.getInput(), world);
        return recipe.map(CompatRecipeEntry::new).orElseGet(() -> new CompatRecipeEntry<>(null));
    }

    public static <I extends Inventory, T extends Recipe<I>> CompatRecipeEntry<I> getFirstMatch(RecipeManager recipeManager, CompatRecipeType<T> type, CompatRecipeInput<I> input, World world, CompatIdentifier identifier) {
        Optional<Pair<Identifier, T>> recipe = recipeManager.getFirstMatch(type.getType(), input.getInput(), world, identifier.toMinecraft());
        return recipe.map((r) -> new CompatRecipeEntry<>(r.getSecond())).orElseGet(() -> new CompatRecipeEntry<>(null));
    }

    public static <I extends Inventory, T extends Recipe<I>> CompatRecipeEntry<I> getFirstMatch(World world, CompatRecipeType<T> type, CompatRecipeInput<I> input) {
        return getFirstMatch(getRecipeManager(world), type, input, world);
    }

    public static <I extends Inventory, T extends Recipe<I>> CompatRecipeEntry<I> getFirstMatch(World world, CompatRecipeType<T> type, CompatRecipeInput<I> input, CompatIdentifier identifier) {
        return getFirstMatch(getRecipeManager(world), type, input, world, identifier);
    }

    public static RecipeManager getRecipeManager(World world) {
        return world.getRecipeManager();
    }

    public Optional<Recipe<?>> get(World world, CompatIdentifier id) {
        return get(getRecipeManager(world), id);
    }

    public Optional<Recipe<?>> get(RecipeManager recipeManager, CompatIdentifier id) {
        return (Optional<Recipe<?>>) recipeManager.get(id.toMinecraft());
    }

    public static <I extends Inventory, T extends Recipe<I>> MatchGetter<I, T> createCachedMatchGetter(RecipeType<T> type) {
        return (input, world) -> {
            Optional<T> optional = RecipeManager.createCachedMatchGetter(type).getFirstMatch(input.getInput(), world);
            return Optional.of(optional.map(CompatRecipeEntry::new).orElseGet(() -> new CompatRecipeEntry<>(null)));
        };
    }

    public static <I extends Inventory, T extends Recipe<I>> MatchGetter<I, T> createCachedMatchGetter(CompatRecipeType<T> type) {
        return createCachedMatchGetter(type.getType());
    }

    public static DefaultedList<Ingredient> getInputs(Recipe<?> recipe) {
        return recipe.getIngredients();
    }

    public static DefaultedList<Ingredient> getInputs(CompatRecipeEntry<?> recipeEntry) {
        return getInputs(recipeEntry.getRecipe());
    }

    public static ItemStackList getInputsAsStack(Recipe<?> recipe) {
        DefaultedList<Ingredient> ingredients = getInputs(recipe);
        ItemStackList stacks = ItemStackList.ofSize(ingredients.size(), ItemStackUtil.empty());
        for (Ingredient ingredient : ingredients) {
            stacks.addAll(IngredientUtil.getMatchingStacksAsList(ingredient));
        }
        return stacks;
    }

    public static ItemStackList getInputsAsStack(CompatRecipeEntry<?> recipeEntry) {
        return getInputsAsStack(recipeEntry.getRecipe());
    }

    public static ItemStack getOutput(Recipe<?> recipe, CompatRegistryLookup registryLookup) {
        return recipe.getOutput();
    }

    public static ItemStack getOutput(CompatRecipeEntry<?> recipeEntry, CompatRegistryLookup registryLookup) {
        return recipeEntry.getRecipe().getOutput();
    }

    public static CompatRecipeType<?> getType(CompatRecipeEntry<?> recipeEntry) {
        return CompatRecipeType.of(recipeEntry.getRecipe().getType());
    }

    public static  <I extends Inventory, T extends Recipe<I>> ItemStackList getRemainder(CompatRecipeEntry<I> recipeEntry, CompatRecipeInput<I> input) {
        return ItemStackList.of(recipeEntry.getRecipe().getRemainder(input.getInput()));
    }

    public enum CompatibilityCraftingRecipeCategory {
        BUILDING,
        REDSTONE,
        EQUIPMENT,
        MISC;
    }
}
