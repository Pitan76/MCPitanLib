package net.pitan76.mcpitanlib.api.text;

import net.minecraft.util.Formatting;
import net.pitan76.mcpitanlib.api.util.CompatStringIdentifiable;

public class CompatFormatting implements CompatStringIdentifiable {
    private final Formatting formatting;

    public static final CompatFormatting BLACK = of(Formatting.BLACK);
    public static final CompatFormatting DARK_BLUE = of(Formatting.DARK_BLUE);
    public static final CompatFormatting DARK_GREEN = of(Formatting.DARK_GREEN);
    public static final CompatFormatting DARK_AQUA = of(Formatting.DARK_AQUA);
    public static final CompatFormatting DARK_RED = of(Formatting.DARK_RED);
    public static final CompatFormatting DARK_PURPLE = of(Formatting.DARK_PURPLE);
    public static final CompatFormatting GOLD = of(Formatting.GOLD);
    public static final CompatFormatting GRAY = of(Formatting.GRAY);
    public static final CompatFormatting DARK_GRAY = of(Formatting.DARK_GRAY);
    public static final CompatFormatting BLUE = of(Formatting.BLUE);
    public static final CompatFormatting GREEN = of(Formatting.GREEN);
    public static final CompatFormatting AQUA = of(Formatting.AQUA);
    public static final CompatFormatting RED = of(Formatting.RED);
    public static final CompatFormatting LIGHT_PURPLE = of(Formatting.LIGHT_PURPLE);
    public static final CompatFormatting YELLOW = of(Formatting.YELLOW);
    public static final CompatFormatting WHITE = of(Formatting.WHITE);
    public static final CompatFormatting OBFUSCATED = of(Formatting.OBFUSCATED);
    public static final CompatFormatting BOLD = of(Formatting.BOLD);
    public static final CompatFormatting STRIKETHROUGH = of(Formatting.STRIKETHROUGH);
    public static final CompatFormatting UNDERLINE = of(Formatting.UNDERLINE);
    public static final CompatFormatting ITALIC = of(Formatting.ITALIC);
    public static final CompatFormatting RESET = of(Formatting.RESET);

    public CompatFormatting(Formatting formatting) {
        this.formatting = formatting;
    }

    public static CompatFormatting of(Formatting formatting) {
        return new CompatFormatting(formatting);
    }

    public Formatting getRaw() {
        return formatting;
    }

    @Override
    public String asString_compat() {
        return formatting.toString();
    }

    public boolean isColor() {
        return formatting.isColor();
    }

    public boolean isModifier() {
        return formatting.isModifier();
    }

    public char getCode() {
        return formatting.toString().charAt(1);
    }

    public Integer getColorValue() {
        return formatting.getColorValue();
    }

    public int getColorIndex() {
        return formatting.getColorIndex();
    }

    @Override
    public String toString() {
        return formatting.toString();
    }

    @Override
    public int hashCode() {
        return formatting.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        CompatFormatting other = (CompatFormatting) obj;
        return formatting.equals(other.formatting);
    }
}
