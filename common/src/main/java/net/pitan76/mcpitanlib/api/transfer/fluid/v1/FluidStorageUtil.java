package net.pitan76.mcpitanlib.api.transfer.fluid.v1;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.fluid.Fluid;

public class FluidStorageUtil {
    @ExpectPlatform
    public static IFluidStorage withFixedCapacity(long capacity, Runnable onChange) {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static IFluidVariant getVariant(Fluid fluid) {
        throw new AssertionError();
    }
}
