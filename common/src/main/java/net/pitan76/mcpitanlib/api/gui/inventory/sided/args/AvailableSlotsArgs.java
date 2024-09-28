package net.pitan76.mcpitanlib.api.gui.inventory.sided.args;

import net.minecraft.util.math.Direction;
import net.pitan76.mcpitanlib.api.gui.inventory.sided.CompatSidedInventory;
import net.pitan76.mcpitanlib.api.gui.inventory.IInventory;

public class AvailableSlotsArgs {
    public Direction side;
    public CompatSidedInventory inventory;

    public AvailableSlotsArgs(Direction side, CompatSidedInventory inventory) {
        this.side = side;
        this.inventory = inventory;
    }

    public Direction getSide() {
        return side;
    }

    public CompatSidedInventory getInventory() {
        return inventory;
    }

    public int[] getAllSlots() {
        if (!(inventory instanceof IInventory))
            return new int[0];

        IInventory inv = (IInventory) inventory;
        int size = inv.size();

        int[] slots = new int[size];
        for (int i = 0; i < size; i++) {
            slots[i] = i;
        }

        return slots;
    }
}
