package net.pitan76.mcpitanlib.api.util.screen;

import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import org.jetbrains.annotations.Nullable;

import java.util.Set;

public class ScreenHandlerUtil {
    public static int calcComparatorOutput(@Nullable Container inventory) {
        return AbstractContainerMenu.getRedstoneSignalFromContainer(inventory);
    }

    public static int calcComparatorOutput(@Nullable BlockEntity blockEntity) {
        return AbstractContainerMenu.getRedstoneSignalFromBlockEntity(blockEntity);
    }

    public static int calculateStackSize(Set<Slot> slots, int mode, ItemStack stack) {
        return AbstractContainerMenu.getQuickCraftPlaceCount(slots, mode, stack);
    }

    public static boolean canInsertItemIntoSlot(@Nullable Slot slot, ItemStack stack, boolean allowOverflow) {
        return AbstractContainerMenu.canItemQuickReplace(slot, stack, allowOverflow);
    }
}
