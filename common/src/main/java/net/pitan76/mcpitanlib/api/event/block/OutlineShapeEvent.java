package net.pitan76.mcpitanlib.api.event.block;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.pitan76.mcpitanlib.api.event.BaseEvent;
import net.pitan76.mcpitanlib.api.state.property.IProperty;

public class OutlineShapeEvent extends BaseEvent {
    public BlockState state;
    public BlockGetter world;
    public BlockPos pos;
    public CollisionContext context;

    public OutlineShapeEvent(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
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

    public BlockGetter getWorld() {
        return world;
    }

    public CollisionContext getContext() {
        return context;
    }

    public <T extends Comparable<T>> T getProperty(Property<T> property) {
        return state.getValue(property);
    }

    public <T extends Comparable<T>> boolean containsProperty(Property<T> property) {
        return state.hasProperty(property);
    }

    public <T extends Comparable<T>, V extends T> BlockState with(Property<T> property, V value) {
        return state.setValue(property, value);
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
