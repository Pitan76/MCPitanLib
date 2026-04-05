package net.pitan76.mcpitanlib.api.util.block.properties;

import net.minecraft.block.enums.StairShape;
import net.pitan76.mcpitanlib.api.util.CompatStringIdentifiable;

public class CompatStairShape implements CompatStringIdentifiable {
    private final StairShape stairShape;

    public static final CompatStairShape STRAIGHT = of(StairShape.STRAIGHT);
    public static final CompatStairShape INNER_LEFT = of(StairShape.INNER_LEFT);
    public static final CompatStairShape INNER_RIGHT = of(StairShape.INNER_RIGHT);
    public static final CompatStairShape OUTER_LEFT = of(StairShape.OUTER_LEFT);
    public static final CompatStairShape OUTER_RIGHT = of(StairShape.OUTER_RIGHT);

    public CompatStairShape(StairShape stairShape) {
        this.stairShape = stairShape;
    }

    public static CompatStairShape of(StairShape stairShape) {
        return new CompatStairShape(stairShape);
    }

    public StairShape getStairShape() {
        return stairShape;
    }

    public String getName() {
        return stairShape.name();
    }

    @Override
    public String asString_compat() {
        return getName();
    }

    @Override
    public int hashCode() {
        return stairShape.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        CompatStairShape other = (CompatStairShape) obj;
        return stairShape == other.stairShape;
    }
}
