package net.pitan76.mcpitanlib.api.gui.inventory.sided;

import net.pitan76.mcpitanlib.api.gui.inventory.sided.args.AvailableSlotsArgs;

public interface ChestStyleSidedInventory extends VanillaStyleSidedInventory {
    @Override
    default int[] getAvailableSlots(AvailableSlotsArgs args) {
        return args.getAllSlots();
    }
}
