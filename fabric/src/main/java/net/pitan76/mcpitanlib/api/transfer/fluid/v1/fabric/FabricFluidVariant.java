package net.pitan76.mcpitanlib.api.transfer.fluid.v1.fabric;

import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.minecraft.world.level.material.Fluid;
import net.pitan76.mcpitanlib.api.transfer.fluid.v1.IFluidVariant;

public class FabricFluidVariant implements IFluidVariant {

    public final FluidVariant raw;

    public FabricFluidVariant() {
        this.raw = FluidVariant.blank();
    }

    public FabricFluidVariant(FluidVariant variant) {
        this.raw = variant;
    }

    @Override
    public Fluid getFluid() {
        return raw.getFluid();
    }

    @Override
    public boolean isBlank() {
        return raw.isBlank();
    }

    @Override
    public boolean isSame(Fluid fluid) {
        return raw.isOf(fluid);
    }
}
