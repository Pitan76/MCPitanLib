package net.pitan76.mcpitanlib.api.event.block;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.level.material.FluidState;
import net.pitan76.mcpitanlib.api.event.BaseEvent;
import net.pitan76.mcpitanlib.api.util.FluidStateUtil;

public class CanPathfindThroughArgs extends BaseEvent {
    public BlockState state;
    public PathComputationType type;

    public CanPathfindThroughArgs(BlockState state, PathComputationType type) {
        this.state = state;

        this.type = type;
    }

    public BlockState getState() {
        return state;
    }

    public PathComputationType getType() {
        return type;
    }

    public FluidState getFluidState() {
        return state.getFluidState();
    }

    public boolean isWaterNavigationType() {
        return type == PathComputationType.WATER;
    }

    public boolean isAirNavigationType() {
        return type == PathComputationType.AIR;
    }

    public boolean isLandNavigationType() {
        return type == PathComputationType.LAND;
    }

    public boolean isWaterState() {
        return FluidStateUtil.isWater(getFluidState());
    }

    public boolean isLavaState() {
        return FluidStateUtil.isLava(getFluidState());
    }
}
