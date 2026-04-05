package net.pitan76.mcpitanlib.api.state.property;

import net.minecraft.block.enums.StairShape;
import net.pitan76.mcpitanlib.api.util.block.properties.CompatStairShape;
import net.pitan76.mcpitanlib.midohra.block.BlockState;

import java.util.function.Predicate;

public class StairShapeProperty extends EnumProperty<StairShape> {

    public StairShapeProperty(String name, Class<StairShape> type) {
        super(name, type);
    }

    public StairShapeProperty(String name, Class<StairShape> type, Predicate<StairShape> filter) {
        super(name, type, filter);
    }

    public StairShapeProperty(net.minecraft.state.property.EnumProperty<StairShape> property) {
        super(property);
    }

    public static StairShapeProperty ofRaw(net.minecraft.state.property.EnumProperty<StairShape> property) {
        return new StairShapeProperty(property);
    }

    public BlockState with(BlockState state, CompatStairShape value) {
        return super.with(state, value.getStairShape());
    }

    public CompatStairShape getCompat(BlockState state) {
        return CompatStairShape.of(super.get(state));
    }
}
