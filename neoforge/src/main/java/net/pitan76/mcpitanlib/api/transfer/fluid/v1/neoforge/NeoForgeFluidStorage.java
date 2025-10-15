package net.pitan76.mcpitanlib.api.transfer.fluid.v1.neoforge;

import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.transfer.fluid.FluidResource;
import net.neoforged.neoforge.transfer.fluid.FluidStacksResourceHandler;
import net.neoforged.neoforge.transfer.transaction.Transaction;
import net.pitan76.mcpitanlib.api.event.nbt.ReadNbtArgs;
import net.pitan76.mcpitanlib.api.event.nbt.WriteNbtArgs;
import net.pitan76.mcpitanlib.api.transfer.fluid.v1.IFluidStorage;
import net.pitan76.mcpitanlib.api.transfer.fluid.v1.IFluidVariant;

public class NeoForgeFluidStorage implements IFluidStorage {

    public final FluidStacksResourceHandler handler;
    public FluidStack fluid = FluidStack.EMPTY;
    public long capacity;

    public Runnable onChange;

    public NeoForgeFluidStorage(FluidStacksResourceHandler handler, long capacity, Runnable onChange) {
        this.handler = handler;
        this.onChange = onChange;
        this.capacity = capacity;
    }

    @Override
    public long getAmount() {
        return handler.getAmountFrom(fluid);
    }

    @Override
    public long getCapacity() {
        return capacity;
    }

    @Override
    public IFluidVariant getResource() {
        return new NeoForgeFluidVariant(fluid);
    }

    @Override
    public void setResource(IFluidVariant variant) {
        fluid = ((NeoForgeFluidVariant) variant).raw;
    }

    @Override
    public boolean isResourceBlank() {
        return fluid.isEmpty();
    }

    @Override
    public long insert(IFluidVariant variant, long maxAmount, boolean simulate) {
        if (simulate)
            return handler.insert(FluidResource.of(((NeoForgeFluidVariant) variant).raw), (int) maxAmount, Transaction.open(null));

        onChange.run();
        long inserted = 0;

        try (Transaction transaction = Transaction.open(null)) {
            inserted += handler.insert(FluidResource.of(((NeoForgeFluidVariant) variant).raw), (int) maxAmount, transaction);
            transaction.commit();
        }
        return inserted;
    }

    @Override
    public long extract(IFluidVariant variant, long maxAmount, boolean simulate) {
        if (simulate)
            return handler.extract(FluidResource.of(((NeoForgeFluidVariant) variant).raw), (int) maxAmount, Transaction.open(null));

        onChange.run();
        long extracted = 0;
        try (Transaction transaction = Transaction.open(null)) {
            extracted += handler.extract(FluidResource.of(((NeoForgeFluidVariant) variant).raw), (int) maxAmount, transaction);
            transaction.commit();
        }
        return extracted;
    }

    @Override
    public void writeNbt(WriteNbtArgs args) {
        handler.serialize(args.view);
    }

    @Override
    public void readNbt(ReadNbtArgs args) {
        handler.deserialize(args.view);
    }
}
