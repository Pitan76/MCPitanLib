package net.pitan76.mcpitanlib.api.transfer.fluid.v1;

import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import net.pitan76.mcpitanlib.midohra.fluid.FluidWrapper;

public interface IFluidVariant {
    Fluid getFluid();

    default FluidWrapper getWrapper() {
        return FluidWrapper.of(getFluid());
    }

    default boolean isSame(IFluidVariant variant) {
        return getFluid() == variant.getFluid();
    }

    default boolean isSame(Fluid fluid) {
        return getFluid() == fluid;
    }

    default boolean isBlank() {
        return getFluid() == null || getFluid() == Fluids.EMPTY;
    }

    static IFluidVariant of(Fluid fluid) {
        return FluidStorageUtil.getVariant(fluid);
    }
}
