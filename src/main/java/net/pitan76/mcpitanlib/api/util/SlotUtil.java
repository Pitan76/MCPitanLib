package net.pitan76.mcpitanlib.api.util;

import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.inventory.Slot;
import net.pitan76.mcpitanlib.api.entity.Player;

public class SlotUtil {
    public static void setStack(Slot slot, ItemStack stack) {
        slot.setByPlayer(stack);
    }

    public static ItemStack getStack(Slot slot) {
        return slot.getItem();
    }

    public static void takeStack(Slot slot, int amount) {
        slot.remove(amount);
    }

    public static boolean hasStack(Slot slot) {
        return slot.hasItem();
    }

    public static void markDirty(Slot slot) {
        slot.setChanged();
    }

    public static boolean canInsert(Slot slot, ItemStack stack) {
        return slot.mayPlace(stack);
    }

    public static boolean canTakeItems(Slot slot) {
        return slot.mayPickup(null);
    }

    public static void onTakeItem(Slot slot, Player player, ItemStack stack) {
        slot.onTake(player.getEntity(), stack);
    }

    public static Container getInventory(Slot slot) {
        return slot.container;
    }
}
