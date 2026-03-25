package net.pitan76.mcpitanlib.api.block.args.v2;

import net.minecraft.block.ShapeContext;
import net.pitan76.mcpitanlib.midohra.block.BlockState;
import net.pitan76.mcpitanlib.midohra.util.math.BlockPos;
import net.pitan76.mcpitanlib.midohra.world.BlockView;

public class CollisionShapeEvent extends OutlineShapeEvent {

    public CollisionShapeEvent(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        super(state, world, pos, context);
    }

    public CollisionShapeEvent(net.minecraft.block.BlockState state, net.minecraft.world.BlockView world, net.minecraft.util.math.BlockPos pos, ShapeContext context) {
        super(BlockState.of(state), BlockView.of(world), BlockPos.of(pos), context);
    }
}
