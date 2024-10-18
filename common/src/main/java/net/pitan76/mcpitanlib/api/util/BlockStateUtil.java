package net.pitan76.mcpitanlib.api.util;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.Property;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.block.WireOrientation;
import net.pitan76.mcpitanlib.api.entity.Player;
import net.pitan76.mcpitanlib.api.sound.CompatBlockSoundGroup;
import net.pitan76.mcpitanlib.api.util.block.BlockHitResultUtil;

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

    public static CompatBlockSoundGroup getCompatSoundGroup(BlockState state) {
        return CompatBlockSoundGroup.of(getSoundGroup(state));
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
        WireOrientation wireOrientation = WireOrientation.of(pos.up() == fromPos ? Direction.UP : Direction.DOWN, pos.north() == fromPos ? Direction.NORTH : Direction.SOUTH, pos.east() == fromPos ? WireOrientation.SideBias.RIGHT : WireOrientation.SideBias.LEFT);

        state.neighborUpdate(world, pos, block, wireOrientation, notify);
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

    public static CompatActionResult onUse(BlockState state, World world, Player player, BlockHitResult hitResult) {
        return CompatActionResult.create(state.onUse(world, player.getEntity(), hitResult));
    }

    public static CompatActionResult onUse(BlockState state, World world, Player player, Direction dir, BlockPos blockPos) {
        return onUse(state, world, player, BlockHitResultUtil.create(player.getPos(), dir, blockPos));
    }

    public static CompatActionResult onUseWithItem(BlockState state, ItemStack stack, World world, PlayerEntity player, Hand hand, BlockHitResult hit) {
        return CompatActionResult.create(state.onUseWithItem(stack, world, player, hand, hit));
    }

    public static CompatActionResult onUseWithItem_actionResult(BlockState state, ItemStack stack, World world, PlayerEntity player, Hand hand, BlockHitResult hit) {
        return onUseWithItem(state, stack, world, player, hand, hit);
    }
}
