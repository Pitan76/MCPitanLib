package net.pitan76.mcpitanlib.api.util.color;

import net.minecraft.world.level.material.MapColor;

public class CompatMapColor {
    private final MapColor color;

    public static final CompatMapColor CLEAR = of(MapColor.NONE);
    public static final CompatMapColor PALE_GREEN = of(MapColor.GRASS);
    public static final CompatMapColor PALE_YELLOW = of(MapColor.SAND);
    public static final CompatMapColor WHITE_GRAY = of(MapColor.WOOL);
    public static final CompatMapColor BRIGHT_RED = of(MapColor.FIRE);
    public static final CompatMapColor PALE_PURPLE = of(MapColor.ICE);
    public static final CompatMapColor IRON_GRAY = of(MapColor.METAL);
    public static final CompatMapColor DARK_GREEN = of(MapColor.PLANT);
    public static final CompatMapColor WHITE = of(MapColor.SNOW);
    public static final CompatMapColor LIGHT_BLUE_GRAY = of(MapColor.CLAY);
    public static final CompatMapColor DIRT_BROWN = of(MapColor.DIRT);
    public static final CompatMapColor STONE_GRAY = of(MapColor.STONE);
    public static final CompatMapColor WATER_BLUE = of(MapColor.WATER);
    public static final CompatMapColor OAK_TAN = of(MapColor.WOOD);
    public static final CompatMapColor OFF_WHITE = of(MapColor.QUARTZ);
    public static final CompatMapColor ORANGE = of(MapColor.COLOR_ORANGE);
    public static final CompatMapColor MAGENTA = of(MapColor.COLOR_MAGENTA);
    public static final CompatMapColor LIGHT_BLUE = of(MapColor.COLOR_LIGHT_BLUE);
    public static final CompatMapColor YELLOW = of(MapColor.COLOR_YELLOW);
    public static final CompatMapColor LIME = of(MapColor.COLOR_LIGHT_GREEN);
    public static final CompatMapColor PINK = of(MapColor.COLOR_PINK);
    public static final CompatMapColor GRAY = of(MapColor.COLOR_GRAY);
    public static final CompatMapColor LIGHT_GRAY = of(MapColor.COLOR_LIGHT_GRAY);
    public static final CompatMapColor CYAN = of(MapColor.COLOR_CYAN);
    public static final CompatMapColor PURPLE = of(MapColor.COLOR_PURPLE);
    public static final CompatMapColor BLUE = of(MapColor.COLOR_BLUE);
    public static final CompatMapColor BROWN = of(MapColor.COLOR_BROWN);
    public static final CompatMapColor GREEN = of(MapColor.COLOR_GREEN);
    public static final CompatMapColor RED = of(MapColor.COLOR_RED);
    public static final CompatMapColor BLACK = of(MapColor.COLOR_BLACK);
    public static final CompatMapColor GOLD = of(MapColor.GOLD);
    public static final CompatMapColor DIAMOND_BLUE = of(MapColor.DIAMOND);
    public static final CompatMapColor LAPIS_BLUE = of(MapColor.LAPIS);
    public static final CompatMapColor EMERALD_GREEN = of(MapColor.EMERALD);
    public static final CompatMapColor SPRUCE_BROWN = of(MapColor.PODZOL);
    public static final CompatMapColor DARK_RED = of(MapColor.NETHER);
    public static final CompatMapColor TERRACOTTA_WHITE = of(MapColor.TERRACOTTA_WHITE);
    public static final CompatMapColor TERRACOTTA_ORANGE = of(MapColor.TERRACOTTA_ORANGE);
    public static final CompatMapColor TERRACOTTA_MAGENTA = of(MapColor.TERRACOTTA_MAGENTA);
    public static final CompatMapColor TERRACOTTA_LIGHT_BLUE = of(MapColor.TERRACOTTA_LIGHT_BLUE);
    public static final CompatMapColor TERRACOTTA_YELLOW = of(MapColor.TERRACOTTA_YELLOW);
    public static final CompatMapColor TERRACOTTA_LIME = of(MapColor.TERRACOTTA_LIGHT_GREEN);
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
    public static final CompatMapColor DULL_RED = of(MapColor.CRIMSON_NYLIUM);
    public static final CompatMapColor DULL_PINK = of(MapColor.CRIMSON_STEM);
    public static final CompatMapColor DARK_CRIMSON = of(MapColor.CRIMSON_HYPHAE);
    public static final CompatMapColor TEAL = of(MapColor.WARPED_NYLIUM);
    public static final CompatMapColor DARK_AQUA = of(MapColor.WARPED_STEM);
    public static final CompatMapColor DARK_DULL_PINK = of(MapColor.WARPED_HYPHAE);
    public static final CompatMapColor BRIGHT_TEAL = of(MapColor.WARPED_WART_BLOCK);
    public static final CompatMapColor DEEPSLATE_GRAY = of(MapColor.DEEPSLATE);
    public static final CompatMapColor RAW_IRON_PINK = of(MapColor.RAW_IRON);
    public static final CompatMapColor LICHEN_GREEN = of(MapColor.GLOW_LICHEN);

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
        return color.col;
    }

    public int getRenderColor(CompatBrightness brightness) {
        return color.calculateARGBColor(brightness.get());
    }
}
