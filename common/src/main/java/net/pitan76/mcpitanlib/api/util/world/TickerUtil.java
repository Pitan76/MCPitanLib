package net.pitan76.mcpitanlib.api.util.world;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.entity.Entity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.pitan76.mcpitanlib.api.util.WorldUtil;
import net.pitan76.mcpitanlib.midohra.block.entity.BlockEntityWrapper;

public class TickerUtil {
    public static <T extends BlockEntity> void tick(T blockEntity, Level world, BlockPos pos, BlockState state) {
        if (isTicker(blockEntity))
            ((BlockEntityTicker<T>) blockEntity).tick(world, pos, state, blockEntity);
    }

    public static <T extends BlockEntity> void tick(T blockEntity, Level world, BlockPos pos) {
        tick(blockEntity, world, pos, WorldUtil.getBlockState(world, pos));
    }

    public static <T extends BlockEntity> void tick(T blockEntity) {
        tick(blockEntity, blockEntity.getLevel(), blockEntity.getBlockPos());
    }

    public static <T extends Entity> void tick(T entity) {
        entity.tick();
    }

    public static boolean isTicker(BlockEntity blockEntity) {
        return blockEntity instanceof BlockEntityTicker;
    }

    public static <T extends BlockEntity> void tick(T blockEntity, net.pitan76.mcpitanlib.midohra.world.World world, net.pitan76.mcpitanlib.midohra.util.math.BlockPos pos, net.pitan76.mcpitanlib.midohra.block.BlockState state) {
        tick(blockEntity, world.toMinecraft(), pos.toMinecraft(), state.toMinecraft());
    }

    public static <T extends BlockEntity> void tick(T blockEntity, net.pitan76.mcpitanlib.midohra.world.World world, net.pitan76.mcpitanlib.midohra.util.math.BlockPos pos) {
        tick(blockEntity, world.toMinecraft(), pos.toMinecraft());
    }

    public static void tick(BlockEntityWrapper blockEntity) {
        tick(blockEntity.get());
    }

    public static void tick(BlockEntityWrapper blockEntity, net.pitan76.mcpitanlib.midohra.world.World world, net.pitan76.mcpitanlib.midohra.util.math.BlockPos pos, net.pitan76.mcpitanlib.midohra.block.BlockState state) {
        tick(blockEntity.get(), world.toMinecraft(), pos.toMinecraft(), state.toMinecraft());
    }

    public static void tick(BlockEntityWrapper blockEntity, net.pitan76.mcpitanlib.midohra.world.World world, net.pitan76.mcpitanlib.midohra.util.math.BlockPos pos) {
        tick(blockEntity.get(), world.toMinecraft(), pos.toMinecraft());
    }
}
