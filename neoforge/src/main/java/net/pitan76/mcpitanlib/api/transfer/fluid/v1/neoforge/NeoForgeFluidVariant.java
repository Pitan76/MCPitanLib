package net.pitan76.mcpitanlib.api.transfer.fluid.v1.neoforge;

import net.minecraft.world.level.material.Fluid;
import net.neoforged.neoforge.fluids.FluidStack;
import net.pitan76.mcpitanlib.api.transfer.fluid.v1.IFluidVariant;

public class NeoForgeFluidVariant implements IFluidVariant {

    public final FluidStack raw;

    public NeoForgeFluidVariant() {
        this.raw = FluidStack.EMPTY;
    }

    public NeoForgeFluidVariant(FluidStack stack) {
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
        return raw.is(fluid);
    }
}
