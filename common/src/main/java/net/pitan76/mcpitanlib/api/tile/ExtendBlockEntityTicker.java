package net.pitan76.mcpitanlib.api.tile;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.pitan76.mcpitanlib.api.event.tile.TileTickEvent;

public interface ExtendBlockEntityTicker<T extends BlockEntity> extends BlockEntityTicker<T> {
    @Override
    default void tick(World world, BlockPos pos, BlockState state, BlockEntity blockEntity) {
        tick(new TileTickEvent(world, pos, state, blockEntity));
    }

    void tick(TileTickEvent event);
}
