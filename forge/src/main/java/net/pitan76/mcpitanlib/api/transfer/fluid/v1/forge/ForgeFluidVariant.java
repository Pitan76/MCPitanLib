package net.pitan76.mcpitanlib.api.transfer.fluid.v1.forge;

import net.minecraft.fluid.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.pitan76.mcpitanlib.api.transfer.fluid.v1.IFluidVariant;

public class ForgeFluidVariant implements IFluidVariant {

    public final FluidStack raw;

    public ForgeFluidVariant() {
        this.raw = FluidStack.EMPTY;
    }

    public ForgeFluidVariant(FluidStack stack) {
        this.raw = stack;
    }

    @Override
    public Fluid getFluid() {
        return raw.getFluid();
    }

    @Override
    public boolean isBlank() {
        return raw.isEmpty();
    }

    @Override
    public boolean isSame(Fluid fluid) {
        return raw.getFluid() == fluid;
    }
}
