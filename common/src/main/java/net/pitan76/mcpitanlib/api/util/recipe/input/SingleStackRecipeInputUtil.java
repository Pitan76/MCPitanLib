package net.pitan76.mcpitanlib.api.util.recipe.input;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.pitan76.mcpitanlib.api.recipe.input.CompatRecipeInput;
import net.pitan76.mcpitanlib.api.util.ItemStackUtil;

import java.util.Optional;

public class SingleStackRecipeInputUtil {
    public static Optional<Inventory> get(CompatRecipeInput<?> input) {
        if (input.getInput() instanceof Inventory) {
            return Optional.of( input.getInput());
        }
        return Optional.empty();
    }

    public static CompatRecipeInput<?> create(Inventory input) {
        return new CompatRecipeInput<>(input);
    }

    public static CompatRecipeInput<?> create(ItemStack stack) {
        return new CompatRecipeInput<>(new Inventory() {

            private ItemStack itemStack = stack;

            @Override
            public void clear() {
                itemStack = ItemStackUtil.empty();
            }

            @Override
            public int size() {
                return 1;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public ItemStack getStack(int slot) {
                if (slot == 0)
                    return itemStack;
                return ItemStackUtil.empty();
            }

            @Override
            public ItemStack removeStack(int slot, int amount) {
                if (slot == 0) {
                    ItemStack stack = itemStack;
                    ItemStackUtil.decrementCount(stack, amount);
                    return stack;
                }
                return ItemStackUtil.empty();
            }

            @Override
            public ItemStack removeStack(int slot) {
                if (slot == 0) {
                    ItemStack stack = itemStack;
                    itemStack = ItemStackUtil.empty();
                    return stack;
                }
                return ItemStackUtil.empty();
            }

            @Override
            public void setStack(int slot, ItemStack stack) {
                if (slot == 0)
                    itemStack = stack;
            }

            @Override
            public void markDirty() {

            }

            @Override
            public boolean canPlayerUse(PlayerEntity player) {
                return false;
            }
        });
    }

    public static ItemStack getStack(CompatRecipeInput<?> input) {
        Optional<Inventory> recipeInput = get(input);
        if (!recipeInput.isPresent()) return ItemStackUtil.empty();

        return recipeInput.get().getStack(0);
    }
}
