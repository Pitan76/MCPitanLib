package net.pitan76.mcpitanlib.api.util;

import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.*;
import net.minecraft.recipe.book.CraftingRecipeCategory;
import net.minecraft.recipe.input.CraftingRecipeInput;
import net.minecraft.recipe.input.RecipeInput;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.server.world.ServerWorld;
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
        return new ShapelessRecipe(group, CraftingRecipeCategory.valueOf(category.name()), output, input);
    }

    public static ShapelessRecipe createShapelessRecipe(Identifier id, String group, ItemStack output, DefaultedList<Ingredient> input) {
        return createShapelessRecipe(id, group, CompatibilityCraftingRecipeCategory.MISC, output, input);
    }

    @Deprecated
    public static <C extends RecipeInput> ItemStack craft_2(Recipe<C> recipe, C inventory, World world) {
        return recipe.craft(inventory, world.getRegistryManager());
    }

    @Deprecated
    public static <C extends RecipeInput> ItemStack getOutput_2(Recipe<C> recipe, World world) {
        return craft_2(recipe, (C) CraftingRecipeInput.EMPTY, world);
    }

    public static ItemStack craft(Recipe<?> recipe, Inventory inventory, World world) {
        if (inventory instanceof RecipeInput) {
            Recipe<RecipeInput> inputRecipe = (Recipe<RecipeInput>) recipe;
            return inputRecipe.craft((RecipeInput) inventory, world.getRegistryManager());
        }
        return ItemStack.EMPTY;
    }

    public static ItemStack getOutput(Recipe<?> recipe, World world) {
        return recipe.craft(null, world.getRegistryManager());
    }

    public static List<Recipe<?>> getAllRecipes(World world) {
        RecipeManager iRecipeManager = getRecipeManager(world);
        if (!(iRecipeManager instanceof ServerRecipeManager))
            return new ArrayList<>();

        ServerRecipeManager recipeManager = (ServerRecipeManager) iRecipeManager;

        Collection<RecipeEntry<?>> recipes = recipeManager.values();
        List<Recipe<?>> outRecipes = new ArrayList<>();
        for (Object recipeEntryObj : recipes) {
            if (recipeEntryObj instanceof RecipeEntry) {
                RecipeEntry<?> recipeEntry = (RecipeEntry<?>) recipeEntryObj;
                if (recipeEntry.value() instanceof Recipe) {
                    outRecipes.add(recipeEntry.value());
                }
            }
        }
        return outRecipes;
    }

    public static RecipeType<?> getType(Recipe<?> recipe) {
        return recipe.getType();
    }

    public static Identifier getId(Recipe<?> recipe) {
        return IdentifierUtil.id(recipe.getClass().hashCode() + "");
    }

    public static <I extends RecipeInput, T extends Recipe<I>> CompatRecipeEntry<T> getFirstMatch(RecipeManager iRecipeManager, CompatRecipeType<T> type, CompatRecipeInput<I> input, World world) {
        if (!(iRecipeManager instanceof ServerRecipeManager))
            return new CompatRecipeEntry<>(null);

        ServerRecipeManager recipeManager = (ServerRecipeManager) iRecipeManager;

        Optional<RecipeEntry<T>> recipe = recipeManager.getFirstMatch(type.getType(), input.getInput(), world);
        return recipe.map(CompatRecipeEntry::new).orElseGet(() -> new CompatRecipeEntry<>(null));
    }

    public static <I extends RecipeInput, T extends Recipe<I>> CompatRecipeEntry<T> getFirstMatch(RecipeManager iRecipeManager, CompatRecipeType<T> type, CompatRecipeInput<I> input, World world, CompatIdentifier identifier) {
        if (!(iRecipeManager instanceof ServerRecipeManager))
            return new CompatRecipeEntry<>(null);

        ServerRecipeManager recipeManager = (ServerRecipeManager) iRecipeManager;

        Optional<RecipeEntry<T>> recipe = recipeManager.getFirstMatch(type.getType(), input.getInput(), world, RegistryKey.of(RegistryKeys.RECIPE, identifier.toMinecraft()));
        return recipe.map(CompatRecipeEntry::new).orElseGet(() -> new CompatRecipeEntry<>(null));
    }

    public static <I extends RecipeInput, T extends Recipe<I>> CompatRecipeEntry<T> getFirstMatch(World world, CompatRecipeType<T> type, CompatRecipeInput<I> input) {
        return getFirstMatch(getRecipeManager(world), type, input, world);
    }

    public static <I extends RecipeInput, T extends Recipe<I>> CompatRecipeEntry<T> getFirstMatch(World world, CompatRecipeType<T> type, CompatRecipeInput<I> input, CompatIdentifier identifier) {
        return getFirstMatch(getRecipeManager(world), type, input, world, identifier);
    }

    public static RecipeManager getRecipeManager(World world) {
        return world.getRecipeManager();
    }

    public Optional<RecipeEntry<?>> get(World world, CompatIdentifier id) {
        return get(getRecipeManager(world), id);
    }

    public Optional<RecipeEntry<?>> get(RecipeManager iRecipeManager, CompatIdentifier id) {
        if (!(iRecipeManager instanceof ServerRecipeManager))
            return Optional.empty();
        ServerRecipeManager recipeManager = (ServerRecipeManager) iRecipeManager;

        return recipeManager.get(RegistryKey.of(RegistryKeys.RECIPE, id.toMinecraft()));
    }

    public static <I extends RecipeInput, T extends Recipe<I>> MatchGetter<I, T> createCachedMatchGetter(RecipeType<T> type) {
        return (input, world) -> {
            Optional<RecipeEntry<T>> optional = ServerRecipeManager.createCachedMatchGetter(type).getFirstMatch(input.getInput(), (ServerWorld) world);
            return optional.map(CompatRecipeEntry::new);
        };
    }

    public static <I extends RecipeInput, T extends Recipe<I>> MatchGetter<I, T> createCachedMatchGetter(CompatRecipeType<T> type) {
        return createCachedMatchGetter(type.getType());
    }

    public static DefaultedList<Ingredient> getInputs(Recipe<?> recipe) {
        List<Ingredient> ingredients = recipe.getIngredientPlacement().getIngredients();

        DefaultedList<Ingredient> outIngredients = DefaultedList.ofSize(ingredients.size());

        for (int i = 0; i < ingredients.size(); i++) {
            outIngredients.set(i, ingredients.get(i));
        }

        return outIngredients;
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
        return recipe.craft(null, registryLookup.getRegistryLookup());
    }

    public static ItemStack getOutput(CompatRecipeEntry<?> recipeEntry, CompatRegistryLookup registryLookup) {
        return recipeEntry.getRecipe().craft(null, registryLookup.getRegistryLookup());
    }

    public static CompatRecipeType<?> getType(CompatRecipeEntry<?> recipeEntry) {
        return CompatRecipeType.of(recipeEntry.getRecipe().getType());
    }

    public static  <I extends RecipeInput, T extends Recipe<I>> ItemStackList getRemainder(CompatRecipeEntry<T> recipeEntry, CompatRecipeInput<I> input) {
        return ItemStackList.of();
        //return ItemStackList.of(recipeEntry.getRecipe().getRemainder(input.getInput()));
    }

    public enum CompatibilityCraftingRecipeCategory {
        BUILDING,
        REDSTONE,
        EQUIPMENT,
        MISC;
    }
}
