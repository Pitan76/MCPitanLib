package net.pitan76.mcpitanlib.api.util.screen;

import net.minecraft.block.entity.BlockEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;
import org.jetbrains.annotations.Nullable;

import java.util.Set;

public class ScreenHandlerUtil {
    public static int calcComparatorOutput(@Nullable Inventory inventory) {
        return ScreenHandler.calculateComparatorOutput(inventory);
    }

    public static int calcComparatorOutput(@Nullable BlockEntity blockEntity) {
        return ScreenHandler.calculateComparatorOutput(blockEntity);
    }

    public static int calculateStackSize(Set<Slot> slots, int mode, ItemStack stack) {
        return ScreenHandler.calculateStackSize(slots, mode, stack);
    }

    public static boolean canInsertItemIntoSlot(@Nullable Slot slot, ItemStack stack, boolean allowOverflow) {
        return ScreenHandler.canInsertItemIntoSlot(slot, stack, allowOverflow);
    }
}
