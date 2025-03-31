package net.pitan76.mcpitanlib.api.util.inventory;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.collection.DefaultedList;
import net.pitan76.mcpitanlib.api.entity.Player;

public class PlayerInventoryUtil {
    public static Player getPlayer(PlayerInventory playerInventory) {
        return new Player(playerInventory.player);
    }

    public static int getSelectedSlot(PlayerInventory playerInventory) {
        return playerInventory.selectedSlot;
    }

    public static void setSelectedSlot(PlayerInventory playerInventory, int slot) {
        playerInventory.selectedSlot = slot;
    }

    public static void dropAllItems(PlayerInventory inv) {
        inv.dropAll();
    }

    public static DefaultedList<ItemStack> getMain(PlayerInventory inv) {
        return inv.main;
    }

    public static DefaultedList<ItemStack> getArmor(PlayerInventory inv) {
        return inv.armor;
    }

    public static DefaultedList<ItemStack> getOffHand(PlayerInventory inv) {
        return inv.offHand;
    }

    public static ItemStack getMainHandStack(PlayerInventory inv) {
        return inv.getMainHandStack();
    }
}
