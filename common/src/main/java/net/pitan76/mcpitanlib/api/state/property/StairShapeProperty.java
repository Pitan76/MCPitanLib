package net.pitan76.mcpitanlib.api.state.property;

import net.minecraft.world.level.block.state.properties.StairsShape;
import net.pitan76.mcpitanlib.api.util.block.properties.CompatStairShape;
import net.pitan76.mcpitanlib.midohra.block.BlockState;

import java.util.function.Predicate;

public class StairShapeProperty extends EnumProperty<StairsShape> {

    public StairShapeProperty(String name, Class<StairsShape> type) {
        super(name, type);
    }

    public StairShapeProperty(String name, Class<StairsShape> type, Predicate<StairsShape> filter) {
        super(name, type, filter);
    }

    public StairShapeProperty(net.minecraft.world.level.block.state.properties.EnumProperty<StairsShape> property) {
        super(property);
    }

    public static StairShapeProperty ofRaw(net.minecraft.world.level.block.state.properties.EnumProperty<StairsShape> property) {
        return new StairShapeProperty(property);
    }

    public BlockState with(BlockState state, CompatStairShape value) {
        return super.with(state, value.getStairShape());
    }

    public CompatStairShape getCompat(BlockState state) {
        return CompatStairShape.of(super.get(state));
    }
}
