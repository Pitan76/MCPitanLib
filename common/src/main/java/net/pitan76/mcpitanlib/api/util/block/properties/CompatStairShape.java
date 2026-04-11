package net.pitan76.mcpitanlib.api.util.block.properties;

import net.minecraft.world.level.block.state.properties.StairsShape;
import net.pitan76.mcpitanlib.api.util.CompatStringIdentifiable;

public class CompatStairShape implements CompatStringIdentifiable {
    private final StairsShape stairShape;

    public static final CompatStairShape STRAIGHT = of(StairsShape.STRAIGHT);
    public static final CompatStairShape INNER_LEFT = of(StairsShape.INNER_LEFT);
    public static final CompatStairShape INNER_RIGHT = of(StairsShape.INNER_RIGHT);
    public static final CompatStairShape OUTER_LEFT = of(StairsShape.OUTER_LEFT);
    public static final CompatStairShape OUTER_RIGHT = of(StairsShape.OUTER_RIGHT);

    public CompatStairShape(StairsShape stairShape) {
        this.stairShape = stairShape;
    }

    public static CompatStairShape of(StairsShape stairShape) {
        return new CompatStairShape(stairShape);
    }

    public StairsShape getStairShape() {
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
