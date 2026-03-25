package net.pitan76.mcpitanlib.api.event.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;

public class CollisionShapeEvent extends OutlineShapeEvent {

    public CollisionShapeEvent(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        super(state, world, pos, context);
    }
}
