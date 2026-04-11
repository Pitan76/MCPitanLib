package net.pitan76.mcpitanlib.api.util;

import net.minecraft.core.NonNullList;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import net.pitan76.mcpitanlib.api.recipe.CompatRecipeType;
import net.pitan76.mcpitanlib.api.recipe.MatchGetter;
import net.pitan76.mcpitanlib.api.recipe.input.CompatRecipeInput;
import net.pitan76.mcpitanlib.api.recipe.v2.CompatRecipeEntry;
import net.pitan76.mcpitanlib.api.recipe.v2.CompatRecipeNonEntry;
import net.pitan76.mcpitanlib.api.registry.CompatRegistryLookup;
import net.pitan76.mcpitanlib.api.util.collection.ItemStackList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class RecipeUtil {
    public static ShapelessRecipe createShapelessRecipe(Identifier id, String group, CompatibilityCraftingRecipeCategory category, ItemStack output, NonNullList<Ingredient> input) {
        CraftingRecipe.CraftingBookInfo craftingBookInfo = new CraftingRecipe.CraftingBookInfo(CraftingBookCategory.valueOf(category.name()), group);
        Recipe.CommonInfo commonInfo = new Recipe.CommonInfo(false);
        ItemStackTemplate outputTemplate = ItemStackTemplate.fromNonEmptyStack(output);
        return new ShapelessRecipe(commonInfo, craftingBookInfo, outputTemplate, input);
    }

    public static ShapelessRecipe createShapelessRecipe(Identifier id, String group, ItemStack output, NonNullList<Ingredient> input) {
        return createShapelessRecipe(id, group, CompatibilityCraftingRecipeCategory.MISC, output, input);
    }

    @Deprecated
    public static <C extends RecipeInput> ItemStack craft_2(Recipe<C> recipe, C inventory, Level world) {
        return recipe.assemble(inventory);
    }

    @Deprecated
    public static <C extends RecipeInput> ItemStack getOutput_2(Recipe<C> recipe, Level world) {
        return craft_2(recipe, (C) CraftingInput.EMPTY, world);
    }

    public static ItemStack craft(Recipe<?> recipe, Container inventory, Level world) {
        if (inventory instanceof RecipeInput) {
            Recipe<RecipeInput> inputRecipe = (Recipe<RecipeInput>) recipe;
            return inputRecipe.assemble((RecipeInput) inventory);
        }
        return ItemStack.EMPTY;
    }

    public static ItemStack getOutput(Recipe<?> recipe, Level world) {
        return getOutput(recipe, RegistryLookupUtil.getRegistryLookup(world));
    }

    public static List<Recipe<?>> getAllRecipes(Level world) {
        RecipeAccess iRecipeManager = getRecipeManager(world);
        if (!(iRecipeManager instanceof RecipeManager))
            return new ArrayList<>();

        RecipeManager recipeManager = (RecipeManager) iRecipeManager;

        Collection<RecipeHolder<?>> recipes = recipeManager.getRecipes();
        List<Recipe<?>> outRecipes = new ArrayList<>();
        for (Object recipeEntryObj : recipes) {
            if (recipeEntryObj instanceof RecipeHolder) {
                RecipeHolder<?> recipeEntry = (RecipeHolder<?>) recipeEntryObj;
                if (recipeEntry.value() instanceof Recipe) {
                    outRecipes.add(recipeEntry.value());
                }
            }
        }
        return outRecipes;
    }

    public static List<CompatRecipeNonEntry<?>> getAllCompatRecipeEntry(Level world) {
        List<Recipe<?>> recipes = getAllRecipes(world);
        List<CompatRecipeNonEntry<?>> outRecipes = new ArrayList<>();
        for (Recipe<?> recipe : recipes) {
            outRecipes.add(new CompatRecipeNonEntry<>(recipe));
        }
        return outRecipes;
    }

    public static RecipeType<?> getType(Recipe<?> recipe) {
        return recipe.getType();
    }

    public static Identifier getId(Recipe<?> recipe) {
        return IdentifierUtil.id(recipe.getClass().hashCode() + "");
    }

    public static <I extends RecipeInput, T extends Recipe<I>> CompatRecipeEntry<T> getFirstMatch(RecipeAccess iRecipeManager, CompatRecipeType<T> type, CompatRecipeInput<I> input, Level world) {
        if (!(iRecipeManager instanceof RecipeManager))
            return new CompatRecipeEntry<>(null);

        RecipeManager recipeManager = (RecipeManager) iRecipeManager;

        Optional<RecipeHolder<T>> recipe = recipeManager.getRecipeFor(type.getType(), input.getInput(), world);
        return recipe.map(CompatRecipeEntry::new).orElseGet(() -> new CompatRecipeEntry<>(null));
    }

    public static <I extends RecipeInput, T extends Recipe<I>> CompatRecipeEntry<T> getFirstMatch(RecipeAccess iRecipeManager, CompatRecipeType<T> type, CompatRecipeInput<I> input, Level world, CompatIdentifier identifier) {
        if (!(iRecipeManager instanceof RecipeManager))
            return new CompatRecipeEntry<>(null);

        RecipeManager recipeManager = (RecipeManager) iRecipeManager;

        Optional<RecipeHolder<T>> recipe = recipeManager.getRecipeFor(type.getType(), input.getInput(), world, ResourceKey.create(Registries.RECIPE, identifier.toMinecraft()));
        return recipe.map(CompatRecipeEntry::new).orElseGet(() -> new CompatRecipeEntry<>(null));
    }

    public static <I extends RecipeInput, T extends Recipe<I>> CompatRecipeEntry<T> getFirstMatch(Level world, CompatRecipeType<T> type, CompatRecipeInput<I> input) {
        return getFirstMatch(getRecipeManager(world), type, input, world);
    }

    public static <I extends RecipeInput, T extends Recipe<I>> CompatRecipeEntry<T> getFirstMatch(Level world, CompatRecipeType<T> type, CompatRecipeInput<I> input, CompatIdentifier identifier) {
        return getFirstMatch(getRecipeManager(world), type, input, world, identifier);
    }

    public static RecipeAccess getRecipeManager(Level world) {
        return world.recipeAccess();
    }

    public Optional<RecipeHolder<?>> get(Level world, CompatIdentifier id) {
        return get(getRecipeManager(world), id);
    }

    public Optional<RecipeHolder<?>> get(RecipeAccess iRecipeManager, CompatIdentifier id) {
        if (!(iRecipeManager instanceof RecipeManager))
            return Optional.empty();
        RecipeManager recipeManager = (RecipeManager) iRecipeManager;

        return recipeManager.byKey(ResourceKey.create(Registries.RECIPE, id.toMinecraft()));
    }

    public static <I extends RecipeInput, T extends Recipe<I>> MatchGetter<I, T> createCachedMatchGetter(RecipeType<T> type) {
        return (input, world) -> {
            Optional<RecipeHolder<T>> optional = RecipeManager.createCheck(type).getRecipeFor(input.getInput(), (ServerLevel) world);
            return optional.map(CompatRecipeEntry::new);
        };
    }

    public static <I extends RecipeInput, T extends Recipe<I>> MatchGetter<I, T> createCachedMatchGetter(CompatRecipeType<T> type) {
        return createCachedMatchGetter(type.getType());
    }

    public static NonNullList<Ingredient> getInputs(Recipe<?> recipe) {
        List<Ingredient> ingredients = recipe.placementInfo().ingredients();

        NonNullList<Ingredient> outIngredients = NonNullList.createWithCapacity(ingredients.size());

        for (int i = 0; i < ingredients.size(); i++) {
            outIngredients.set(i, ingredients.get(i));
        }

        return outIngredients;
    }

    public static NonNullList<Ingredient> getInputs(CompatRecipeEntry<?> recipeEntry) {
        return getInputs(recipeEntry.getRecipe());
    }

    public static ItemStackList getInputsAsStack(Recipe<?> recipe) {
        NonNullList<Ingredient> ingredients = getInputs(recipe);
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
        if (recipe instanceof ShapelessRecipe) {
            ShapelessRecipe shapelessRecipe = (ShapelessRecipe) recipe;
            return shapelessRecipe.assemble(CraftingInput.EMPTY);
        }

        if (recipe instanceof ShapedRecipe) {
            ShapedRecipe shapedRecipe = (ShapedRecipe) recipe;
            return shapedRecipe.assemble(CraftingInput.EMPTY);
        }

        if (recipe instanceof CraftingRecipe) {
            CraftingRecipe craftingRecipe = (CraftingRecipe) recipe;
            return craftingRecipe.assemble(CraftingInput.EMPTY);
        }

        if (recipe instanceof CustomRecipe) {
            CustomRecipe specialCraftingRecipe = (CustomRecipe) recipe;
            return specialCraftingRecipe.assemble(CraftingInput.EMPTY);
        }

        return recipe.assemble(null);
    }

    public static ItemStack getOutput(CompatRecipeEntry<?> recipeEntry, CompatRegistryLookup registryLookup) {
        return getOutput(recipeEntry.getRecipe(), registryLookup);
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
