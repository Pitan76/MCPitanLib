package net.pitan76.mcpitanlib.api.util.screen;

import net.minecraft.block.entity.BlockEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;
import net.minecraft.util.math.MathHelper;
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
        int stackSize;

        switch (mode) {
            case 0:
                stackSize = MathHelper.floor((float)stack.getCount() / (float)slots.size());
                break;
            case 1:
                stackSize = 1;
                break;
            case 2:
                stackSize = stack.getMaxCount();
                break;
            default:
                stackSize = stack.getCount();
        }

        return stackSize;
    }

    public static boolean canInsertItemIntoSlot(@Nullable Slot slot, ItemStack stack, boolean allowOverflow) {
        return ScreenHandler.canInsertItemIntoSlot(slot, stack, allowOverflow);
    }
}
