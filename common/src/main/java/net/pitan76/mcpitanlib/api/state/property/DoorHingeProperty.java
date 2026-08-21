package net.pitan76.mcpitanlib.api.state.property;

import net.minecraft.world.level.block.state.properties.DoorHingeSide;
import net.pitan76.mcpitanlib.api.util.block.properties.CompatDoorHinge;
import net.pitan76.mcpitanlib.midohra.block.BlockState;

import java.util.function.Predicate;

public class DoorHingeProperty extends EnumProperty<DoorHingeSide> {

    public DoorHingeProperty(String name, Class<DoorHingeSide> type) {
        super(name, type);
    }

    public DoorHingeProperty(String name, Class<DoorHingeSide> type, Predicate<DoorHingeSide> filter) {
        super(name, type, filter);
    }

    public DoorHingeProperty(net.minecraft.world.level.block.state.properties.EnumProperty<DoorHingeSide> property) {
        super(property);
    }

    public static DoorHingeProperty ofRaw(net.minecraft.world.level.block.state.properties.EnumProperty<DoorHingeSide> property) {
        return new DoorHingeProperty(property);
    }

    public BlockState with(BlockState state, CompatDoorHinge value) {
        return super.with(state, value.getDoorHinge());
    }

    public CompatDoorHinge getCompat(BlockState state) {
        return CompatDoorHinge.of(super.get(state));
    }
}
