package net.pitan76.mcpitanlib.api.util.block.properties;

import net.minecraft.world.level.block.state.properties.BedPart;
import net.pitan76.mcpitanlib.api.util.CompatStringIdentifiable;

public class CompatBedPart implements CompatStringIdentifiable {
    private final BedPart bedPart;

    public static final CompatBedPart HEAD = of(BedPart.HEAD);
    public static final CompatBedPart FOOT = of(BedPart.FOOT);

    public CompatBedPart(BedPart bedPart) {
        this.bedPart = bedPart;
    }

    public static CompatBedPart of(BedPart bedPart) {
        return new CompatBedPart(bedPart);
    }

    public BedPart getBedPart() {
        return bedPart;
    }

    public String getName() {
        return bedPart.name();
    }

    @Override
    public String asString_compat() {
        return getName();
    }

    @Override
    public int hashCode() {
        return bedPart.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        CompatBedPart other = (CompatBedPart) obj;
        return bedPart == other.bedPart;
    }
}
