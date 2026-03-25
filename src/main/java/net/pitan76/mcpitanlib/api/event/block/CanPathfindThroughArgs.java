package net.pitan76.mcpitanlib.api.event.block;

import net.minecraft.block.BlockState;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.fluid.FluidState;
import net.pitan76.mcpitanlib.api.event.BaseEvent;
import net.pitan76.mcpitanlib.api.util.FluidStateUtil;

public class CanPathfindThroughArgs extends BaseEvent {
    public BlockState state;
    public NavigationType type;

    public CanPathfindThroughArgs(BlockState state, NavigationType type) {
        this.state = state;

        this.type = type;
    }

    public BlockState getState() {
        return state;
    }

    public NavigationType getType() {
        return type;
    }

    public FluidState getFluidState() {
        return state.getFluidState();
    }

    public boolean isWaterNavigationType() {
        return type == NavigationType.WATER;
    }

    public boolean isAirNavigationType() {
        return type == NavigationType.AIR;
    }

    public boolean isLandNavigationType() {
        return type == NavigationType.LAND;
    }

    public boolean isWaterState() {
        return FluidStateUtil.isWater(getFluidState());
    }

    public boolean isLavaState() {
        return FluidStateUtil.isLava(getFluidState());
    }
}
