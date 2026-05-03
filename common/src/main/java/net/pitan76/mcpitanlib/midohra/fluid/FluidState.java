package net.pitan76.mcpitanlib.midohra.fluid;

import net.pitan76.mcpitanlib.api.util.FluidStateUtil;
import net.pitan76.mcpitanlib.api.util.particle.effect.CompatParticleEffect;
import net.pitan76.mcpitanlib.midohra.block.BlockState;
import net.pitan76.mcpitanlib.midohra.util.math.Box;

public class FluidState {
    private final net.minecraft.world.level.material.FluidState fluidState;

    public FluidState(net.minecraft.world.level.material.FluidState fluidState) {
        this.fluidState = fluidState;
    }

    public static FluidState of(net.minecraft.world.level.material.FluidState fluidState) {
        return new FluidState(fluidState);
    }

    public net.minecraft.world.level.material.FluidState getRaw() {
        return fluidState;
    }

    public net.minecraft.world.level.material.FluidState toMinecraft() {
        return getRaw();
    }

    public boolean isNull() {
        return getRaw() == null;
    }

    public boolean isEmpty() {
        return isNull() || getRaw().isEmpty();
    }

    public FluidWrapper getFluid() {
        return FluidWrapper.of(getRaw().getType());
    }

    public BlockState getBlockState() {
        return BlockState.of(getRaw().createLegacyBlock());
    }

    public boolean hasRandomTicks() {
        return getRaw().isRandomlyTicking();
    }

    public CompatParticleEffect getParticle() {
        return CompatParticleEffect.of(getRaw().getDripParticle());
    }

    public int getLevel() {
        return getRaw().getAmount();
    }

    public boolean isStill() {
        return getRaw().isSource();
    }

    public boolean isFull() {
        return getRaw().isFull();
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
