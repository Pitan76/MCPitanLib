package net.pitan76.mcpitanlib.api.util.block.properties;

import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.pitan76.mcpitanlib.api.util.CompatStringIdentifiable;

public class CompatDoubleBlockHalf implements CompatStringIdentifiable {
    private final DoubleBlockHalf doubleBlockHalf;

    public static final CompatDoubleBlockHalf UPPER = of(DoubleBlockHalf.UPPER);
    public static final CompatDoubleBlockHalf LOWER = of(DoubleBlockHalf.LOWER);

    public CompatDoubleBlockHalf(DoubleBlockHalf doubleBlockHalf) {
        this.doubleBlockHalf = doubleBlockHalf;
    }

    public static CompatDoubleBlockHalf of(DoubleBlockHalf doubleBlockHalf) {
        return new CompatDoubleBlockHalf(doubleBlockHalf);
    }

    public DoubleBlockHalf getDoubleBlockHalf() {
        return doubleBlockHalf;
    }

    public String getName() {
        return doubleBlockHalf.name();
    }

    @Override
    public String asString_compat() {
        return getName();
    }

    @Override
    public int hashCode() {
        return doubleBlockHalf.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        CompatDoubleBlockHalf other = (CompatDoubleBlockHalf) obj;
        return doubleBlockHalf == other.doubleBlockHalf;
    }
}
