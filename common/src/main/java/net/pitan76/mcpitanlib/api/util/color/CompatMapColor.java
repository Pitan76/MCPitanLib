package net.pitan76.mcpitanlib.api.util.color;

import net.minecraft.block.MapColor;

public class CompatMapColor {
    private final MapColor color;

    public static final CompatMapColor CLEAR = of(MapColor.CLEAR);
    public static final CompatMapColor PALE_GREEN = of(MapColor.PALE_GREEN);
    public static final CompatMapColor PALE_YELLOW = of(MapColor.PALE_YELLOW);
    public static final CompatMapColor WHITE_GRAY = of(MapColor.WHITE_GRAY);
    public static final CompatMapColor BRIGHT_RED = of(MapColor.BRIGHT_RED);
    public static final CompatMapColor PALE_PURPLE = of(MapColor.PALE_PURPLE);
    public static final CompatMapColor IRON_GRAY = of(MapColor.IRON_GRAY);
    public static final CompatMapColor DARK_GREEN = of(MapColor.DARK_GREEN);
    public static final CompatMapColor WHITE = of(MapColor.WHITE);
    public static final CompatMapColor LIGHT_BLUE_GRAY = of(MapColor.LIGHT_BLUE_GRAY);
    public static final CompatMapColor DIRT_BROWN = of(MapColor.DIRT_BROWN);
    public static final CompatMapColor STONE_GRAY = of(MapColor.STONE_GRAY);
    public static final CompatMapColor WATER_BLUE = of(MapColor.WATER_BLUE);
    public static final CompatMapColor OAK_TAN = of(MapColor.OAK_TAN);
    public static final CompatMapColor OFF_WHITE = of(MapColor.OFF_WHITE);
    public static final CompatMapColor ORANGE = of(MapColor.ORANGE);
    public static final CompatMapColor MAGENTA = of(MapColor.MAGENTA);
    public static final CompatMapColor LIGHT_BLUE = of(MapColor.LIGHT_BLUE);
    public static final CompatMapColor YELLOW = of(MapColor.YELLOW);
    public static final CompatMapColor LIME = of(MapColor.LIME);
    public static final CompatMapColor PINK = of(MapColor.PINK);
    public static final CompatMapColor GRAY = of(MapColor.GRAY);
    public static final CompatMapColor LIGHT_GRAY = of(MapColor.LIGHT_GRAY);
    public static final CompatMapColor CYAN = of(MapColor.CYAN);
    public static final CompatMapColor PURPLE = of(MapColor.PURPLE);
    public static final CompatMapColor BLUE = of(MapColor.BLUE);
    public static final CompatMapColor BROWN = of(MapColor.BROWN);
    public static final CompatMapColor GREEN = of(MapColor.GREEN);
    public static final CompatMapColor RED = of(MapColor.RED);
    public static final CompatMapColor BLACK = of(MapColor.BLACK);
    public static final CompatMapColor GOLD = of(MapColor.GOLD);
    public static final CompatMapColor DIAMOND_BLUE = of(MapColor.DIAMOND_BLUE);
    public static final CompatMapColor LAPIS_BLUE = of(MapColor.LAPIS_BLUE);
    public static final CompatMapColor EMERALD_GREEN = of(MapColor.EMERALD_GREEN);
    public static final CompatMapColor SPRUCE_BROWN = of(MapColor.SPRUCE_BROWN);
    public static final CompatMapColor DARK_RED = of(MapColor.DARK_RED);
    public static final CompatMapColor TERRACOTTA_WHITE = of(MapColor.TERRACOTTA_WHITE);
    public static final CompatMapColor TERRACOTTA_ORANGE = of(MapColor.TERRACOTTA_ORANGE);
    public static final CompatMapColor TERRACOTTA_MAGENTA = of(MapColor.TERRACOTTA_MAGENTA);
    public static final CompatMapColor TERRACOTTA_LIGHT_BLUE = of(MapColor.TERRACOTTA_LIGHT_BLUE);
    public static final CompatMapColor TERRACOTTA_YELLOW = of(MapColor.TERRACOTTA_YELLOW);
    public static final CompatMapColor TERRACOTTA_LIME = of(MapColor.TERRACOTTA_LIME);
    public static final CompatMapColor TERRACOTTA_PINK = of(MapColor.TERRACOTTA_PINK);
    public static final CompatMapColor TERRACOTTA_GRAY = of(MapColor.TERRACOTTA_GRAY);
    public static final CompatMapColor TERRACOTTA_LIGHT_GRAY = of(MapColor.TERRACOTTA_LIGHT_GRAY);
    public static final CompatMapColor TERRACOTTA_CYAN = of(MapColor.TERRACOTTA_CYAN);
    public static final CompatMapColor TERRACOTTA_PURPLE = of(MapColor.TERRACOTTA_PURPLE);
    public static final CompatMapColor TERRACOTTA_BLUE = of(MapColor.TERRACOTTA_BLUE);
    public static final CompatMapColor TERRACOTTA_BROWN = of(MapColor.TERRACOTTA_BROWN);
    public static final CompatMapColor TERRACOTTA_GREEN = of(MapColor.TERRACOTTA_GREEN);
    public static final CompatMapColor TERRACOTTA_RED = of(MapColor.TERRACOTTA_RED);
    public static final CompatMapColor TERRACOTTA_BLACK = of(MapColor.TERRACOTTA_BLACK);
    public static final CompatMapColor DULL_RED = of(MapColor.DULL_RED);
    public static final CompatMapColor DULL_PINK = of(MapColor.DULL_PINK);
    public static final CompatMapColor DARK_CRIMSON = of(MapColor.DARK_CRIMSON);
    public static final CompatMapColor TEAL = of(MapColor.TEAL);
    public static final CompatMapColor DARK_AQUA = of(MapColor.DARK_AQUA);
    public static final CompatMapColor DARK_DULL_PINK = of(MapColor.DARK_DULL_PINK);
    public static final CompatMapColor BRIGHT_TEAL = of(MapColor.BRIGHT_TEAL);
    public static final CompatMapColor DEEPSLATE_GRAY = of(MapColor.DEEPSLATE_GRAY);
    public static final CompatMapColor RAW_IRON_PINK = of(MapColor.RAW_IRON_PINK);
    public static final CompatMapColor LICHEN_GREEN = of(MapColor.LICHEN_GREEN);

    public CompatMapColor(MapColor color) {
        this.color = color;
    }

    public static CompatMapColor of(MapColor color) {
        return new CompatMapColor(color);
    }

    public MapColor getColor() {
        return color;
    }

    public int getId() {
        return color.id;
    }

    public int getRgb() {
        return color.color;
    }

    public int getRenderColor(CompatBrightness brightness) {
        return color.getRenderColor(brightness.get());
    }

    @Override
    public int hashCode() {
        return color.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        CompatMapColor other = (CompatMapColor) obj;
        return color.equals(other.color);
    }
}
