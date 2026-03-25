package net.pitan76.mcpitanlib.api.block.args.v2;

import net.pitan76.mcpitanlib.api.event.BaseEvent;
import net.pitan76.mcpitanlib.midohra.block.BlockState;
import net.pitan76.mcpitanlib.midohra.util.math.BlockPos;
import net.pitan76.mcpitanlib.midohra.world.WorldView;

public class CanPlaceAtArgs extends BaseEvent {
    public final net.minecraft.world.level.block.state.BlockState state;
    public final net.minecraft.world.level.LevelReader world;
    public final net.minecraft.core.BlockPos pos;

    public CanPlaceAtArgs(net.minecraft.world.level.block.state.BlockState state, net.minecraft.world.level.LevelReader world, net.minecraft.core.BlockPos pos) {
        this.state = state;
        this.world = world;
        this.pos = pos;
    }

    public CanPlaceAtArgs(BlockState state, WorldView world, BlockPos pos) {
        this(state.toMinecraft(), world.toMinecraft(), pos.toMinecraft());
    }

    public net.minecraft.world.level.block.state.BlockState getState() {
        return state;
    }

    public net.minecraft.world.level.LevelReader getWorld() {
        return world;
    }

    public net.minecraft.core.BlockPos getPos() {
        return pos;
    }

    public BlockState getMidohraState() {
        return BlockState.of(state);
    }

    public WorldView getMidohraWorld() {
        return WorldView.of(world);
    }

    public BlockPos getMidohraPos() {
        return BlockPos.of(pos);
    }

    public boolean isClient() {
        return world.isClientSide();
    }

    public boolean isServer() {
        return !world.isClientSide();
    }
}
