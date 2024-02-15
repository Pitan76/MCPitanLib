package net.pitan76.mcpitanlib.api.util;

import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.collection.DefaultedList;

public class SimpleInventoryUtil {
    public static DefaultedList<ItemStack> getHeldStacks(SimpleInventory inventory) {
        return inventory.getHeldStacks();
    }
}
