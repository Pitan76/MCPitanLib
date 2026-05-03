package net.pitan76.mcpitanlib.midohra.fluid;

import net.pitan76.mcpitanlib.api.util.FluidStateUtil;
import net.pitan76.mcpitanlib.api.util.particle.effect.CompatParticleEffect;
import net.pitan76.mcpitanlib.midohra.block.BlockState;
import net.pitan76.mcpitanlib.midohra.util.math.Box;

public class FluidState {
    private final net.minecraft.fluid.FluidState fluidState;

    public FluidState(net.minecraft.fluid.FluidState fluidState) {
        this.fluidState = fluidState;
    }

    public static FluidState of(net.minecraft.fluid.FluidState fluidState) {
        return new FluidState(fluidState);
    }

    public net.minecraft.fluid.FluidState getRaw() {
        return fluidState;
    }

    public net.minecraft.fluid.FluidState toMinecraft() {
        return getRaw();
    }

    public boolean isNull() {
        return getRaw() == null;
    }

    public boolean isEmpty() {
        return isNull() || getRaw().isEmpty();
    }

    public FluidWrapper getFluid() {
        return FluidWrapper.of(getRaw().getFluid());
    }

    public BlockState getBlockState() {
        return BlockState.of(getRaw().getBlockState());
    }

    public boolean hasRandomTicks() {
        return getRaw().hasRandomTicks();
    }

    public CompatParticleEffect getParticle() {
        return CompatParticleEffect.of(getRaw().getParticle());
    }

    public int getLevel() {
        return getRaw().getLevel();
    }

    public boolean isStill() {
        return getRaw().isStill();
    }

    public boolean isFull() {
        return getRaw().getHeight() >= 1.0;
    }

    public boolean isWater() {
        return FluidStateUtil.isWater(getRaw());
    }

    public boolean isLava() {
        return FluidStateUtil.isLava(getRaw());
    }


    @Override
    public int hashCode() {
        return getRaw().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        FluidState other = (FluidState) obj;
        return getRaw().equals(other.getRaw());
    }

    public FluidState(FluidWrapper fluid) {
        this.fluidState = fluid.getDefaultState();
    }

    public static FluidState of(FluidWrapper fluid) {
        return new FluidState(fluid);
    }

    public static FluidState water() {
        return of(Fluids.WATER);
    }

    public static FluidState lava() {
        return of(Fluids.LAVA);
    }

    public static FluidState flowingWater() {
        return of(Fluids.FLOWING_WATER);
    }

    public static FluidState flowingLava() {
        return of(Fluids.FLOWING_LAVA);
    }
}
