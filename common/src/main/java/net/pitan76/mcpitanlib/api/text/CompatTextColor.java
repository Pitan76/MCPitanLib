package net.pitan76.mcpitanlib.api.text;

import net.minecraft.text.TextColor;
import net.minecraft.util.Formatting;

public class CompatTextColor {
    private final TextColor textColor;

    public static final CompatTextColor BLACK = of(TextColor.fromFormatting(Formatting.BLACK));
    public static final CompatTextColor DARK_BLUE = of(TextColor.fromFormatting(Formatting.DARK_BLUE));
    public static final CompatTextColor DARK_GREEN = of(TextColor.fromFormatting(Formatting.DARK_GREEN));
    public static final CompatTextColor DARK_AQUA = of(TextColor.fromFormatting(Formatting.DARK_AQUA));
    public static final CompatTextColor DARK_RED = of(TextColor.fromFormatting(Formatting.DARK_RED));
    public static final CompatTextColor DARK_PURPLE = of(TextColor.fromFormatting(Formatting.DARK_PURPLE));
    public static final CompatTextColor GOLD = of(TextColor.fromFormatting(Formatting.GOLD));
    public static final CompatTextColor GRAY = of(TextColor.fromFormatting(Formatting.GRAY));
    public static final CompatTextColor DARK_GRAY = of(TextColor.fromFormatting(Formatting.DARK_GRAY));
    public static final CompatTextColor BLUE = of(TextColor.fromFormatting(Formatting.BLUE));
    public static final CompatTextColor GREEN = of(TextColor.fromFormatting(Formatting.GREEN));
    public static final CompatTextColor AQUA = of(TextColor.fromFormatting(Formatting.AQUA));
    public static final CompatTextColor RED = of(TextColor.fromFormatting(Formatting.RED));
    public static final CompatTextColor LIGHT_PURPLE = of(TextColor.fromFormatting(Formatting.LIGHT_PURPLE));
    public static final CompatTextColor YELLOW = of(TextColor.fromFormatting(Formatting.YELLOW));
    public static final CompatTextColor WHITE = of(TextColor.fromFormatting(Formatting.WHITE));

    public CompatTextColor(TextColor textColor) {
        this.textColor = textColor;
    }

    public TextColor getRaw() {
        return textColor;
    }

    public static CompatTextColor of(TextColor textColor) {
        return new CompatTextColor(textColor);
    }

    public static CompatTextColor fromFormatting(Formatting formatting) {
        return new CompatTextColor(TextColor.fromFormatting(formatting));
    }

    public static CompatTextColor fromFormatting(CompatFormatting formatting) {
        return fromFormatting(formatting.getRaw());
    }

    public static CompatTextColor fromRgb(int rgb) {
        return new CompatTextColor(TextColor.fromRgb(rgb));
    }

    public static CompatTextColor fromRgb(int r, int g, int b) {
        int rgb = (r << 16) | (g << 8) | b;
        return fromRgb(rgb);
    }

    public static CompatTextColor parse(String string) {
        try {
            return parseOrThrow(string);
        } catch (Exception e) {
            return null;
        }
    }

    public static CompatTextColor parseOrThrow(String string) {
        return new CompatTextColor(TextColor.parse(string).get().orThrow());
    }

    @Override
    public int hashCode() {
        return textColor.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        CompatTextColor other = (CompatTextColor) obj;
        return textColor.equals(other.textColor);
    }
}
