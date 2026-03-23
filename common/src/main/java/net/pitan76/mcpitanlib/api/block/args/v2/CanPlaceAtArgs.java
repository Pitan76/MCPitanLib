package net.pitan76.mcpitanlib.api.block.args.v2;

import net.pitan76.mcpitanlib.api.event.BaseEvent;
import net.pitan76.mcpitanlib.midohra.block.BlockState;
import net.pitan76.mcpitanlib.midohra.util.math.BlockPos;
import net.pitan76.mcpitanlib.midohra.world.WorldView;

public class CanPlaceAtArgs extends BaseEvent {
    public final net.minecraft.block.BlockState state;
    public final net.minecraft.world.WorldView world;
    public final net.minecraft.util.math.BlockPos pos;

    public CanPlaceAtArgs(net.minecraft.block.BlockState state, net.minecraft.world.WorldView world, net.minecraft.util.math.BlockPos pos) {
        this.state = state;
        this.world = world;
        this.pos = pos;
    }

    public CanPlaceAtArgs(BlockState state, WorldView world, BlockPos pos) {
        this(state.toMinecraft(), world.toMinecraft(), pos.toMinecraft());
    }

    public net.minecraft.block.BlockState getState() {
        return state;
    }

    public net.minecraft.world.WorldView getWorld() {
        return world;
    }

    public net.minecraft.util.math.BlockPos getPos() {
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
        return world.isClient();
    }

    public boolean isServer() {
        return !world.isClient();
    }
}
