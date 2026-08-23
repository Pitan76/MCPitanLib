package net.pitan76.mcpitanlib.api.transfer.item.v1.fabric;

import net.fabricmc.fabric.api.transfer.v1.item.ItemVariant;
import net.fabricmc.fabric.api.transfer.v1.storage.Storage;
import net.fabricmc.fabric.api.transfer.v1.transaction.Transaction;
import net.minecraft.item.ItemStack;
import net.pitan76.mcpitanlib.api.transfer.item.v1.IItemHandler;

public class FabricItemHandler implements IItemHandler {

    public final Storage<ItemVariant> storage;

    public FabricItemHandler(Storage<ItemVariant> storage) {
        this.storage = storage;
    }

    public Storage<ItemVariant> getRaw() {
        return storage;
    }

    @Override
    public int insert(ItemStack stack, boolean simulate) {
        if (stack == null || stack.isEmpty() || !supportsInsertion()) return 0;

        try (Transaction transaction = Transaction.openOuter()) {
            long inserted = storage.insert(ItemVariant.of(stack), stack.getCount(), transaction);
            if (!simulate) transaction.commit();

            return (int) inserted;
        }
    }

    @Override
    public int extract(ItemStack stack, boolean simulate) {
        if (stack == null || stack.isEmpty() || !supportsExtraction()) return 0;

        try (Transaction transaction = Transaction.openOuter()) {
            long extracted = storage.extract(ItemVariant.of(stack), stack.getCount(), transaction);
            if (!simulate) transaction.commit();

            return (int) extracted;
        }
    }

    @Override
    public boolean supportsInsertion() {
        return storage.supportsInsertion();
    }

    @Override
    public boolean supportsExtraction() {
        return storage.supportsExtraction();
    }
}
