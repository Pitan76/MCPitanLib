package net.pitan76.mcpitanlib.api.transfer.fluid.v1.neoforge;

import net.neoforged.neoforge.fluids.capability.IFluidHandler;
import net.neoforged.neoforge.fluids.capability.templates.FluidTank;
import net.pitan76.mcpitanlib.api.event.nbt.ReadNbtArgs;
import net.pitan76.mcpitanlib.api.event.nbt.WriteNbtArgs;
import net.pitan76.mcpitanlib.api.transfer.fluid.v1.IFluidStorage;
import net.pitan76.mcpitanlib.api.transfer.fluid.v1.IFluidVariant;

public class NeoForgeFluidStorage implements IFluidStorage {

    public final FluidTank storage;

    public Runnable onChange;

    public NeoForgeFluidStorage(FluidTank storage, Runnable onChange) {
        this.storage = storage;
        this.onChange = onChange;
    }

    @Override
    public long getAmount() {
        return storage.getFluidAmount();
    }

    @Override
    public long getCapacity() {
        return storage.getCapacity();
    }

    @Override
    public IFluidVariant getResource() {
        return new NeoForgeFluidVariant(storage.getFluid());
    }

    @Override
    public void setResource(IFluidVariant variant) {
        storage.setFluid(((NeoForgeFluidVariant) variant).raw);
    }

    @Override
    public boolean isResourceBlank() {
        return storage.isEmpty();
    }

    @Override
    public long insert(IFluidVariant variant, long maxAmount, boolean simulate) {
        if (simulate)
            return storage.fill(((NeoForgeFluidVariant) variant).raw.copyWithAmount((int) maxAmount), IFluidHandler.FluidAction.SIMULATE);

        onChange.run();
        return storage.fill(((NeoForgeFluidVariant) variant).raw.copyWithAmount((int) maxAmount), IFluidHandler.FluidAction.EXECUTE);
    }

    @Override
    public long extract(IFluidVariant variant, long maxAmount, boolean simulate) {
        if (simulate)
            return storage.drain(((NeoForgeFluidVariant) variant).raw.copyWithAmount((int) maxAmount), IFluidHandler.FluidAction.SIMULATE).getAmount();

        onChange.run();
        return storage.drain(((NeoForgeFluidVariant) variant).raw.copyWithAmount((int) maxAmount), IFluidHandler.FluidAction.EXECUTE).getAmount();
    }

    @Override
    public void writeNbt(WriteNbtArgs args) {
        storage.writeToNBT(args.nbt);
    }

    @Override
    public void readNbt(ReadNbtArgs args) {
        storage.readFromNBT(args.nbt);
    }
}
