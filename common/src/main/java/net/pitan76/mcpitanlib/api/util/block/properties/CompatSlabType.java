package net.pitan76.mcpitanlib.api.util.block.properties;

import net.minecraft.world.level.block.state.properties.SlabType;
import net.pitan76.mcpitanlib.api.util.CompatStringIdentifiable;

public class CompatSlabType implements CompatStringIdentifiable {
    private final SlabType slabType;

    public static final CompatSlabType TOP = of(SlabType.TOP);
    public static final CompatSlabType BOTTOM = of(SlabType.BOTTOM);
    public static final CompatSlabType DOUBLE = of(SlabType.DOUBLE);

    public CompatSlabType(SlabType slabType) {
        this.slabType = slabType;
    }

    public static CompatSlabType of(SlabType slabType) {
        return new CompatSlabType(slabType);
    }

    public SlabType getSlabType() {
        return slabType;
    }

    public String getName() {
        return slabType.name();
    }

    @Override
    public String asString_compat() {
        return getName();
    }

    @Override
    public int hashCode() {
        return slabType.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        CompatSlabType other = (CompatSlabType) obj;
        return slabType == other.slabType;
    }
}
