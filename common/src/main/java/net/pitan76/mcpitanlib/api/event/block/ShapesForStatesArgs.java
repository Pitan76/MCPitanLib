package net.pitan76.mcpitanlib.api.event.block;

import net.minecraft.block.BlockState;
import net.minecraft.util.shape.VoxelShape;

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
