package net.pitan76.mcpitanlib.api.util.block.properties;

import net.minecraft.world.level.block.state.properties.DoorHingeSide;
import net.pitan76.mcpitanlib.api.util.CompatStringIdentifiable;

public class CompatDoorHinge implements CompatStringIdentifiable {
    private final DoorHingeSide doorHinge;

    public static final CompatDoorHinge LEFT = of(DoorHingeSide.LEFT);
    public static final CompatDoorHinge RIGHT = of(DoorHingeSide.RIGHT);

    public CompatDoorHinge(DoorHingeSide doorHinge) {
        this.doorHinge = doorHinge;
    }

    public static CompatDoorHinge of(DoorHingeSide doorHinge) {
        return new CompatDoorHinge(doorHinge);
    }

    public DoorHingeSide getDoorHinge() {
        return doorHinge;
    }

    public DoorHingeSide getDoorHingeSide() {
        return doorHinge;
    }

    public String getName() {
        return doorHinge.name();
    }

    @Override
    public String asString_compat() {
        return getName();
    }

    @Override
    public int hashCode() {
        return doorHinge.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        CompatDoorHinge other = (CompatDoorHinge) obj;
        return doorHinge == other.doorHinge;
    }
}
