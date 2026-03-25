package net.pitan76.mcpitanlib.api.util.world;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.server.MinecraftServer;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelAccessor;

public class WorldAccessUtil extends WorldViewUtil {

    public static void scheduleBlockTick(LevelAccessor world, BlockPos pos, Block block, int delay) {
        world.scheduleTick(pos, block, delay);
    }

    public static void scheduleFluidTick(LevelAccessor world, BlockPos pos, Fluid fluid, int delay) {
        world.scheduleTick(pos, fluid, delay);
    }

    public static boolean setBlockState(LevelAccessor world, BlockPos pos, BlockState state, int flags) {
        return world.setBlock(pos, state, flags);
    }

    public static boolean setBlockState(LevelAccessor world, BlockPos pos, BlockState state, int flags, int maxUpdateDepth) {
        return world.setBlock(pos, state, flags, maxUpdateDepth);
    }

    public static boolean setBlockState(LevelAccessor world, BlockPos pos, BlockState state) {
        return setBlockState(world, pos, state, 3);
    }

    public static BlockState getBlockState(LevelAccessor world, BlockPos pos) {
        return world.getBlockState(pos);
    }

    public static boolean breakBlock(LevelAccessor world, BlockPos pos, boolean drop) {
        return world.destroyBlock(pos, drop);
    }

    public static boolean breakBlock(LevelAccessor world, BlockPos pos, boolean drop, Entity entity) {
        return world.destroyBlock(pos, drop, entity);
    }

    public static boolean removeBlock(LevelAccessor world, BlockPos pos, boolean move) {
        return world.removeBlock(pos, move);
    }

    public static MinecraftServer getServer(LevelAccessor world) {
        return world.getServer();
    }
}
