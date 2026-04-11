package net.pitan76.mcpitanlib.api.transfer.fluid.v1;

import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.fabricmc.fabric.api.transfer.v1.fluid.base.SingleFluidStorage;
import net.minecraft.world.level.material.Fluid;
import net.pitan76.mcpitanlib.api.transfer.fluid.v1.FabricFluidVariant;

public class FluidStorageUtil {
    public static IFluidStorage withFixedCapacity(long capacity, Runnable onChange) {
        return new FabricFluidStorage(SingleFluidStorage.withFixedCapacity(capacity, onChange));
    }

    public static IFluidVariant getVariant(Fluid fluid) {
        return new FabricFluidVariant(FluidVariant.of(fluid));
    }
}
