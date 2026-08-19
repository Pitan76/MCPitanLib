package net.pitan76.mcpitanlib.api.transfer.fluid.v1.forge;

import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.templates.FluidTank;
import net.pitan76.mcpitanlib.api.event.nbt.ReadNbtArgs;
import net.pitan76.mcpitanlib.api.event.nbt.WriteNbtArgs;
import net.pitan76.mcpitanlib.api.transfer.fluid.v1.IFluidStorage;
import net.pitan76.mcpitanlib.api.transfer.fluid.v1.IFluidVariant;

public class ForgeFluidStorage implements IFluidStorage {

    public final FluidTank storage;

    public Runnable onChange;

    public ForgeFluidStorage(FluidTank storage, Runnable onChange) {
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
        return new ForgeFluidVariant(storage.getFluid());
    }

    @Override
    public void setResource(IFluidVariant variant) {
        storage.setFluid(((ForgeFluidVariant) variant).raw);
    }

    @Override
    public boolean isResourceBlank() {
        return storage.isEmpty();
    }

    private static FluidStack copyWithAmount(FluidStack stack, int amount) {
        FluidStack copy = stack.copy();
        copy.setAmount(amount);
        return copy;
    }

    @Override
    public long insert(IFluidVariant variant, long maxAmount, boolean simulate) {
        FluidStack toFill = copyWithAmount(((ForgeFluidVariant) variant).raw, (int) maxAmount);
        if (simulate)
            return storage.fill(toFill, IFluidHandler.FluidAction.SIMULATE);

        onChange.run();
        return storage.fill(toFill, IFluidHandler.FluidAction.EXECUTE);
    }

    @Override
    public long extract(IFluidVariant variant, long maxAmount, boolean simulate) {
        FluidStack toDrain = copyWithAmount(((ForgeFluidVariant) variant).raw, (int) maxAmount);
        if (simulate)
            return storage.drain(toDrain, IFluidHandler.FluidAction.SIMULATE).getAmount();

        onChange.run();
        return storage.drain(toDrain, IFluidHandler.FluidAction.EXECUTE).getAmount();
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
