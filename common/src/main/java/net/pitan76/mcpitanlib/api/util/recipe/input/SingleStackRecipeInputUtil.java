package net.pitan76.mcpitanlib.api.util.recipe.input;

import net.minecraft.item.ItemStack;
import net.minecraft.recipe.input.SingleStackRecipeInput;
import net.pitan76.mcpitanlib.api.recipe.input.CompatRecipeInput;
import net.pitan76.mcpitanlib.api.util.ItemStackUtil;

import java.util.Optional;

public class SingleStackRecipeInputUtil {
    public static Optional<SingleStackRecipeInput> get(CompatRecipeInput<?> input) {
        if (input.getInput() instanceof SingleStackRecipeInput) {
            return Optional.of((SingleStackRecipeInput) input.getInput());
        }
        return Optional.empty();
    }

    public static CompatRecipeInput<?> create(SingleStackRecipeInput input) {
        return new CompatRecipeInput<>(input);
    }

    public static CompatRecipeInput<?> create(ItemStack stack) {
        return new CompatRecipeInput<>(new SingleStackRecipeInput(stack));
    }

    public static ItemStack getStack(CompatRecipeInput<?> input) {
        Optional<SingleStackRecipeInput> recipeInput = get(input);
        if (!recipeInput.isPresent()) return ItemStackUtil.empty();

        return recipeInput.get().getStackInSlot(0);
    }
}
