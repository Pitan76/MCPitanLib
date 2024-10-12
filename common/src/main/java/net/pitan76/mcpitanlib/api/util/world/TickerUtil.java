package net.pitan76.mcpitanlib.api.util.world;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.Entity;
import net.minecraft.util.Tickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class TickerUtil {
    public static <T extends BlockEntity> void tick(T blockEntity, World world, BlockPos pos, BlockState state) {
        tick(blockEntity);
    }

    public static <T extends BlockEntity> void tick(T blockEntity, World world, BlockPos pos) {
        tick(blockEntity);
    }

    public static <T extends BlockEntity> void tick(T blockEntity) {
        if (blockEntity instanceof Tickable)
            ((Tickable) blockEntity).tick();
    }

    public static <T extends Entity> void tick(T entity) {
        entity.tick();
    }
}
