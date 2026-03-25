package net.pitan76.mcpitanlib.api.event.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.state.property.Property;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.pitan76.mcpitanlib.api.event.BaseEvent;
import net.pitan76.mcpitanlib.api.state.property.IProperty;

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

    public <T extends Comparable<T>> boolean containsProperty(Property<T> property) {
        return state.contains(property);
    }

    public <T extends Comparable<T>, V extends T> BlockState with(Property<T> property, V value) {
        return state.with(property, value);
    }

    public <T extends Comparable<T>> T get(IProperty<T> property) {
        return getProperty(property.getProperty());
    }

    public <T extends Comparable<T>> boolean contains(IProperty<T> property) {
        return containsProperty(property.getProperty());
    }

    public <T extends Comparable<T>, V extends T> net.pitan76.mcpitanlib.midohra.block.BlockState with(IProperty<T> property, V value) {
        return net.pitan76.mcpitanlib.midohra.block.BlockState.of(with(property.getProperty(), value));
    }
}
