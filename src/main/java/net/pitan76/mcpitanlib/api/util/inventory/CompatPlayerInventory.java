package net.pitan76.mcpitanlib.api.util.inventory;

import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;
import net.pitan76.mcpitanlib.api.entity.Player;

public class CompatPlayerInventory {
    public Inventory inv;

    public CompatPlayerInventory(Inventory inv) {
        this.inv = inv;
    }

    public Player getPlayer() {
        return PlayerInventoryUtil.getPlayer(inv);
    }

    public int getSelectedSlot() {
        return PlayerInventoryUtil.getSelectedSlot(inv);
    }

    public void setSelectedSlot(int slot) {
        PlayerInventoryUtil.setSelectedSlot(inv, slot);
    }

    public void dropAllItems() {
        PlayerInventoryUtil.dropAllItems(inv);
    }

    public void offerOrDrop(ItemStack stack) {
        inv.placeItemBackInInventory(stack);
    }

    public void offerOrDrop(net.pitan76.mcpitanlib.midohra.item.ItemStack stack) {
        offerOrDrop(stack.toMinecraft());
    }

    public Inventory getRaw() {
        return inv;
    }
}

