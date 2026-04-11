package net.pitan76.mcpitanlib.api.gui.inventory.sided;

import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.core.Direction;
import net.pitan76.mcpitanlib.api.gui.inventory.sided.args.AvailableSlotsArgs;
import net.pitan76.mcpitanlib.api.gui.inventory.sided.args.CanExtractArgs;
import net.pitan76.mcpitanlib.api.gui.inventory.sided.args.CanInsertArgs;
import org.jetbrains.annotations.Nullable;

public interface CompatSidedInventory extends WorldlyContainer {
    @Override
    @Deprecated
    default int[] getSlotsForFace(Direction side) {
        return getAvailableSlots(new AvailableSlotsArgs(side, this));
    }

    int[] getAvailableSlots(AvailableSlotsArgs args);

    @Override
    @Deprecated
    default boolean canPlaceItemThroughFace(int slot, ItemStack stack, @Nullable Direction dir) {
        return canInsert(new CanInsertArgs(slot, stack, dir));
    }

    boolean canInsert(CanInsertArgs args);

    @Override
    @Deprecated
    default boolean canTakeItemThroughFace(int slot, ItemStack stack, Direction dir) {
        return canExtract(new CanExtractArgs(slot, stack, dir));
    }

    boolean canExtract(CanExtractArgs args);
}
