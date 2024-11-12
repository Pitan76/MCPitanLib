package net.pitan76.mcpitanlib.api.block.args;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.Direction;

public class SideInvisibleArgs {
    public BlockState state;
    public BlockState stateFrom;
    public Direction direction;

    public SideInvisibleArgs(BlockState state, BlockState stateFrom, Direction direction) {
        this.state = state;
        this.stateFrom = stateFrom;
        this.direction = direction;
    }

    public SideInvisibleArgs(net.pitan76.mcpitanlib.midohra.block.BlockState state, net.pitan76.mcpitanlib.midohra.block.BlockState stateFrom, net.pitan76.mcpitanlib.midohra.util.math.Direction direction) {
        this.state = state.toMinecraft();
        this.stateFrom = stateFrom.toMinecraft();
        this.direction = direction.toMinecraft();
    }

    public BlockState getState() {
        return state;
    }

    public BlockState getStateFrom() {
        return stateFrom;
    }

    public Direction getDirection() {
        return direction;
    }

    public net.pitan76.mcpitanlib.midohra.block.BlockState getMidohraState() {
        return net.pitan76.mcpitanlib.midohra.block.BlockState.of(getState());
    }

    public net.pitan76.mcpitanlib.midohra.block.BlockState getMidohraStateFrom() {
        return net.pitan76.mcpitanlib.midohra.block.BlockState.of(getStateFrom());
    }

    public net.pitan76.mcpitanlib.midohra.util.math.Direction getMidohraDirection() {
        return net.pitan76.mcpitanlib.midohra.util.math.Direction.of(getDirection());
    }
}
