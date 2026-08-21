package net.pitan76.mcpitanlib.api.state.property;

import net.minecraft.world.level.block.state.properties.BedPart;
import net.pitan76.mcpitanlib.api.util.block.properties.CompatBedPart;
import net.pitan76.mcpitanlib.midohra.block.BlockState;

import java.util.function.Predicate;

public class BedPartProperty extends EnumProperty<BedPart> {

    public BedPartProperty(String name, Class<BedPart> type) {
        super(name, type);
    }

    public BedPartProperty(String name, Class<BedPart> type, Predicate<BedPart> filter) {
        super(name, type, filter);
    }

    public BedPartProperty(net.minecraft.world.level.block.state.properties.EnumProperty<BedPart> property) {
        super(property);
    }

    public static BedPartProperty ofRaw(net.minecraft.world.level.block.state.properties.EnumProperty<BedPart> property) {
        return new BedPartProperty(property);
    }

    public BlockState with(BlockState state, CompatBedPart value) {
        return super.with(state, value.getBedPart());
    }

    public CompatBedPart getCompat(BlockState state) {
        return CompatBedPart.of(super.get(state));
    }
}
