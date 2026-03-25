package net.pitan76.mcpitanlib.api.tile;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.pitan76.mcpitanlib.api.event.tile.TileTickEvent;

public interface ExtendBlockEntityTicker<T extends BlockEntity> extends BlockEntityTicker<T> {
    @Override
    default void tick(Level world, BlockPos pos, BlockState state, T blockEntity) {
        tick(new TileTickEvent<>(world, pos, state, blockEntity));
    }

    void tick(TileTickEvent<T> event);
}
