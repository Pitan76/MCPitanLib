package net.pitan76.mcpitanlib.api.util.inventory;

import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;
import net.pitan76.mcpitanlib.api.entity.Player;

public class CompatPlayerInventory implements ICompatInventory {
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

    @Override
    public int getContainerSize() {
        return inv.getContainerSize();
    }

    @Override
    public boolean isEmpty() {
        return inv.isEmpty();
    }

    @Override
    public ItemStack getItem(int slot) {
        return inv.getItem(slot);
    }

    @Override
    public ItemStack removeItem(int slot, int count) {
        return inv.removeItem(slot, count);
    }

    @Override
    public ItemStack removeItemNoUpdate(int slot) {
        return inv.removeItemNoUpdate(slot);
    }

    @Override
    public void setItem(int slot, ItemStack itemStack) {
        inv.setItem(slot, itemStack);
    }

    @Override
    public void setChanged() {
        inv.setChanged();
    }

    @Override
    public boolean stillValid(net.minecraft.world.entity.player.Player player) {
        return inv.stillValid(player);
    }

    @Override
    public void clearContent() {
        inv.clearContent();
    }
}

