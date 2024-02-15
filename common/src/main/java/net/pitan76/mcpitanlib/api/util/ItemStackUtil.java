package net.pitan76.mcpitanlib.api.util;

import net.minecraft.item.ItemStack;

public class ItemStackUtil {
    public static ItemStack copy(ItemStack stack) {
        return stack.copy();
    }

    public static ItemStack copyWithCount(ItemStack stack, int count) {
        ItemStack copy = stack.copy();
        copy.setCount(count);
        return copy;
    }

    public static boolean areItemsEqual(ItemStack left, ItemStack right) {
        return ItemStack.areItemsEqual(left, right);
    }

    public static boolean areNbtEqual(ItemStack left, ItemStack right) {
        return ItemStack.areNbtEqual(left, right);
    }
}
