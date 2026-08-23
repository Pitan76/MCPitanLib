package net.pitan76.mcpitanlib.api.transfer.item.v1.fabric;

import net.fabricmc.fabric.api.transfer.v1.item.ItemStorage;
import net.fabricmc.fabric.api.transfer.v1.item.ItemVariant;
import net.fabricmc.fabric.api.transfer.v1.storage.Storage;
import net.fabricmc.fabric.api.transfer.v1.transaction.Transaction;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.pitan76.mcpitanlib.api.transfer.item.v1.IItemHandler;
import org.jetbrains.annotations.Nullable;

public class ItemTransferUtilImpl {

    public static int insertTo(Level world, BlockPos pos, @Nullable Direction side, ItemStack stack, boolean simulate) {
        if (stack == null || stack.isEmpty()) return 0;

        Storage<ItemVariant> storage = ItemStorage.SIDED.find(world, pos, side);
        if (storage == null || !storage.supportsInsertion()) return 0;

        try (Transaction transaction = Transaction.openOuter()) {
            long inserted = storage.insert(ItemVariant.of(stack), stack.getCount(), transaction);
            if (!simulate) transaction.commit();

            return (int) inserted;
        }
    }

    @Nullable
    public static IItemHandler getItemHandler(Level world, BlockPos pos, @Nullable Direction side) {
        Storage<ItemVariant> storage = ItemStorage.SIDED.find(world, pos, side);
        if (storage == null) return null;

        return new FabricItemHandler(storage);
    }

    public static void registerInventory(BlockEntityType<?> type) {
    }
}
