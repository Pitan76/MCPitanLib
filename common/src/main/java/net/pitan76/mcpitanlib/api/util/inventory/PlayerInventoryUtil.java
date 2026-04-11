package net.pitan76.mcpitanlib.api.util.inventory;

import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;
import net.minecraft.core.NonNullList;
import net.pitan76.mcpitanlib.api.entity.Player;

public class PlayerInventoryUtil {
    public static Player getPlayer(Inventory playerInventory) {
        return new Player(playerInventory.player);
    }

    public static int getSelectedSlot(Inventory playerInventory) {
        return playerInventory.getSelectedSlot();
    }

    public static void setSelectedSlot(Inventory playerInventory, int slot) {
        playerInventory.setSelectedSlot(slot);
    }

    public static void dropAllItems(Inventory inv) {
        inv.dropAll();
    }

    public static NonNullList<ItemStack> getMain(Inventory inv) {
        return inv.getNonEquipmentItems();
    }

    public static NonNullList<ItemStack> getArmor(Inventory inv) {
        NonNullList<ItemStack> stacks = NonNullList.withSize(4, ItemStack.EMPTY);
        stacks.set(0, inv.getItem(36));
        stacks.set(1, inv.getItem(37));
        stacks.set(2, inv.getItem(38));
        stacks.set(3, inv.getItem(39));
        return stacks;
    }

    public static NonNullList<ItemStack> getOffHand(Inventory inv) {
        NonNullList<ItemStack> stacks = NonNullList.withSize(1, ItemStack.EMPTY);
        stacks.set(0, inv.getItem(Inventory.SLOT_OFFHAND));
        return stacks;
    }

    public static ItemStack getMainHandStack(Inventory inv) {
        return inv.player.getMainHandItem();
    }
}
