package net.pitan76.mcpitanlib.api.util.inventory;

import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class CompatInventory extends SimpleInventory {
    public CompatInventory(int size) {
        super(size);
    }

    @Override
    public void setStack(int slot, ItemStack stack) {
        super.setStack(slot, stack);
    }

    /**
     * super method of setStack(slot, stack)
     */
    public final void superSetStack(int slot, ItemStack stack) {
        super.setStack(slot, stack);
    }

    /**
     * super method of removeStack(slot, amount)
     */
    public final ItemStack superRemoveStack(int slot, int amount) {
        return super.removeStack(slot, amount);
    }

    @Override
    public ItemStack removeStack(int slot, int amount) {
        return super.removeStack(slot, amount);
    }

    @Override
    public ItemStack removeStack(int slot) {
        return super.removeStack(slot);
    }

    @Override
    public ItemStack removeItem(Item item, int count) {
        return super.removeItem(item, count);
    }
}
