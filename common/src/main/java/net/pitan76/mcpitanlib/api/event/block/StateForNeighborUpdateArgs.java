package net.pitan76.mcpitanlib.api.event.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.WorldAccess;

public class StateForNeighborUpdateArgs {
    public BlockState state;
    public Direction direction;
    public BlockState neighborState;
    public WorldAccess world;
    public BlockPos pos;
    public BlockPos neighborPos;

    public StateForNeighborUpdateArgs(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        this.state = state;
        this.direction = direction;
        this.neighborState = neighborState;
        this.world = world;
        this.pos = pos;
        this.neighborPos = neighborPos;
    }

    public BlockState getState() {
        return state;
    }

    public BlockEntity getBlockEntity() {
        return world.getBlockEntity(pos);
    }

    public BlockState getBlockState(BlockPos pos) {
        return world.getBlockState(pos);
    }

    public BlockEntity getBlockEntity(BlockPos pos) {
        return world.getBlockEntity(pos);
    }

    public Direction getDirection() {
        return direction;
    }

    public BlockState getNeighborState() {
        return neighborState;
    }

    public WorldAccess getWorld() {
        return world;
    }

    public BlockPos getPos() {
        return pos;
    }

    public BlockPos getNeighborPos() {
        return neighborPos;
    }
}
