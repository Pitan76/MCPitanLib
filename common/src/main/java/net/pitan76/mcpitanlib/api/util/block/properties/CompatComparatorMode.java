package net.pitan76.mcpitanlib.api.util.block.properties;

import net.minecraft.world.level.block.state.properties.ComparatorMode;
import net.pitan76.mcpitanlib.api.util.CompatStringIdentifiable;

public class CompatComparatorMode implements CompatStringIdentifiable {
    private final ComparatorMode comparatorMode;

    public static final CompatComparatorMode COMPARE = of(ComparatorMode.COMPARE);
    public static final CompatComparatorMode SUBTRACT = of(ComparatorMode.SUBTRACT);

    public CompatComparatorMode(ComparatorMode comparatorMode) {
        this.comparatorMode = comparatorMode;
    }

    public static CompatComparatorMode of(ComparatorMode comparatorMode) {
        return new CompatComparatorMode(comparatorMode);
    }

    public ComparatorMode getComparatorMode() {
        return comparatorMode;
    }

    public String getName() {
        return comparatorMode.name();
    }

    @Override
    public String asString_compat() {
        return getName();
    }

    @Override
    public int hashCode() {
        return comparatorMode.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        CompatComparatorMode other = (CompatComparatorMode) obj;
        return comparatorMode == other.comparatorMode;
    }
}
