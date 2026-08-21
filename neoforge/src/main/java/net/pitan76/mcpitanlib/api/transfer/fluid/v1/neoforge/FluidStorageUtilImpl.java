package net.pitan76.mcpitanlib.api.transfer.fluid.v1.neoforge;

import net.minecraft.fluid.Fluid;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.FluidType;
import net.neoforged.neoforge.fluids.capability.templates.FluidTank;
import net.pitan76.mcpitanlib.api.transfer.fluid.v1.IFluidStorage;
import net.pitan76.mcpitanlib.api.transfer.fluid.v1.IFluidVariant;

public class FluidStorageUtilImpl {
    public static IFluidStorage withFixedCapacity(long capacity, Runnable onChange) {
        return new NeoForgeFluidStorage(new FluidTank((int) capacity), onChange);
    }

    public static IFluidVariant getVariant(Fluid fluid) {
        return new NeoForgeFluidVariant(new FluidStack(fluid, 1));
    }

    public static long bucketAmount() {
        return FluidType.BUCKET_VOLUME;
    }
}
