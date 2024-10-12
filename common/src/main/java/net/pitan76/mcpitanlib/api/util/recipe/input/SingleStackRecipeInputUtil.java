package net.pitan76.mcpitanlib.api.util.recipe.input;

import net.minecraft.block.entity.BlockEntity;
import net.minecraft.inventory.SingleStackInventory;
import net.minecraft.item.ItemStack;
import net.pitan76.mcpitanlib.api.recipe.input.CompatRecipeInput;
import net.pitan76.mcpitanlib.api.util.ItemStackUtil;

import java.util.Optional;

public class SingleStackRecipeInputUtil {
    public static Optional<SingleStackInventory> get(CompatRecipeInput<?> input) {
        if (input.getInput() instanceof SingleStackInventory) {
            return Optional.of((SingleStackInventory) input.getInput());
        }
        return Optional.empty();
    }

    public static CompatRecipeInput<?> create(SingleStackInventory input) {
        return new CompatRecipeInput<>(input);
    }

    public static CompatRecipeInput<?> create(ItemStack stack) {
        return new CompatRecipeInput<>(new SingleStackInventory() {
            private ItemStack itemStack = stack;

            @Override
            public void markDirty() {

            }

            @Override
            public ItemStack getStack() {
                return itemStack;
            }

            @Override
            public ItemStack decreaseStack(int count) {
                ItemStackUtil.decrementCount(itemStack, count);
                return itemStack;
            }

            @Override
            public void setStack(ItemStack stack) {
                this.itemStack = stack;
            }

            @Override
            public BlockEntity asBlockEntity() {
                return null;
            }
        });
    }

    public static ItemStack getStack(CompatRecipeInput<?> input) {
        Optional<SingleStackInventory> recipeInput = get(input);
        if (!recipeInput.isPresent()) return ItemStackUtil.empty();

        return recipeInput.get().getStack(0);
    }
}
