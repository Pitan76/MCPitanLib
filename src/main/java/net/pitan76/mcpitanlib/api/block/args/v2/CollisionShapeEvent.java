package net.pitan76.mcpitanlib.api.block.args.v2;

import net.minecraft.world.phys.shapes.CollisionContext;
import net.pitan76.mcpitanlib.midohra.block.BlockState;
import net.pitan76.mcpitanlib.midohra.util.math.BlockPos;
import net.pitan76.mcpitanlib.midohra.world.BlockView;

public class CollisionShapeEvent extends OutlineShapeEvent {

    public CollisionShapeEvent(BlockState state, BlockView world, BlockPos pos, CollisionContext context) {
        super(state, world, pos, context);
    }

    public CollisionShapeEvent(net.minecraft.world.level.block.state.BlockState state, net.minecraft.world.level.BlockGetter world, net.minecraft.core.BlockPos pos, CollisionContext context) {
        super(BlockState.of(state), BlockView.of(world), BlockPos.of(pos), context);
    }
}
