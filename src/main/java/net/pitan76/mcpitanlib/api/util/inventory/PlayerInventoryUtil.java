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
        return playerInventory.getSelectedSlot();
    }

    public static void setSelectedSlot(PlayerInventory playerInventory, int slot) {
        playerInventory.setSelectedSlot(slot);
    }

    public static void dropAllItems(PlayerInventory inv) {
        inv.dropAll();
    }

    public static DefaultedList<ItemStack> getMain(PlayerInventory inv) {
        return inv.getMainStacks();
    }

    public static DefaultedList<ItemStack> getArmor(PlayerInventory inv) {
        DefaultedList<ItemStack> stacks = DefaultedList.ofSize(4, ItemStack.EMPTY);
        stacks.set(0, inv.getStack(36));
        stacks.set(1, inv.getStack(37));
        stacks.set(2, inv.getStack(38));
        stacks.set(3, inv.getStack(39));
        return stacks;
    }

    public static DefaultedList<ItemStack> getOffHand(PlayerInventory inv) {
        DefaultedList<ItemStack> stacks = DefaultedList.ofSize(1, ItemStack.EMPTY);
        stacks.set(0, inv.getStack(PlayerInventory.OFF_HAND_SLOT));
        return stacks;
    }

    public static ItemStack getMainHandStack(PlayerInventory inv) {
        return inv.player.getMainHandStack();
    }
}
