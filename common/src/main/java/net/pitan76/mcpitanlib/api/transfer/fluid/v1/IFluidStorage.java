package net.pitan76.mcpitanlib.api.transfer.fluid.v1;

import net.minecraft.world.level.material.FluidState;
import net.minecraft.nbt.CompoundTag;
import net.pitan76.mcpitanlib.api.event.nbt.ReadNbtArgs;
import net.pitan76.mcpitanlib.api.event.nbt.WriteNbtArgs;
import net.pitan76.mcpitanlib.api.registry.CompatRegistryLookup;

public interface IFluidStorage extends IFluidHandler {
    long getAmount();

    long getCapacity();

    IFluidVariant getResource();

    void setResource(IFluidVariant variant);

    void writeNbt(WriteNbtArgs args);

    void readNbt(ReadNbtArgs args);

    default void readNbt(WriteNbtArgs args) {
        readNbt(new ReadNbtArgs(args.getNbt(), args.getWrapperLookup()));
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

    default void writeNbt(CompoundTag nbt, CompatRegistryLookup registryLookup) {
        writeNbt(new WriteNbtArgs(nbt, registryLookup));
    }

    default void readNbt(CompoundTag nbt, CompatRegistryLookup registryLookup) {
        readNbt(new WriteNbtArgs(nbt, registryLookup));
    }

}
