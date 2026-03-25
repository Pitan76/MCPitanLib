package net.pitan76.mcpitanlib.api.event.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemScattererUtil {
    public static void spawn(World world, BlockPos pos, BlockEntity blockEntity) {
        if (blockEntity instanceof Inventory) {
            spawn(world, pos, (Inventory) blockEntity);
        }
    }

    public static void spawn(World world, BlockPos pos, Inventory inventory) {
        ItemScatterer.spawn(world, pos, inventory);
    }

    public static void spawn(World world, BlockPos pos, ItemStack stack) {
        ItemScatterer.spawn(world, pos.getX(), pos.getY(), pos.getZ(), stack);
    }

    public static void spawn(World world, BlockPos pos, DefaultedList<ItemStack> stacks) {
        ItemScatterer.spawn(world, pos, stacks);
    }

    public static void onStateReplaced(StateReplacedEvent e) {
        onStateReplaced(e.getState(), e.getNewState(), e.getWorld(), e.getPos());
    }

    public static void onStateReplaced(BlockState state, BlockState newState, World world, BlockPos pos) {
        ItemScatterer.onStateReplaced(state, world, pos);
    }

    public static void spawn(net.pitan76.mcpitanlib.midohra.world.World world, net.pitan76.mcpitanlib.midohra.util.math.BlockPos pos, ItemStack stack) {
        spawn(world.getRaw(), pos.toMinecraft(), stack);
    }

    public static void spawn(net.pitan76.mcpitanlib.midohra.world.World world, net.pitan76.mcpitanlib.midohra.util.math.BlockPos pos, DefaultedList<ItemStack> stacks) {
        spawn(world.getRaw(), pos.toMinecraft(), stacks);
    }

    public static void spawn(net.pitan76.mcpitanlib.midohra.world.World world, net.pitan76.mcpitanlib.midohra.util.math.BlockPos pos, Inventory inventory) {
        spawn(world.getRaw(), pos.toMinecraft(), inventory);
    }
}
