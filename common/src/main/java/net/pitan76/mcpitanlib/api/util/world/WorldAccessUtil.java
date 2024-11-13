package net.pitan76.mcpitanlib.api.util.world;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.fluid.Fluid;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldAccess;

public class WorldAccessUtil extends WorldViewUtil {

    public static void scheduleBlockTick(WorldAccess world, BlockPos pos, Block block, int delay) {
        world.createAndScheduleBlockTick(pos, block, delay);
    }

    public static void scheduleFluidTick(WorldAccess world, BlockPos pos, Fluid fluid, int delay) {
        world.createAndScheduleFluidTick(pos, fluid, delay);
    }

    public static boolean setBlockState(WorldAccess world, BlockPos pos, BlockState state, int flags) {
        return world.setBlockState(pos, state, flags);
    }

    public static boolean setBlockState(WorldAccess world, BlockPos pos, BlockState state, int flags, int maxUpdateDepth) {
        return world.setBlockState(pos, state, flags, maxUpdateDepth);
    }

    public static boolean setBlockState(WorldAccess world, BlockPos pos, BlockState state) {
        return setBlockState(world, pos, state, 3);
    }

    public static BlockState getBlockState(WorldAccess world, BlockPos pos) {
        return world.getBlockState(pos);
    }

    public static boolean breakBlock(WorldAccess world, BlockPos pos, boolean drop) {
        return world.breakBlock(pos, drop);
    }

    public static boolean breakBlock(WorldAccess world, BlockPos pos, boolean drop, Entity entity) {
        return world.breakBlock(pos, drop, entity);
    }

    public static boolean removeBlock(WorldAccess world, BlockPos pos, boolean move) {
        return world.removeBlock(pos, move);
    }

    public static MinecraftServer getServer(WorldAccess world) {
        return world.getServer();
    }
}
