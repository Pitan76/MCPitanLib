package net.pitan76.mcpitanlib.api.state.property;

import net.minecraft.world.level.block.state.properties.PistonType;
import net.pitan76.mcpitanlib.api.util.block.properties.CompatPistonType;
import net.pitan76.mcpitanlib.midohra.block.BlockState;

import java.util.function.Predicate;

public class PistonTypeProperty extends EnumProperty<PistonType> {

    public PistonTypeProperty(String name, Class<PistonType> type) {
        super(name, type);
    }

    public PistonTypeProperty(String name, Class<PistonType> type, Predicate<PistonType> filter) {
        super(name, type, filter);
    }

    public PistonTypeProperty(net.minecraft.world.level.block.state.properties.EnumProperty<PistonType> property) {
        super(property);
    }

    public static PistonTypeProperty ofRaw(net.minecraft.world.level.block.state.properties.EnumProperty<PistonType> property) {
        return new PistonTypeProperty(property);
    }

    public BlockState with(BlockState state, CompatPistonType value) {
        return super.with(state, value.getPistonType());
    }

    public CompatPistonType getCompat(BlockState state) {
        return CompatPistonType.of(super.get(state));
    }
}
