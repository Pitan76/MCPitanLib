package net.pitan76.mcpitanlib.api.util;

import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.collection.DefaultedList;

public class SimpleInventoryUtil {
    public static DefaultedList<ItemStack> getHeldStacks(SimpleInventory inventory) {
        return inventory.getHeldStacks();
    }

    public static SimpleInventory create(int size) {
        return InventoryUtil.createSimpleInventory(size);
    }

    public static ItemStack getStack(SimpleInventory inventory, int slot) {
        return inventory.getStack(slot);
    }

    public static void setStack(SimpleInventory inventory, int slot, ItemStack stack) {
        inventory.setStack(slot, stack);
    }

    public static void clear(SimpleInventory inventory) {
        inventory.clear();
    }

    public static int size(SimpleInventory inventory) {
        return inventory.size();
    }
}
