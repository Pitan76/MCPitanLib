package net.pitan76.mcpitanlib.api.gui.inventory.sided;

import net.minecraft.inventory.SidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Direction;
import net.pitan76.mcpitanlib.api.gui.inventory.sided.args.AvailableSlotsArgs;
import net.pitan76.mcpitanlib.api.gui.inventory.sided.args.CanExtractArgs;
import net.pitan76.mcpitanlib.api.gui.inventory.sided.args.CanInsertArgs;
import org.jetbrains.annotations.Nullable;

public interface CompatSidedInventory extends SidedInventory {
    @Override
    @Deprecated
    default int[] getAvailableSlots(Direction side) {
        return getAvailableSlots(new AvailableSlotsArgs(side, this));
    }

    int[] getAvailableSlots(AvailableSlotsArgs args);

    @Override
    @Deprecated
    default boolean canInsert(int slot, ItemStack stack, @Nullable Direction dir) {
        return canInsert(new CanInsertArgs(slot, stack, dir));
    }

    boolean canInsert(CanInsertArgs args);

    @Override
    @Deprecated
    default boolean canExtract(int slot, ItemStack stack, Direction dir) {
        return canExtract(new CanExtractArgs(slot, stack, dir));
    }

    boolean canExtract(CanExtractArgs args);
}
