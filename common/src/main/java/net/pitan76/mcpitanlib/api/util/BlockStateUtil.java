package net.pitan76.mcpitanlib.api.util;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.Property;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.pitan76.mcpitanlib.api.entity.Player;
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

    public static ActionResult onUse(BlockState state, World world, Player player, BlockHitResult hitResult) {
        Hand hand = player.getMainHandStack().isEmpty() ? Hand.OFF_HAND : Hand.MAIN_HAND;
        return state.onUse(world, player.getEntity(), hand, hitResult);
    }

    public static ActionResult onUse(BlockState state, World world, Player player, Direction dir, BlockPos blockPos) {
        return onUse(state, world, player, BlockHitResultUtil.create(player.getPos(), dir, blockPos));
    }

    public static ActionResult onUseWithItem(BlockState state, ItemStack stack, World world, PlayerEntity player, Hand hand, BlockHitResult hit) {
        return state.onUse(world, player, hand, hit);
    }

    public static ActionResult onUseWithItem_actionResult(BlockState state, ItemStack stack, World world, PlayerEntity player, Hand hand, BlockHitResult hit) {
        return onUseWithItem(state, stack, world, player, hand, hit);
    }
}
