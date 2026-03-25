package net.pitan76.mcpitanlib.api.block.args.v2;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.pitan76.mcpitanlib.api.event.BaseEvent;
import net.pitan76.mcpitanlib.api.util.WorldUtil;
import net.pitan76.mcpitanlib.api.util.screen.ScreenHandlerUtil;

public class GetComparatorOutputArgs extends BaseEvent {
    public BlockState state;
    public World world;
    public BlockPos pos;
    public Direction direction;

    public GetComparatorOutputArgs(BlockState state, World world, BlockPos pos) {
        this(state, world, pos, Direction.NORTH);
    }

    public GetComparatorOutputArgs(BlockState state, World world, BlockPos pos, Direction direction) {
        this.state = state;
        this.world = world;
        this.pos = pos;
        this.direction = direction;
    }

    public BlockState getState() {
        return state;
    }

    public World getWorld() {
        return world;
    }

    public BlockPos getPos() {
        return pos;
    }

    public BlockEntity getBlockEntity() {
        return WorldUtil.getBlockEntity(world, pos);
    }

    public int calcComparatorOutputFromBlockEntity() {
        return ScreenHandlerUtil.calcComparatorOutput(getBlockEntity());
    }

    public net.pitan76.mcpitanlib.midohra.block.BlockState getMidohraState() {
        return net.pitan76.mcpitanlib.midohra.block.BlockState.of(state);
    }

    public net.pitan76.mcpitanlib.midohra.world.World getMidohraWorld() {
        return net.pitan76.mcpitanlib.midohra.world.World.of(world);
    }

    public net.pitan76.mcpitanlib.midohra.util.math.BlockPos getMidohraPos() {
        return net.pitan76.mcpitanlib.midohra.util.math.BlockPos.of(pos);
    }
}
