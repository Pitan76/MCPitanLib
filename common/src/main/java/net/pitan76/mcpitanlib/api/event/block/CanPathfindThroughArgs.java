package net.pitan76.mcpitanlib.api.event.block;

import net.minecraft.block.BlockState;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.fluid.FluidState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.pitan76.mcpitanlib.api.event.BaseEvent;
import net.pitan76.mcpitanlib.api.util.FluidStateUtil;

public class CanPathfindThroughArgs extends BaseEvent {
    public BlockState state;
    private BlockView blockView;
    private BlockPos pos;
    public NavigationType type;

    public CanPathfindThroughArgs(BlockState state, BlockView blockView, BlockPos pos, NavigationType type) {
        this.state = state;
        this.blockView = blockView;
        this.pos = pos;
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

    @Deprecated(forRemoval = true)
    public BlockPos getPos() {
        return pos;
    }

    @Deprecated(forRemoval = true)
    public BlockView getBlockView() {
        return blockView;
    }
}
