package net.pitan76.mcpitanlib.api.state.property;

import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.pitan76.mcpitanlib.midohra.block.BlockState;
import net.pitan76.mcpitanlib.midohra.util.math.Axis;

import java.util.function.Predicate;

public class AxisProperty implements IProperty<Direction.Axis> {
    private final EnumProperty<Direction.Axis> property;

    public AxisProperty(String name, Predicate<Direction.Axis> filter) {
        this(EnumProperty.create(name, Direction.Axis.class, filter));
    }

    public AxisProperty(String name) {
        this(name, axis -> true);
    }

    public AxisProperty(EnumProperty<Direction.Axis> property) {
        this.property = property;
    }


    public static AxisProperty of(String name) {
        return new AxisProperty(name);
    }

    public static AxisProperty of(String name, Predicate<Direction.Axis> filter) {
        return new AxisProperty(name, filter);
    }

    public Axis getAsMidohra(BlockState state) {
        return Axis.of(get(state.toMinecraft()));
    }

    public BlockState with(BlockState state, Axis value) {
        return BlockState.of(with(state.toMinecraft(), value.toMinecraft()));
    }

    public BlockState cycle(BlockState state) {
        return BlockState.of(cycle(state.toMinecraft()));
    }

    @Override
    public EnumProperty<Direction.Axis> getProperty() {
        return property;
    }
}
