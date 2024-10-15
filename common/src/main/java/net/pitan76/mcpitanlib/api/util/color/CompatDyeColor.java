package net.pitan76.mcpitanlib.api.util.color;

import net.minecraft.util.DyeColor;
import net.pitan76.mcpitanlib.api.util.CompatStringIdentifiable;

public class CompatDyeColor implements CompatStringIdentifiable {
    private final DyeColor color;

    public static final CompatDyeColor WHITE = of(DyeColor.WHITE);
    public static final CompatDyeColor ORANGE = of(DyeColor.ORANGE);
    public static final CompatDyeColor MAGENTA = of(DyeColor.MAGENTA);
    public static final CompatDyeColor LIGHT_BLUE = of(DyeColor.LIGHT_BLUE);
    public static final CompatDyeColor YELLOW = of(DyeColor.YELLOW);
    public static final CompatDyeColor LIME = of(DyeColor.LIME);
    public static final CompatDyeColor PINK = of(DyeColor.PINK);
    public static final CompatDyeColor GRAY = of(DyeColor.GRAY);
    public static final CompatDyeColor LIGHT_GRAY = of(DyeColor.LIGHT_GRAY);
    public static final CompatDyeColor CYAN = of(DyeColor.CYAN);
    public static final CompatDyeColor PURPLE = of(DyeColor.PURPLE);
    public static final CompatDyeColor BLUE = of(DyeColor.BLUE);
    public static final CompatDyeColor BROWN = of(DyeColor.BROWN);
    public static final CompatDyeColor GREEN = of(DyeColor.GREEN);
    public static final CompatDyeColor RED = of(DyeColor.RED);
    public static final CompatDyeColor BLACK = of(DyeColor.BLACK);

    public CompatDyeColor(DyeColor color) {
        this.color = color;
    }

    public static CompatDyeColor of(DyeColor color) {
        return new CompatDyeColor(color);
    }

    public DyeColor get() {
        return color;
    }

    public String getName() {
        return color.getName();
    }

    @Override
    public String asString_compat() {
        return getName();
    }
}
