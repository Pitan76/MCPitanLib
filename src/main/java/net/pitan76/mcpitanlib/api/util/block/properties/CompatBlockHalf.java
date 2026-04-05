package net.pitan76.mcpitanlib.api.util.block.properties;

import net.minecraft.world.level.block.state.properties.Half;
import net.pitan76.mcpitanlib.api.util.CompatStringIdentifiable;

public class CompatBlockHalf implements CompatStringIdentifiable {
    private final Half blockHalf;

    public static final CompatBlockHalf TOP = of(Half.TOP);
    public static final CompatBlockHalf BOTTOM = of(Half.BOTTOM);

    public CompatBlockHalf(Half blockHalf) {
        this.blockHalf = blockHalf;
    }

    public static CompatBlockHalf of(Half blockHalf) {
        return new CompatBlockHalf(blockHalf);
    }

    public Half getBlockHalf() {
        return blockHalf;
    }

    public String getName() {
        return blockHalf.name();
    }

    @Override
    public String asString_compat() {
        return getName();
    }

    @Override
    public int hashCode() {
        return blockHalf.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        CompatBlockHalf other = (CompatBlockHalf) obj;
        return blockHalf == other.blockHalf;
    }
}
