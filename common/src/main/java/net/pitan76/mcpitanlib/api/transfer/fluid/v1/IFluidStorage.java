package net.pitan76.mcpitanlib.api.transfer.fluid.v1;

import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.nbt.NbtCompound;
import net.pitan76.mcpitanlib.api.event.nbt.ReadNbtArgs;
import net.pitan76.mcpitanlib.api.event.nbt.WriteNbtArgs;
import net.pitan76.mcpitanlib.api.registry.CompatRegistryLookup;
import net.pitan76.mcpitanlib.midohra.fluid.FluidWrapper;

public interface IFluidStorage {
    long getAmount();

    long getCapacity();

    IFluidVariant getResource();

    void setResource(IFluidVariant variant);

    long insert(IFluidVariant variant, long maxAmount, boolean simulate);

    long extract(IFluidVariant variant, long maxAmount, boolean simulate);

    void writeNbt(WriteNbtArgs args);

    void readNbt(ReadNbtArgs args);

    default void readNbt(WriteNbtArgs args) {
        readNbt(new ReadNbtArgs(args.getNbt(), args.getRegistryLookup()));
    }

    default long insert(IFluidVariant variant, long maxAmount) {
        return insert(variant, maxAmount, false);
    }

    default long extract(IFluidVariant variant, long maxAmount) {
        return extract(variant, maxAmount, false);
    }

    default long insert(Fluid fluid, long maxAmount) {
        return insert(FluidStorageUtil.getVariant(fluid), maxAmount);
    }

    default long extract(Fluid fluid, long maxAmount) {
        return extract(FluidStorageUtil.getVariant(fluid), maxAmount);
    }

    default long insert(FluidWrapper wrapper, long maxAmount) {
        return insert(wrapper.get(), maxAmount);
    }

    default long extract(FluidWrapper wrapper, long maxAmount) {
        return extract(wrapper.get(), maxAmount);
    }

    default boolean isResourceBlank() {
        return getResource() == null || getAmount() <= 0;
    }

    default boolean isResource(IFluidVariant variant) {
        return getResource() == variant;
    }

    default boolean isEmpty() {
        return getAmount() <= 0;
    }

    default boolean isFull() {
        return getAmount() >= getCapacity();
    }

    default boolean canInsert(IFluidVariant variant, long amount) {
        return insert(variant, amount, true) == amount;
    }

    default boolean canExtract(IFluidVariant variant, long amount) {
        return extract(variant, amount, true) == amount;
    }

    default boolean canInsert(Fluid fluid, long amount) {
        return canInsert(FluidStorageUtil.getVariant(fluid), amount);
    }

    default boolean canExtract(Fluid fluid, long amount) {
        return canExtract(FluidStorageUtil.getVariant(fluid), amount);
    }

    default boolean canInsert(FluidWrapper wrapper, long amount) {
        return canInsert(wrapper.get(), amount);
    }

    default boolean canExtract(FluidWrapper wrapper, long amount) {
        return canExtract(wrapper.get(), amount);
    }

    default void writeNbt(NbtCompound nbt, CompatRegistryLookup registryLookup) {
        writeNbt(new WriteNbtArgs(nbt, registryLookup));
    }

    default void readNbt(NbtCompound nbt, CompatRegistryLookup registryLookup) {
        readNbt(new WriteNbtArgs(nbt, registryLookup));
    }

}
