package net.pitan76.mcpitanlib.api.util.world;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldView;
import net.minecraft.world.dimension.DimensionType;

public class WorldViewUtil {

    public static boolean isClient(WorldView world) {
        return world.isClient();
    }

    public static BlockState getBlockState(WorldView world, BlockPos pos) {
        return world.getBlockState(pos);
    }

    public static Block getBlock(WorldView world, BlockPos pos) {
        return getBlockState(world, pos).getBlock();
    }

    public static BlockEntity getBlockEntity(WorldView world, BlockPos pos) {
        return world.getBlockEntity(pos);
    }

    public static FluidState getFluidState(WorldView world, BlockPos pos) {
        return getBlockState(world, pos).getFluidState();
    }

    public static Fluid getFluid(WorldView world, BlockPos pos) {
        return getFluidState(world, pos).getFluid();
    }

    public static int getBottomY(WorldView world) {
        return world.getBottomY();
    }

    public static int getTopY(WorldView world) {
        return world.getTopY();
    }

    public static boolean isChunkLoaded(WorldView world, BlockPos pos) {
        return world.isChunkLoaded(pos);
    }

    public static boolean isRegionLoaded(WorldView world, BlockPos min, BlockPos max) {
        return world.isRegionLoaded(min, max);
    }

    public static DimensionType getDimensionType(WorldView world) {
        return world.getDimension();
    }

    public static boolean isAirBlock(WorldView world, BlockPos pos) {
        return getBlockState(world, pos).isAir();
    }

    public static boolean isOpaqueBlock(WorldView world, BlockPos pos) {
        return getBlockState(world, pos).isOpaque();
    }

    public static boolean isWater(WorldView world, BlockPos pos) {
        return getFluidState(world, pos).isIn(FluidTags.WATER);
    }
}
