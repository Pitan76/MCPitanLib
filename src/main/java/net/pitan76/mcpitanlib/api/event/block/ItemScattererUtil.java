package net.pitan76.mcpitanlib.api.event.block;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.Containers;
import net.minecraft.core.NonNullList;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;

public class ItemScattererUtil {
    public static void spawn(Level world, BlockPos pos, BlockEntity blockEntity) {
        if (blockEntity instanceof Container) {
            spawn(world, pos, (Container) blockEntity);
        }
    }

    public static void spawn(Level world, BlockPos pos, Container inventory) {
        Containers.dropContents(world, pos, inventory);
    }

    public static void spawn(Level world, BlockPos pos, ItemStack stack) {
        Containers.dropItemStack(world, pos.getX(), pos.getY(), pos.getZ(), stack);
    }

    public static void spawn(Level world, BlockPos pos, NonNullList<ItemStack> stacks) {
        Containers.dropContents(world, pos, stacks);
    }

    public static void onStateReplaced(StateReplacedEvent e) {
        onStateReplaced(e.getState(), e.getNewState(), e.getWorld(), e.getPos());
    }

    public static void onStateReplaced(BlockState state, BlockState newState, Level world, BlockPos pos) {
        Containers.updateNeighboursAfterDestroy(state, world, pos);
    }

    public static void spawn(net.pitan76.mcpitanlib.midohra.world.World world, net.pitan76.mcpitanlib.midohra.util.math.BlockPos pos, ItemStack stack) {
        spawn(world.getRaw(), pos.toMinecraft(), stack);
    }

    public static void spawn(net.pitan76.mcpitanlib.midohra.world.World world, net.pitan76.mcpitanlib.midohra.util.math.BlockPos pos, NonNullList<ItemStack> stacks) {
        spawn(world.getRaw(), pos.toMinecraft(), stacks);
    }

    public static void spawn(net.pitan76.mcpitanlib.midohra.world.World world, net.pitan76.mcpitanlib.midohra.util.math.BlockPos pos, Container inventory) {
        spawn(world.getRaw(), pos.toMinecraft(), inventory);
    }
}
