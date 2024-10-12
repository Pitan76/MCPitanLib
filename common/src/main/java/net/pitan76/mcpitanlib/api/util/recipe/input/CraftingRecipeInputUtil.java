package net.pitan76.mcpitanlib.api.util.recipe.input;

import net.minecraft.inventory.CraftingInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.RecipeMatcher;
import net.pitan76.mcpitanlib.api.recipe.input.CompatRecipeInput;
import net.pitan76.mcpitanlib.api.util.ItemStackUtil;
import net.pitan76.mcpitanlib.api.util.collection.ItemStackList;

import java.util.List;
import java.util.Optional;

public class CraftingRecipeInputUtil {
    public static Optional<CraftingInventory> get(CompatRecipeInput<?> input) {
        if (input.getInput() instanceof CraftingInventory) {
            return Optional.of((CraftingInventory) input.getInput());
        }
        return Optional.empty();
    }

    public static CompatRecipeInput<?> create(CraftingInventory input) {
        return new CompatRecipeInput<>(input);
    }

    public static CompatRecipeInput<?> create(int width, int height, List<ItemStack> stacks) {
        ItemStackList stackList = ItemStackList.ofSize(stacks.size(), ItemStackUtil.empty());
        for (int i = 0; i < stacks.size(); i++) {
            stackList.set(i, stacks.get(i));
        }

        return new CompatRecipeInput<>(new CraftingInventory(null, width, height, stackList));
    }

    public static ItemStack getStack(CraftingInventory input, int x, int y) {
        return input.getStack(x + y * input.getWidth());
    }

    public static ItemStack getStack(CompatRecipeInput<?> input, int x, int y) {
        Optional<CraftingInventory> recipeInput = get(input);
        if (!recipeInput.isPresent()) return ItemStackUtil.empty();

        return getStack(recipeInput.get(), x, y);
    }

    public static net.pitan76.mcpitanlib.midohra.item.ItemStack getMidohraStack(CompatRecipeInput<?> input, int x, int y) {
        return net.pitan76.mcpitanlib.midohra.item.ItemStack.of(getStack(input, x, y));
    }

    public static RecipeMatcher getRecipeMatcher(CraftingInventory input) {
        return null;
    }

    public static RecipeMatcher getRecipeMatcher(CompatRecipeInput<?> input) {
        Optional<CraftingInventory> recipeInput = get(input);
        if (!recipeInput.isPresent()) return null;

        return getRecipeMatcher(recipeInput.get());
    }

    public static List<ItemStack> getStacks(CraftingInventory input) {
        return input.getHeldStacks();
    }

    public static List<ItemStack> getStacks(CompatRecipeInput<?> input) {
        Optional<CraftingInventory> recipeInput = get(input);
        if (!recipeInput.isPresent()) return null;

        return getStacks(recipeInput.get());
    }

    public static int getWidth(CraftingInventory input) {
        return input.getWidth();
    }

    public static int getWidth(CompatRecipeInput<?> input) {
        Optional<CraftingInventory> recipeInput = get(input);
        if (!recipeInput.isPresent()) return -1;

        return getWidth(recipeInput.get());
    }

    public static int getHeight(CraftingInventory input) {
        return input.getHeight();
    }

    public static int getHeight(CompatRecipeInput<?> input) {
        Optional<CraftingInventory> recipeInput = get(input);
        if (!recipeInput.isPresent()) return -1;

        return getHeight(recipeInput.get());
    }

    public static int getStackCount(CraftingInventory input) {
        return input.getHeldStacks().size();
    }

    public static int getStackCount(CompatRecipeInput<?> input) {
        Optional<CraftingInventory> recipeInput = get(input);
        if (!recipeInput.isPresent()) return -1;

        return getStackCount(recipeInput.get());
    }
}
