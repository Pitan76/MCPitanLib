package net.pitan76.mcpitanlib.api.util.block.properties;

import net.minecraft.world.level.block.state.properties.PistonType;
import net.pitan76.mcpitanlib.api.util.CompatStringIdentifiable;

public class CompatPistonType implements CompatStringIdentifiable {
    private final PistonType pistonType;

    public static final CompatPistonType DEFAULT = of(PistonType.DEFAULT);
    public static final CompatPistonType STICKY = of(PistonType.STICKY);

    public CompatPistonType(PistonType pistonType) {
        this.pistonType = pistonType;
    }

    public static CompatPistonType of(PistonType pistonType) {
        return new CompatPistonType(pistonType);
    }

    public PistonType getPistonType() {
        return pistonType;
    }

    public String getName() {
        return pistonType.name();
    }

    @Override
    public String asString_compat() {
        return getName();
    }

    @Override
    public int hashCode() {
        return pistonType.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        CompatPistonType other = (CompatPistonType) obj;
        return pistonType == other.pistonType;
    }
}
