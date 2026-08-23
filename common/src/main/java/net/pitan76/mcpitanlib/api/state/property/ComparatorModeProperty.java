package net.pitan76.mcpitanlib.api.state.property;

import net.minecraft.world.level.block.state.properties.ComparatorMode;
import net.pitan76.mcpitanlib.api.util.block.properties.CompatComparatorMode;
import net.pitan76.mcpitanlib.midohra.block.BlockState;

import java.util.function.Predicate;

public class ComparatorModeProperty extends EnumProperty<ComparatorMode> {

    public ComparatorModeProperty(String name, Class<ComparatorMode> type) {
        super(name, type);
    }

    public ComparatorModeProperty(String name, Class<ComparatorMode> type, Predicate<ComparatorMode> filter) {
        super(name, type, filter);
    }

    public ComparatorModeProperty(net.minecraft.world.level.block.state.properties.EnumProperty<ComparatorMode> property) {
        super(property);
    }

    public static ComparatorModeProperty ofRaw(net.minecraft.world.level.block.state.properties.EnumProperty<ComparatorMode> property) {
        return new ComparatorModeProperty(property);
    }

    public BlockState with(BlockState state, CompatComparatorMode value) {
        return super.with(state, value.getComparatorMode());
    }

    public CompatComparatorMode getCompat(BlockState state) {
        return CompatComparatorMode.of(super.get(state));
    }
}
