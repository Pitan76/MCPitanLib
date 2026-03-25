package net.pitan76.mcpitanlib.api.state.property;

import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Property;
import net.minecraft.util.math.Direction;
import net.pitan76.mcpitanlib.midohra.block.BlockState;

import java.util.function.Predicate;

public class DirectionProperty implements IProperty<Direction> {

    private final EnumProperty<Direction> property;

    public DirectionProperty(String name, Predicate<Direction> filter) {
        this(EnumProperty.of(name, Direction.class, filter));
    }

    public DirectionProperty(String name) {
        this(name, direction -> true);
    }

    public DirectionProperty(EnumProperty<Direction> property) {
        this.property = property;
    }

    public static DirectionProperty of(String name) {
        return new DirectionProperty(name);
    }

    public static DirectionProperty of(String name, Predicate<Direction> filter) {
        return new DirectionProperty(name, filter);
    }

    public net.pitan76.mcpitanlib.midohra.util.math.Direction getAsMidohra(BlockState state) {
        return net.pitan76.mcpitanlib.midohra.util.math.Direction.of(get(state.toMinecraft()));
    }

    public BlockState with(BlockState state, net.pitan76.mcpitanlib.midohra.util.math.Direction value) {
        return BlockState.of(with(state.toMinecraft(), value.toMinecraft()));
    }

    public BlockState cycle(BlockState state) {
        return BlockState.of(cycle(state.toMinecraft()));
    }

    @Override
    public Property<Direction> getProperty() {
        return property;
    }
}
