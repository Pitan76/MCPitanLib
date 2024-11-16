package net.pitan76.mcpitanlib.api.block.args.v2;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;
import net.minecraft.world.tick.ScheduledTickView;
import net.pitan76.mcpitanlib.api.util.math.random.CompatRandom;
import net.pitan76.mcpitanlib.midohra.block.entity.BlockEntityWrapper;
import net.pitan76.mcpitanlib.midohra.holder.BlockStatePropertyHolder;
import net.pitan76.mcpitanlib.midohra.world.IWorldView;

public class StateForNeighborUpdateArgs implements BlockStatePropertyHolder {
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

    public Direction getDirection() {
        return direction;
    }

    public BlockState getRawNeighborState() {
        return neighborState;
    }

    public WorldView getRawWorld() {
        return world;
    }

    public BlockPos getRawPos() {
        return pos;
    }

    public BlockPos getRawNeighborPos() {
        return neighborPos;
    }

    public net.pitan76.mcpitanlib.midohra.block.BlockState getNeighborState() {
        return net.pitan76.mcpitanlib.midohra.block.BlockState.of(getRawNeighborState());
    }

    public net.pitan76.mcpitanlib.midohra.world.WorldView getWorld() {
        return net.pitan76.mcpitanlib.midohra.world.WorldView.of(getRawWorld());
    }

    public IWorldView getWorldView() {
        return getWorld();
    }

    public net.pitan76.mcpitanlib.midohra.util.math.BlockPos getPos() {
        return net.pitan76.mcpitanlib.midohra.util.math.BlockPos.of(getRawPos());
    }

    public net.pitan76.mcpitanlib.midohra.util.math.BlockPos getNeighborPos() {
        return net.pitan76.mcpitanlib.midohra.util.math.BlockPos.of(getRawNeighborPos());
    }

    public CompatRandom getRandom() {
        return random;
    }

    public net.pitan76.mcpitanlib.midohra.world.tick.ScheduledTickView getTickView() {
        return net.pitan76.mcpitanlib.midohra.world.tick.ScheduledTickView.of(tickView);
    }

    @Override
    public net.pitan76.mcpitanlib.midohra.block.BlockState getBlockState() {
        return net.pitan76.mcpitanlib.midohra.block.BlockState.of(state);
    }

    public BlockEntityWrapper getBlockEntity() {
        return getWorld().getBlockEntity(getPos());
    }

    public BlockEntity getRawBlockEntity() {
        return world.getBlockEntity(pos);
    }
}
