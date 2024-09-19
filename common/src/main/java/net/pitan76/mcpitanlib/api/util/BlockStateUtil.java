package net.pitan76.mcpitanlib.api.util;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.Property;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;

public class BlockStateUtil {
    public static Block getBlock(BlockState state) {
        return state.getBlock();
    }

    public static boolean isAir(BlockState state) {
        return state.isAir();
    }

    public static boolean isOpaque(BlockState state) {
        return state.isOpaque();
    }

    public static BlockSoundGroup getSoundGroup(BlockState state) {
        return state.getSoundGroup();
    }

    public static BlockState getDefaultState(Block block) {
        return block.getDefaultState();
    }

    public static StateManager<Block, BlockState> getStateManager(Block block) {
        return block.getStateManager();
    }

    public static <T extends Comparable<T>, V extends T> BlockState with(BlockState state, Property<T> property, V value) {
        return state.with(property, value);
    }

    public static void neighborUpdate(BlockState state, World world, BlockPos pos, Block block, BlockPos fromPos, boolean notify) {
        state.neighborUpdate(world, pos, block, fromPos, notify);
    }

    public static void updateNeighbors(BlockState state, WorldAccess world, BlockPos pos, int flags) {
        state.updateNeighbors(world, pos, flags);
    }

    public static boolean hasRandomTicks(BlockState state) {
        return state.hasRandomTicks();
    }

    public static void randomTick(BlockState state, ServerWorld world, BlockPos pos) {
        state.randomTick(world, pos, world.random);
    }
}
