package net.pitan76.mcpitanlib.api.util;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.tags.FluidTags;
import net.pitan76.mcpitanlib.midohra.fluid.FluidWrapper;
import net.pitan76.mcpitanlib.midohra.util.math.BlockPos;
import net.pitan76.mcpitanlib.midohra.world.World;

public class FluidStateUtil {
    public static boolean isWater(FluidState state) {
        return state.is(FluidTags.WATER);
    }

    public static boolean isLava(FluidState state) {
        return state.is(FluidTags.LAVA);
    }

    @Deprecated
    public static FluidState getFluidState(BlockState state) {
        return state.getFluidState();
    }

    public static FluidState getDefaultState(Fluid state) {
        return state.defaultFluidState();
    }

    public static Fluid getFluid(FluidState state) {
        return state.getType();
    }

    public static FluidWrapper getFluidWrapper(FluidState state) {
        return FluidWrapper.of(getFluid(state));
    }

    public static FluidWrapper getFluidWrapper(BlockState state) {
        return getFluidWrapper(getFluidState(state));
    }

    public static FluidWrapper getFluidWrapper(net.pitan76.mcpitanlib.midohra.block.BlockState state) {
        return getFluidWrapper(state.toMinecraft());
    }

    public static FluidWrapper getFluidWrapper(World world, BlockPos pos) {
        return getFluidWrapper(world.getBlockState(pos));
    }
}
