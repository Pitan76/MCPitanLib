package net.pitan76.mcpitanlib.api.util;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.Property;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.block.WireOrientation;
import net.pitan76.mcpitanlib.api.entity.Player;
import net.pitan76.mcpitanlib.api.sound.CompatBlockSoundGroup;
import net.pitan76.mcpitanlib.api.util.block.BlockHitResultUtil;
import net.pitan76.mcpitanlib.api.util.math.CompatBlockMirror;
import net.pitan76.mcpitanlib.api.util.math.CompatBlockRotation;
import net.pitan76.mcpitanlib.midohra.block.BlockWrapper;

public class BlockStateUtil {
    public static Block getBlock(BlockState state) {
        return state.getBlock();
    }

    public static boolean isAir(BlockState state) {
        return state.isAir();
    }

    /**
     * Check if the block of the state is a fluid block (e.g. water, lava).
     * Note: This is not the same as {@code !state.getFluidState().isEmpty()},
     * because waterlogged blocks also have a fluid state.
     * @param state BlockState to check.
     * @return true if the block is an instance of FluidBlock.
     */
    public static boolean isFluidBlock(BlockState state) {
        return state.getBlock() instanceof net.minecraft.block.FluidBlock;
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

    public static FluidState getFluidState(BlockState state) {
        return state.getFluidState();
    }

    public static Fluid getFluid(BlockState state) {
        return getFluidState(state).getFluid();
    }

    public static BlockState rotate(BlockState state, BlockRotation rotation) {
        return state.rotate(rotation);
    }

    public static BlockState rotate(BlockState state, CompatBlockRotation rotation) {
        return rotate(state, rotation.getRaw());
    }

    public static BlockState mirror(BlockState state, BlockMirror mirror) {
        return state.mirror(mirror);
    }

    public static BlockState mirror(BlockState state, CompatBlockMirror mirror) {
        return mirror(state, mirror.getRaw());
    }

    public static net.pitan76.mcpitanlib.midohra.block.BlockState getMidohraDefaultState(Block block) {
        return net.pitan76.mcpitanlib.midohra.block.BlockState.of(getDefaultState(block));
    }

    public static net.pitan76.mcpitanlib.midohra.block.BlockState getDefaultState(BlockWrapper block) {
        return block.getDefaultState();
    }

    public static float getHardness(BlockState state, BlockView world, BlockPos pos) {
        return state.getHardness(world, pos);
    }

    public static float getHardness(BlockState state, World world, BlockPos pos) {
        return state.getHardness(world, pos);
    }

    public static int getLuminance(BlockState state) {
        return state.getLuminance();
    }

    public static int getOpacity(BlockState state) {
        return state.getOpacity();
    }

    public static int getComparatorOutput(BlockState state, World world, BlockPos pos) {
        return state.getComparatorOutput(world, pos);
    }

    public static float getHardness(net.pitan76.mcpitanlib.midohra.block.BlockState state, net.pitan76.mcpitanlib.midohra.world.BlockView world, net.pitan76.mcpitanlib.midohra.util.math.BlockPos pos) {
        return getHardness(state.toMinecraft(), world.getRaw(), pos.toMinecraft());
    }

    public static int getLuminance(net.pitan76.mcpitanlib.midohra.block.BlockState state) {
        return getLuminance(state.toMinecraft());
    }

    public static int getOpacity(net.pitan76.mcpitanlib.midohra.block.BlockState state) {
        return getOpacity(state.toMinecraft());
    }

    public static int getComparatorOutput(net.pitan76.mcpitanlib.midohra.block.BlockState state, net.pitan76.mcpitanlib.midohra.world.World world, net.pitan76.mcpitanlib.midohra.util.math.BlockPos pos) {
        return getComparatorOutput(state.toMinecraft(), world.getRaw(), pos.toMinecraft());
    }

    public static boolean canPlaceAt(BlockState state, BlockPos pos, World world) {
        return state.canPlaceAt(world, pos);
    }

    public static boolean canPlaceAt(net.pitan76.mcpitanlib.midohra.block.BlockState state, net.pitan76.mcpitanlib.midohra.util.math.BlockPos pos, net.pitan76.mcpitanlib.midohra.world.World world) {
        return canPlaceAt(state.toMinecraft(), pos.toMinecraft(), world.getRaw());
    }

    public static boolean hasRandomTicks(net.pitan76.mcpitanlib.midohra.block.BlockState state) {
        return hasRandomTicks(state.toMinecraft());
    }

    public static void randomTick(net.pitan76.mcpitanlib.midohra.block.BlockState state, ServerWorld world, net.pitan76.mcpitanlib.midohra.util.math.BlockPos pos) {
        randomTick(state.toMinecraft(), world, pos.toMinecraft());
    }

    public static void randomTick(net.pitan76.mcpitanlib.midohra.block.BlockState state, net.pitan76.mcpitanlib.midohra.world.ServerWorld world, net.pitan76.mcpitanlib.midohra.util.math.BlockPos pos) {
        randomTick(state.toMinecraft(), world.getRaw(), pos.toMinecraft());
    }
}
