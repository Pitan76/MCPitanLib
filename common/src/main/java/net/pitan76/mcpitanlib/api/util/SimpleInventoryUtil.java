package net.pitan76.mcpitanlib.api.util;

import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.core.NonNullList;

public class SimpleInventoryUtil {
    public static NonNullList<ItemStack> getHeldStacks(SimpleContainer inventory) {
        return inventory.getItems();
    }

    public static SimpleContainer create(int size) {
        return InventoryUtil.createSimpleInventory(size);
    }

    public static ItemStack getStack(SimpleContainer inventory, int slot) {
        return inventory.getItem(slot);
    }

    public static void setStack(SimpleContainer inventory, int slot, ItemStack stack) {
        inventory.setItem(slot, stack);
    }

    public static void clear(SimpleContainer inventory) {
        inventory.clearContent();
    }

    public static int size(SimpleContainer inventory) {
        return inventory.getContainerSize();
    }

    public static boolean isEmpty(SimpleContainer inventory) {
        return inventory.isEmpty();
    }
}
