package net.pitan76.mcpitanlib.api.util.inventory.args;

import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
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
    public int getContainerSize() {
        return inventory.getContainerSize();
    }

    @Override
    public boolean isEmpty() {
        return inventory.isEmpty();
    }

    @Override
    public ItemStack getItem(int slot) {
        return inventory.getItem(slot);
    }

    @Override
    public ItemStack removeItem(int slot, int count) {
        return inventory.removeItem(slot, count);
    }

    @Override
    public ItemStack removeItemNoUpdate(int slot) {
        return inventory.removeItemNoUpdate(slot);
    }

    @Override
    public void setItem(int slot, ItemStack itemStack) {
        inventory.setItem(slot, itemStack);
    }

    @Override
    public void setChanged() {
        inventory.setChanged();
    }

    @Override
    public boolean stillValid(Player player) {
        return inventory.stillValid(player);
    }

    @Override
    public void clearContent() {
        inventory.clearContent();
    }
}
