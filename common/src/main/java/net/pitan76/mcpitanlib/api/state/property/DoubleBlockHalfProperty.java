package net.pitan76.mcpitanlib.api.state.property;

import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.pitan76.mcpitanlib.api.util.block.properties.CompatDoubleBlockHalf;
import net.pitan76.mcpitanlib.midohra.block.BlockState;

import java.util.function.Predicate;

public class DoubleBlockHalfProperty extends EnumProperty<DoubleBlockHalf> {

    public DoubleBlockHalfProperty(String name, Class<DoubleBlockHalf> type) {
        super(name, type);
    }

    public DoubleBlockHalfProperty(String name, Class<DoubleBlockHalf> type, Predicate<DoubleBlockHalf> filter) {
        super(name, type, filter);
    }

    public DoubleBlockHalfProperty(net.minecraft.world.level.block.state.properties.EnumProperty<DoubleBlockHalf> property) {
        super(property);
    }

    public static DoubleBlockHalfProperty ofRaw(net.minecraft.world.level.block.state.properties.EnumProperty<DoubleBlockHalf> property) {
        return new DoubleBlockHalfProperty(property);
    }

    public BlockState with(BlockState state, CompatDoubleBlockHalf value) {
        return super.with(state, value.getDoubleBlockHalf());
    }

    public CompatDoubleBlockHalf getCompat(BlockState state) {
        return CompatDoubleBlockHalf.of(super.get(state));
    }
}
