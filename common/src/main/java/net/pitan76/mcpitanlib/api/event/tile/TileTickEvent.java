package net.pitan76.mcpitanlib.api.event.tile;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class TileTickEvent<T extends BlockEntity> {
    public World world;
    public BlockPos pos;
    public BlockState state;
    public T blockEntity;

    public TileTickEvent(World world, BlockPos pos, BlockState state, T blockEntity) {
        this.world = world;
        this.pos = pos;
        this.state = state;
        this.blockEntity = blockEntity;
    }
}
