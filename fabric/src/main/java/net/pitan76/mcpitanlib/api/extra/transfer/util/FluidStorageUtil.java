package net.pitan76.mcpitanlib.api.extra.transfer.util;

import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.fabricmc.fabric.api.transfer.v1.fluid.base.SingleFluidStorage;
import net.fabricmc.fabric.api.transfer.v1.transaction.Transaction;
import net.minecraft.fluid.FluidState;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.world.World;
import net.pitan76.mcpitanlib.api.event.nbt.NbtRWArgs;

public class FluidStorageUtil {
    public static SingleFluidStorage withFixedCapacity(long capacity, Runnable onChange) {
        return SingleFluidStorage.withFixedCapacity(capacity, onChange);
    }

    public static void readNbt(SingleFluidStorage storage, NbtRWArgs args) {
        storage.readNbt(args.getNbt(), args.getWrapperLookup());
    }

    public static void writeNbt(SingleFluidStorage storage, NbtRWArgs args) {
        storage.writeNbt(args.getNbt(), args.getWrapperLookup());
    }

    /**
     * @deprecated Use {@link #readNbt(SingleFluidStorage, NbtRWArgs)} instead
     */
    @Deprecated
    public static void readNbt(SingleFluidStorage storage, NbtCompound nbt, World world) {
        storage.readNbt(nbt, world.getRegistryManager());
    }

    /**
     * @deprecated Use {@link #writeNbt(SingleFluidStorage, NbtRWArgs)} instead
     */
    @Deprecated
    public static void writeNbt(SingleFluidStorage storage, NbtCompound nbt, World world) {
        storage.writeNbt(nbt, world.getRegistryManager());
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
            insert(storage, FluidVariant.of(fluidState.getFluid()), maxAmount, transaction);
            transaction.commit();
        }
    }

    public static void extract(SingleFluidStorage storage, FluidState fluidState, long maxAmount) {
        try (Transaction transaction = Transaction.openOuter()) {
            extract(storage, FluidVariant.of(fluidState.getFluid()), maxAmount, transaction);
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
