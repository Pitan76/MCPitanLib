package net.pitan76.mcpitanlib.api.event.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;
import net.minecraft.world.tick.ScheduledTickView;
import net.pitan76.mcpitanlib.api.util.math.random.CompatRandom;

public class StateForNeighborUpdateArgs {
    public BlockState state;
    public Direction direction;
    public BlockState neighborState;
    public WorldView world;
    public BlockPos pos;
    public BlockPos neighborPos;
    public ScheduledTickView tickView;
    public CompatRandom random;

    public StateForNeighborUpdateArgs(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        this.state = state;
        this.direction = direction;
        this.neighborState = neighborState;
        this.world = world;
        this.pos = pos;
        this.neighborPos = neighborPos;
    }

    public StateForNeighborUpdateArgs(BlockState state, Direction direction, BlockState neighborState, WorldView world, BlockPos pos, BlockPos neighborPos, ScheduledTickView tickView, CompatRandom random) {
        this.state = state;
        this.direction = direction;
        this.neighborState = neighborState;
        this.world = world;
        this.pos = pos;
        this.neighborPos = neighborPos;
        this.tickView = tickView;
        this.random = random;
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

    public WorldView getWorld() {
        return world;
    }

    public BlockPos getPos() {
        return pos;
    }

    public BlockPos getNeighborPos() {
        return neighborPos;
    }

    public CompatRandom getRandom() {
        return random;
    }

    @Deprecated
    public ScheduledTickView getTickView() {
        return tickView;
    }
}
