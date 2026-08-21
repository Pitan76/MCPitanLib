package net.pitan76.mcpitanlib.api.util;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.item.ItemStack;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.redstone.Orientation;
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

    public static boolean isOpaque(BlockState state) {
        return state.canOcclude();
    }

    public static SoundType getSoundGroup(BlockState state) {
        return state.getSoundType();
    }

    public static CompatBlockSoundGroup getCompatSoundGroup(BlockState state) {
        return CompatBlockSoundGroup.of(getSoundGroup(state));
    }

    public static BlockState getDefaultState(Block block) {
        return block.defaultBlockState();
    }

    public static StateDefinition<Block, BlockState> getStateManager(Block block) {
        return block.getStateDefinition();
    }

    public static <T extends Comparable<T>, V extends T> BlockState with(BlockState state, Property<T> property, V value) {
        return state.setValue(property, value);
    }

    public static void neighborUpdate(BlockState state, Level world, BlockPos pos, Block block, BlockPos fromPos, boolean notify) {
        Orientation wireOrientation = Orientation.of(pos.above() == fromPos ? Direction.UP : Direction.DOWN, pos.north() == fromPos ? Direction.NORTH : Direction.SOUTH, pos.east() == fromPos ? Orientation.SideBias.RIGHT : Orientation.SideBias.LEFT);

        state.handleNeighborChanged(world, pos, block, wireOrientation, notify);
    }

    public static void updateNeighbors(BlockState state, LevelAccessor world, BlockPos pos, int flags) {
        state.updateNeighbourShapes(world, pos, flags);
    }

    public static boolean hasRandomTicks(BlockState state) {
        return state.isRandomlyTicking();
    }

    public static void randomTick(BlockState state, ServerLevel world, BlockPos pos) {
        state.randomTick(world, pos, world.getRandom());
    }

    public static CompatActionResult onUse(BlockState state, Level world, Player player, BlockHitResult hitResult) {
        return CompatActionResult.create(state.useWithoutItem(world, player.getEntity(), hitResult));
    }

    public static CompatActionResult onUse(BlockState state, Level world, Player player, Direction dir, BlockPos blockPos) {
        return onUse(state, world, player, BlockHitResultUtil.create(player.getPos(), dir, blockPos));
    }

    public static CompatActionResult onUseWithItem(BlockState state, ItemStack stack, Level world, net.minecraft.world.entity.player.Player player, InteractionHand hand, BlockHitResult hit) {
        return CompatActionResult.create(state.useItemOn(stack, world, player, hand, hit));
    }

    public static CompatActionResult onUseWithItem_actionResult(BlockState state, ItemStack stack, Level world, net.minecraft.world.entity.player.Player player, InteractionHand hand, BlockHitResult hit) {
        return onUseWithItem(state, stack, world, player, hand, hit);
    }

    public static FluidState getFluidState(BlockState state) {
        return state.getFluidState();
    }

    public static Fluid getFluid(BlockState state) {
        return getFluidState(state).getType();
    }

    public static BlockState rotate(BlockState state, Rotation rotation) {
        return state.rotate(rotation);
    }

    public static BlockState rotate(BlockState state, CompatBlockRotation rotation) {
        return rotate(state, rotation.getRaw());
    }

    public static BlockState mirror(BlockState state, Mirror mirror) {
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

    public static float getHardness(BlockState state, BlockGetter world, BlockPos pos) {
        return state.getDestroySpeed(world, pos);
    }

    public static float getHardness(BlockState state, Level world, BlockPos pos) {
        return state.getDestroySpeed(world, pos);
    }

    public static int getLuminance(BlockState state) {
        return state.getLightEmission();
    }

    public static int getOpacity(BlockState state) {
        return state.getLightDampening();
    }

    public static int getComparatorOutput(BlockState state, Level world, BlockPos pos) {
        return state.getAnalogOutputSignal(world, pos, Direction.NORTH);
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

    public static boolean canPlaceAt(BlockState state, BlockPos pos, Level world) {
        return state.canSurvive(world, pos);
    }

    public static boolean canPlaceAt(net.pitan76.mcpitanlib.midohra.block.BlockState state, net.pitan76.mcpitanlib.midohra.util.math.BlockPos pos, net.pitan76.mcpitanlib.midohra.world.World world) {
        return canPlaceAt(state.toMinecraft(), pos.toMinecraft(), world.getRaw());
    }

    public static boolean hasRandomTicks(net.pitan76.mcpitanlib.midohra.block.BlockState state) {
        return hasRandomTicks(state.toMinecraft());
    }

    public static void randomTick(net.pitan76.mcpitanlib.midohra.block.BlockState state, ServerLevel world, net.pitan76.mcpitanlib.midohra.util.math.BlockPos pos) {
        randomTick(state.toMinecraft(), world, pos.toMinecraft());
    }

    public static void randomTick(net.pitan76.mcpitanlib.midohra.block.BlockState state, net.pitan76.mcpitanlib.midohra.world.ServerWorld world, net.pitan76.mcpitanlib.midohra.util.math.BlockPos pos) {
        randomTick(state.toMinecraft(), world.getRaw(), pos.toMinecraft());
    }
}
