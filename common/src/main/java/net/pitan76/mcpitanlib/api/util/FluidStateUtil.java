package net.pitan76.mcpitanlib.api.util;

import net.minecraft.block.BlockState;
import net.minecraft.fluid.FluidState;
import net.minecraft.tag.FluidTags;

public class FluidStateUtil {
    public static boolean isWater(FluidState state) {
        return state.isIn(FluidTags.WATER);
    }

    public static boolean isLava(FluidState state) {
        return state.isIn(FluidTags.LAVA);
    }

    @Deprecated
    public static FluidState getFluidState(BlockState state) {
        return state.getFluidState();
    }
}
