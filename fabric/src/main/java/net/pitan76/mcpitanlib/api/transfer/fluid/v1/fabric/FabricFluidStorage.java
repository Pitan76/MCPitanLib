package net.pitan76.mcpitanlib.api.transfer.fluid.v1.fabric;

import net.fabricmc.fabric.api.transfer.v1.fluid.base.SingleFluidStorage;
import net.fabricmc.fabric.api.transfer.v1.transaction.Transaction;
import net.pitan76.mcpitanlib.api.event.nbt.ReadNbtArgs;
import net.pitan76.mcpitanlib.api.event.nbt.WriteNbtArgs;
import net.pitan76.mcpitanlib.api.extra.transfer.util.FluidStorageUtil;
import net.pitan76.mcpitanlib.api.transfer.fluid.v1.IFluidStorage;
import net.pitan76.mcpitanlib.api.transfer.fluid.v1.IFluidVariant;

@SuppressWarnings({"removal", "UnstableApiUsage"})
public class FabricFluidStorage implements IFluidStorage {

    public final SingleFluidStorage storage;

    public FabricFluidStorage(SingleFluidStorage storage) {
        this.storage = storage;
    }

    @Override
    public long getAmount() {
        return storage.getAmount();
    }

    @Override
    public long getCapacity() {
        return storage.getCapacity();
    }

    @Override
    public IFluidVariant getResource() {
        return new FabricFluidVariant(storage.getResource());
    }

    @Override
    public void setResource(IFluidVariant variant) {
        storage.fluidVariant = ((FabricFluidVariant) variant).raw;
    }

    @Override
    public boolean isResourceBlank() {
        return storage.isResourceBlank();
    }

    @Override
    public long insert(IFluidVariant variant, long maxAmount, boolean simulate) {
        if (simulate)
            return storage.insert(((FabricFluidVariant) variant).raw, maxAmount, Transaction.openOuter());

        long inserted = 0;
        try (Transaction transaction = Transaction.openOuter()) {
            inserted += storage.insert(((FabricFluidVariant) variant).raw, maxAmount, transaction);
            transaction.commit();
        }
        return inserted;
    }

    @Override
    public long extract(IFluidVariant variant, long maxAmount, boolean simulate) {
        if (simulate)
            return storage.extract(((FabricFluidVariant) variant).raw, maxAmount, Transaction.openOuter());

        long extracted = 0;
        try (Transaction transaction = Transaction.openOuter()) {
            extracted += storage.extract(((FabricFluidVariant) variant).raw, maxAmount, transaction);
            transaction.commit();
        }
        return extracted;
    }

    @Override
    public void writeNbt(WriteNbtArgs args) {
        FluidStorageUtil.writeNbt(storage, args);
    }

    @Override
    public void readNbt(ReadNbtArgs args) {
        FluidStorageUtil.readNbt(storage, args);
    }
}
