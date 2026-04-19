package net.pitan76.mcpitanlib.api.util.inventory.args;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.pitan76.mcpitanlib.api.util.inventory.ICompatInventory;

public class InventoryWrapper implements ICompatInventory {
    private final Inventory inventory;

    public InventoryWrapper(Inventory inventory) {
        this.inventory = inventory;
    }

    public static InventoryWrapper of(Inventory inventory) {
        return new InventoryWrapper(inventory);
    }

    public Inventory getInventory() {
        return inventory;
    }

    @Override
    public int size() {
        return inventory.size();
    }

    @Override
    public boolean isEmpty() {
        return inventory.isEmpty();
    }

    @Override
    public ItemStack getStack(int slot) {
        return inventory.getStack(slot);
    }

    @Override
    public ItemStack removeStack(int slot, int count) {
        return inventory.removeStack(slot, count);
    }

    @Override
    public ItemStack removeStack(int slot) {
        return inventory.removeStack(slot);
    }

    @Override
    public void setStack(int slot, ItemStack itemStack) {
        inventory.setStack(slot, itemStack);
    }

    @Override
    public void markDirty() {
        inventory.markDirty();
    }

    @Override
    public boolean canPlayerUse(PlayerEntity player) {
        return inventory.canPlayerUse(player);
    }

    @Override
    public void clear() {
        inventory.clear();
    }
}
