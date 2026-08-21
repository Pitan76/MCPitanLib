package net.pitan76.mcpitanlib.api.transfer.fluid.v1.forge;

import net.minecraft.fluid.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.capability.templates.FluidTank;
import net.pitan76.mcpitanlib.api.transfer.fluid.v1.IFluidStorage;
import net.pitan76.mcpitanlib.api.transfer.fluid.v1.IFluidVariant;

public class FluidStorageUtilImpl {
    public static IFluidStorage withFixedCapacity(long capacity, Runnable onChange) {
        return new ForgeFluidStorage(new FluidTank((int) capacity), onChange);
    }

    public static IFluidVariant getVariant(Fluid fluid) {
        return new ForgeFluidVariant(new FluidStack(fluid, 1));
    }

    public static long bucketAmount() {
        return FluidAttributes.BUCKET_VOLUME;
    }
}
