package net.pitan76.mcpitanlib.api.util.world;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.pitan76.mcpitanlib.api.util.WorldUtil;

public class TickerUtil {
    public static <T extends BlockEntity> void tick(T blockEntity, World world, BlockPos pos, BlockState state) {
        if (blockEntity instanceof BlockEntityTicker)
            ((BlockEntityTicker<T>) blockEntity).tick(world, pos, state, blockEntity);
    }

    public static <T extends BlockEntity> void tick(T blockEntity, World world, BlockPos pos) {
        tick(blockEntity, world, pos, WorldUtil.getBlockState(world, pos));
    }

    public static <T extends BlockEntity> void tick(T blockEntity) {
        tick(blockEntity, blockEntity.getWorld(), blockEntity.getPos());
    }

    public static <T extends Entity> void tick(T entity) {
        entity.tick();
    }
}
