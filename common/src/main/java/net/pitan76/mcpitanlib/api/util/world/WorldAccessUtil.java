package net.pitan76.mcpitanlib.api.util.world;

import net.minecraft.block.Block;
import net.minecraft.fluid.Fluid;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldAccess;

public class WorldAccessUtil extends WorldViewUtil {

    public static void scheduleBlockTick(WorldAccess world, BlockPos pos, Block block, int delay) {
        world.getBlockTickScheduler().schedule(pos, block, delay);
    }

    public static void scheduleFluidTick(WorldAccess world, BlockPos pos, Fluid fluid, int delay) {
        world.getFluidTickScheduler().schedule(pos, fluid, delay);
    }
}
