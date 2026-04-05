package net.pitan76.mcpitanlib.api.util.inventory;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.pitan76.mcpitanlib.api.entity.Player;

public class CompatPlayerInventory implements ICompatInventory {
    public PlayerInventory inv;

    public CompatPlayerInventory(PlayerInventory inv) {
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
        inv.offerOrDrop(inv.player.world, stack);
    }

    public void offerOrDrop(net.pitan76.mcpitanlib.midohra.item.ItemStack stack) {
        offerOrDrop(stack.toMinecraft());
    }

    public PlayerInventory getRaw() {
        return inv;
    }

    @Override
    public int size() {
        return inv.size();
    }

    @Override
    public boolean isEmpty() {
        return inv.isEmpty();
    }

    @Override
    public ItemStack getStack(int slot) {
        return inv.getStack(slot);
    }

    @Override
    public ItemStack removeStack(int slot, int count) {
        return inv.removeStack(slot, count);
    }

    @Override
    public ItemStack removeStack(int slot) {
        return inv.removeStack(slot);
    }

    @Override
    public void setStack(int slot, ItemStack itemStack) {
        inv.setStack(slot, itemStack);
    }

    @Override
    public void markDirty() {
        inv.markDirty();
    }

    @Override
    public boolean canPlayerUse(PlayerEntity player) {
        return inv.canPlayerUse(player);
    }

    @Override
    public void clear() {
        inv.clear();
    }
}

