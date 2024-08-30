package net.pitan76.mcpitanlib.api.event.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.state.property.Property;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.pitan76.mcpitanlib.api.event.BaseEvent;

public class OutlineShapeEvent extends BaseEvent {
    public BlockState state;
    public BlockView world;
    public BlockPos pos;
    public ShapeContext context;

    public OutlineShapeEvent(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        this.state = state;
        this.world = world;
        this.pos = pos;
        this.context = context;
    }

    public BlockPos getPos() {
        return pos;
    }

    public BlockState getState() {
        return state;
    }

    public BlockView getWorld() {
        return world;
    }

    public ShapeContext getContext() {
        return context;
    }

    public <T extends Comparable<T>> T getProperty(Property<T> property) {
        return state.get(property);
    }
}
