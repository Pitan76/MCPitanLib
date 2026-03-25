package net.pitan76.mcpitanlib.api.util.recipe.input;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.SingleRecipeInput;
import net.pitan76.mcpitanlib.api.recipe.input.CompatRecipeInput;
import net.pitan76.mcpitanlib.api.util.ItemStackUtil;

import java.util.Optional;

public class SingleStackRecipeInputUtil {
    public static Optional<SingleRecipeInput> get(CompatRecipeInput<?> input) {
        if (input.getInput() instanceof SingleRecipeInput) {
            return Optional.of((SingleRecipeInput) input.getInput());
        }
        return Optional.empty();
    }

    public static CompatRecipeInput<?> create(SingleRecipeInput input) {
        return new CompatRecipeInput<>(input);
    }

    public static CompatRecipeInput<?> create(ItemStack stack) {
        return new CompatRecipeInput<>(new SingleRecipeInput(stack));
    }

    public static ItemStack getStack(CompatRecipeInput<?> input) {
        Optional<SingleRecipeInput> recipeInput = get(input);
        if (!recipeInput.isPresent()) return ItemStackUtil.empty();

        return recipeInput.get().getItem(0);
    }
}
