package net.pitan76.mcpitanlib.api.extra.transfer.util;

import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.fabricmc.fabric.api.transfer.v1.fluid.base.SingleFluidStorage;
import net.fabricmc.fabric.api.transfer.v1.transaction.Transaction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.FluidState;
import net.pitan76.mcpitanlib.api.event.nbt.NbtRWArgs;
import net.pitan76.mcpitanlib.api.event.nbt.ReadNbtArgs;
import net.pitan76.mcpitanlib.api.event.nbt.WriteNbtArgs;
import net.pitan76.mcpitanlib.api.registry.CompatRegistryLookup;
import net.pitan76.mcpitanlib.core.mc1216.NbtDataConverter;

public class FluidStorageUtil {
    public static SingleFluidStorage withFixedCapacity(long capacity, Runnable onChange) {
        return SingleFluidStorage.withFixedCapacity(capacity, onChange);
    }

    public static void readNbt(SingleFluidStorage storage, NbtRWArgs args) {
        if (args instanceof ReadNbtArgs)
            storage.readValue(((ReadNbtArgs) args).view);
    }

    public static void writeNbt(SingleFluidStorage storage, NbtRWArgs args) {
        if (args instanceof WriteNbtArgs)
            storage.writeValue(((WriteNbtArgs) args).view);
    }

    public static void readNbt(SingleFluidStorage storage, CompoundTag nbt, CompatRegistryLookup registryLookup) {
        storage.readValue(NbtDataConverter.nbt2readData(nbt, registryLookup));
    }

    public static void writeNbt(SingleFluidStorage storage, CompoundTag nbt, CompatRegistryLookup registryLookup) {
        storage.writeValue(NbtDataConverter.nbt2writeData(nbt, registryLookup));
    }

    /**
     * @deprecated Use {@link #readNbt(SingleFluidStorage, NbtRWArgs)} instead
     */
    @Deprecated
    public static void readNbt(SingleFluidStorage storage, CompoundTag nbt, Level world) {
        readNbt(storage, nbt, new CompatRegistryLookup(world.registryAccess()));
    }

    /**
     * @deprecated Use {@link #writeNbt(SingleFluidStorage, NbtRWArgs)} instead
     */
    @Deprecated
    public static void writeNbt(SingleFluidStorage storage, CompoundTag nbt, Level world) {
        writeNbt(storage, nbt, new CompatRegistryLookup(world.registryAccess()));
    }

    public static long getAmount(SingleFluidStorage storage) {
        return storage.getAmount();
    }

    public static long getCapacity(SingleFluidStorage storage) {
        return storage.getCapacity();
    }

    public static FluidVariant getResource(SingleFluidStorage storage) {
        return storage.getResource();
    }

    public static boolean isResourceBlank(SingleFluidStorage storage) {
        return storage.isResourceBlank();
    }

    public static long insert(SingleFluidStorage storage, FluidVariant insertedVariant, long maxAmount, Transaction transaction) {
        return storage.insert(insertedVariant, maxAmount, transaction);
    }

    public static long extract(SingleFluidStorage storage, FluidVariant extractedVariant, long maxAmount, Transaction transaction) {
        return storage.extract(extractedVariant, maxAmount, transaction);
    }

    public static void insert(SingleFluidStorage storage, FluidState fluidState, long maxAmount) {
        try (Transaction transaction = Transaction.openOuter()) {
            insert(storage, FluidVariant.of(fluidState.getType()), maxAmount, transaction);
            transaction.commit();
        }
    }

    public static void extract(SingleFluidStorage storage, FluidState fluidState, long maxAmount) {
        try (Transaction transaction = Transaction.openOuter()) {
            extract(storage, FluidVariant.of(fluidState.getType()), maxAmount, transaction);
            transaction.commit();
        }
    }

    public static boolean isEmpty(SingleFluidStorage storage) {
        return getAmount(storage) == 0;
    }

    public static boolean isFull(SingleFluidStorage storage) {
        return getAmount(storage) >= getCapacity(storage);
    }
}
