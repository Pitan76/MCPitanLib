package net.pitan76.mcpitanlib.api.util.recipe.input;

import net.minecraft.item.ItemStack;
import net.minecraft.recipe.RecipeMatcher;
import net.minecraft.recipe.input.CraftingRecipeInput;
import net.pitan76.mcpitanlib.api.recipe.input.CompatRecipeInput;
import net.pitan76.mcpitanlib.api.util.ItemStackUtil;

import java.util.List;
import java.util.Optional;

public class CraftingRecipeInputUtil {
    public static Optional<CraftingRecipeInput> get(CompatRecipeInput<?> input) {
        if (input.getInput() instanceof CraftingRecipeInput) {
            return Optional.of((CraftingRecipeInput) input.getInput());
        }
        return Optional.empty();
    }

    public static CompatRecipeInput<?> create(CraftingRecipeInput input) {
        return new CompatRecipeInput<>(input);
    }

    public static CompatRecipeInput<?> create(int width, int height, List<ItemStack> stacks) {
        return new CompatRecipeInput<>(CraftingRecipeInput.create(width, height, stacks));
    }

    public static ItemStack getStack(CraftingRecipeInput input, int x, int y) {
        return input.getStackInSlot(x, y);
    }

    public static ItemStack getStack(CompatRecipeInput<?> input, int x, int y) {
        Optional<CraftingRecipeInput> recipeInput = get(input);
        if (!recipeInput.isPresent()) return ItemStackUtil.empty();

        return getStack(recipeInput.get(), x, y);
    }

    public static net.pitan76.mcpitanlib.midohra.item.ItemStack getMidohraStack(CompatRecipeInput<?> input, int x, int y) {
        return net.pitan76.mcpitanlib.midohra.item.ItemStack.of(getStack(input, x, y));
    }

    public static RecipeMatcher getRecipeMatcher(CraftingRecipeInput input) {
        return input.getRecipeMatcher();
    }

    public static RecipeMatcher getRecipeMatcher(CompatRecipeInput<?> input) {
        Optional<CraftingRecipeInput> recipeInput = get(input);
        if (!recipeInput.isPresent()) return null;

        return getRecipeMatcher(recipeInput.get());
    }

    public static List<ItemStack> getStacks(CraftingRecipeInput input) {
        return input.getStacks();
    }

    public static List<ItemStack> getStacks(CompatRecipeInput<?> input) {
        Optional<CraftingRecipeInput> recipeInput = get(input);
        if (!recipeInput.isPresent()) return null;

        return getStacks(recipeInput.get());
    }

    public static int getWidth(CraftingRecipeInput input) {
        return input.getWidth();
    }

    public static int getWidth(CompatRecipeInput<?> input) {
        Optional<CraftingRecipeInput> recipeInput = get(input);
        if (!recipeInput.isPresent()) return -1;

        return getWidth(recipeInput.get());
    }

    public static int getHeight(CraftingRecipeInput input) {
        return input.getHeight();
    }

    public static int getHeight(CompatRecipeInput<?> input) {
        Optional<CraftingRecipeInput> recipeInput = get(input);
        if (!recipeInput.isPresent()) return -1;

        return getHeight(recipeInput.get());
    }

    public static int getStackCount(CraftingRecipeInput input) {
        return input.getStackCount();
    }

    public static int getStackCount(CompatRecipeInput<?> input) {
        Optional<CraftingRecipeInput> recipeInput = get(input);
        if (!recipeInput.isPresent()) return -1;

        return getStackCount(recipeInput.get());
    }
}
