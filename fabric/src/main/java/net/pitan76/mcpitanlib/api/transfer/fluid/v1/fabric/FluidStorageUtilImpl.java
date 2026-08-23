package net.pitan76.mcpitanlib.api.transfer.fluid.v1.fabric;

import net.fabricmc.fabric.api.transfer.v1.fluid.FluidConstants;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidStorage;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.fabricmc.fabric.api.transfer.v1.fluid.base.SingleFluidStorage;
import net.fabricmc.fabric.api.transfer.v1.storage.Storage;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.fluid.Fluid;
import net.pitan76.mcpitanlib.api.transfer.fluid.v1.IFluidHandler;
import org.jetbrains.annotations.Nullable;

import java.util.function.BiFunction;
import net.pitan76.mcpitanlib.api.transfer.fluid.v1.IFluidStorage;
import net.pitan76.mcpitanlib.api.transfer.fluid.v1.IFluidVariant;

public class FluidStorageUtilImpl {
    public static IFluidStorage withFixedCapacity(long capacity, Runnable onChange) {
        return new FabricFluidStorage(SingleFluidStorage.withFixedCapacity(capacity, onChange));
    }

    public static IFluidVariant getVariant(Fluid fluid) {
        return new FabricFluidVariant(FluidVariant.of(fluid));
    }

    public static long bucketAmount() {
        return FluidConstants.BUCKET;
    }

    @Nullable
    public static IFluidHandler getFluidHandler(World world, BlockPos pos, @Nullable Direction side) {
        Storage<FluidVariant> storage = FluidStorage.SIDED.find(world, pos, side);
        if (storage == null) return null;

        return new FabricFluidHandler(storage);
    }

    public static void registerFluidStorage(BlockEntityType<?> type, BiFunction<BlockEntity, Direction, IFluidStorage> provider) {
        FluidStorage.SIDED.registerForBlockEntity((blockEntity, direction) -> {
            IFluidStorage storage = provider.apply(blockEntity, direction);
            if (!(storage instanceof FabricFluidStorage)) return null;

            return ((FabricFluidStorage) storage).storage;
        }, (BlockEntityType) type);
    }
}
