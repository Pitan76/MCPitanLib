package net.pitan76.mcpitanlib.api.transfer.fluid.v1.fabric;

import net.fabricmc.fabric.api.transfer.v1.fluid.FluidConstants;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidStorage;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.fabricmc.fabric.api.transfer.v1.storage.Storage;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.fluid.Fluid;
import net.pitan76.mcpitanlib.api.extra.transfer.util.FluidStorageUtil;
import net.pitan76.mcpitanlib.api.transfer.fluid.v1.IFluidHandler;
import org.jetbrains.annotations.Nullable;

import java.util.function.BiFunction;
import java.util.function.Supplier;
import net.pitan76.mcpitanlib.api.transfer.fluid.v1.IFluidStorage;
import net.pitan76.mcpitanlib.api.transfer.fluid.v1.IFluidVariant;

public class FluidStorageUtilImpl {
    public static IFluidStorage withFixedCapacity(long capacity, Runnable onChange) {
        return new FabricFluidStorage(FluidStorageUtil.withFixedCapacity(capacity, onChange));
    }

    public static IFluidVariant getVariant(Fluid fluid) {
        return new FabricFluidVariant(FluidVariant.of(fluid));
    }

    public static long bucketAmount() {
        return FluidConstants.BUCKET;
    }

    @Nullable
    public static IFluidHandler getFluidHandler(World world, BlockPos pos, @Nullable Direction side) {
        flushPending();

        Storage<FluidVariant> storage = FluidStorage.SIDED.find(world, pos, side);
        if (storage == null) return null;

        return new FabricFluidHandler(storage);
    }

    public static void registerFluidStorageLazy(Supplier<BlockEntityType<?>> typeSupplier, BiFunction<BlockEntity, Direction, IFluidStorage> provider) {
        BlockEntityType<?> type = typeSupplier.get();
        if (type == null) {
            pending.add(new Object[]{typeSupplier, provider});
            return;
        }

        registerFluidStorage(type, provider);
    }

    private static final java.util.List<Object[]> pending = new java.util.concurrent.CopyOnWriteArrayList<>();

    @SuppressWarnings("unchecked")
    public static void flushPending() {
        if (pending.isEmpty()) return;

        for (Object[] entry : pending) {
            Supplier<BlockEntityType<?>> supplier = (Supplier<BlockEntityType<?>>) entry[0];
            BlockEntityType<?> type = supplier.get();
            if (type == null) continue;

            pending.remove(entry);
            registerFluidStorage(type, (BiFunction<BlockEntity, Direction, IFluidStorage>) entry[1]);
        }
    }

    public static void registerFluidStorage(BlockEntityType<?> type, BiFunction<BlockEntity, Direction, IFluidStorage> provider) {
        if (type == null) return;

        FluidStorage.SIDED.registerForBlockEntity((blockEntity, direction) -> {
            IFluidStorage storage = provider.apply(blockEntity, direction);
            if (!(storage instanceof FabricFluidStorage)) return null;

            return ((FabricFluidStorage) storage).storage;
        }, (BlockEntityType) type);
    }
}
