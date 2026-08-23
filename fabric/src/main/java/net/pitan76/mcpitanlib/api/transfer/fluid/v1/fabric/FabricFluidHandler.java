package net.pitan76.mcpitanlib.api.transfer.fluid.v1.fabric;

import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.fabricmc.fabric.api.transfer.v1.storage.Storage;
import net.fabricmc.fabric.api.transfer.v1.transaction.Transaction;
import net.pitan76.mcpitanlib.api.transfer.fluid.v1.IFluidHandler;
import net.pitan76.mcpitanlib.api.transfer.fluid.v1.IFluidVariant;

public class FabricFluidHandler implements IFluidHandler {

    public final Storage<FluidVariant> storage;

    public FabricFluidHandler(Storage<FluidVariant> storage) {
        this.storage = storage;
    }

    public Storage<FluidVariant> getRaw() {
        return storage;
    }

    @Override
    public long insert(IFluidVariant variant, long maxAmount, boolean simulate) {
        if (!supportsInsertion()) return 0;

        try (Transaction transaction = Transaction.openOuter()) {
            long inserted = storage.insert(((FabricFluidVariant) variant).raw, maxAmount, transaction);
            if (!simulate) transaction.commit();

            return inserted;
        }
    }

    @Override
    public long extract(IFluidVariant variant, long maxAmount, boolean simulate) {
        if (!supportsExtraction()) return 0;

        try (Transaction transaction = Transaction.openOuter()) {
            long extracted = storage.extract(((FabricFluidVariant) variant).raw, maxAmount, transaction);
            if (!simulate) transaction.commit();

            return extracted;
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
