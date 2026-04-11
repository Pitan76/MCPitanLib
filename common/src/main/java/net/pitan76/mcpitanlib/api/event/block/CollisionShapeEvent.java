package net.pitan76.mcpitanlib.api.event.block;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;

public class CollisionShapeEvent extends OutlineShapeEvent {

    public CollisionShapeEvent(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        super(state, world, pos, context);
    }
}
