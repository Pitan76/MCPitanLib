package net.pitan76.mcpitanlib.api.util.inventory;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.pitan76.mcpitanlib.api.util.ItemStackUtil;

public class ClippedInventory implements Container, ICompatInventory {

    private final Container inventory;
    private final int start;
    private final int end;

    public static ClippedInventory of(Container inventory, int start, int end) {
        if (start < 0 || end > inventory.getContainerSize() || start >= end) {
            throw new IllegalArgumentException("Invalid start or end indices for clipping inventory.");
        }
        return new ClippedInventory(inventory, start, end);
    }

    public static ClippedInventory of(Container inventory) {
        return of(inventory, 0, inventory.getContainerSize());
    }

    public static ClippedInventory of(Container inventory, int start) {
        return of(inventory, start, inventory.getContainerSize());
    }

    public ClippedInventory(Container inventory, int start, int end) {
        this.inventory = inventory;
        this.start = start;
        this.end = end;
    }

    @Override
    public int getContainerSize() {
        return end - start;
    }

    @Override
    public boolean isEmpty() {
        for (int i = start; i < end; i++) {
            if (!inventory.getItem(i).isEmpty()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public ItemStack getItem(int slot) {
        return inventory.getItem(start + slot);
    }

    @Override
    public ItemStack removeItem(int slot, int amount) {
        return inventory.removeItem(start + slot, amount);
    }

    @Override
    public ItemStack removeItemNoUpdate(int slot) {
        return inventory.removeItemNoUpdate(start + slot);
    }

    @Override
    public void setItem(int slot, ItemStack stack) {
        inventory.setItem(start + slot, stack);
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
        for (int i = start; i < end; i++) {
            inventory.setItem(i, ItemStackUtil.empty());
        }
    }

    @Override
    public int getMaxStackSize() {
        return inventory.getMaxStackSize();
    }
}
