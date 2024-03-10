package net.pitan76.mcpitanlib.api.util;

import net.minecraft.item.ItemStack;

import java.util.Objects;

public class ItemStackUtil {
    public static ItemStack copy(ItemStack stack) {
        return stack.copy();
    }

    public static ItemStack copyWithCount(ItemStack stack, int count) {
        return stack.copyWithCount(count);
    }

    public static boolean areItemsEqual(ItemStack left, ItemStack right) {
        return ItemStack.areItemsEqual(left, right);
    }

    public static boolean areNbtEqual(ItemStack left, ItemStack right) {
        // 1.20.5からはComponentを比較し、それ以前はNBTを比較する
        return Objects.equals(left.getComponents(), right.getComponents());
    }
}
