package net.pitan76.mcpitanlib.api.event.block;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.function.Function;

public class ShapesForStatesArgs {
    public Function<BlockState, VoxelShape> stateToShape;

    public ShapesForStatesArgs(Function<BlockState, VoxelShape> stateToShape) {
        this.stateToShape = stateToShape;
    }

    public Function<BlockState, VoxelShape> getStateToShape() {
        return stateToShape;
    }

    public VoxelShape getShape(BlockState state) {
        return stateToShape.apply(state);
    }
}
