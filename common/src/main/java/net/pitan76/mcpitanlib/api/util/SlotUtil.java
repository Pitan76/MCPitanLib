package net.pitan76.mcpitanlib.api.util;

import net.minecraft.item.ItemStack;
import net.minecraft.screen.slot.Slot;
import net.pitan76.mcpitanlib.api.entity.Player;

public class SlotUtil {
    public static void setStack(Slot slot, ItemStack stack) {
        slot.setStack(stack);
    }

    public static ItemStack getStack(Slot slot) {
        return slot.getStack();
    }

    public static void takeStack(Slot slot, int amount) {
        slot.takeStack(amount);
    }

    public static boolean hasStack(Slot slot) {
        return slot.hasStack();
    }

    public static void markDirty(Slot slot) {
        slot.markDirty();
    }

    public static boolean canInsert(Slot slot, ItemStack stack) {
        return slot.canInsert(stack);
    }

    public static boolean canTakeItems(Slot slot) {
        return slot.canTakeItems(null);
    }

    public static void onTakeItem(Slot slot, Player player, ItemStack stack) {
        slot.onTakeItem(player.getEntity(), stack);
    }
}
