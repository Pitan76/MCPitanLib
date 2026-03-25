package net.pitan76.mcpitanlib.api.transfer.fluid.v1;

import net.minecraft.fluid.Fluid;

public class FluidStorageUtil {
    public static IFluidStorage withFixedCapacity(long capacity, Runnable onChange) {
        return new FabricFluidStorage(SingleFluidStorage.withFixedCapacity(capacity, onChange));
    }

    public static IFluidVariant getVariant(Fluid fluid) {
        return new FabricFluidVariant(FluidVariant.of(fluid));
    }
}
