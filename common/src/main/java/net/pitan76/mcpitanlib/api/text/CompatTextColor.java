package net.pitan76.mcpitanlib.api.text;

import net.minecraft.network.chat.TextColor;
import net.minecraft.ChatFormatting;

public class CompatTextColor {
    private final TextColor textColor;

    public static final CompatTextColor BLACK = of(TextColor.fromLegacyFormat(ChatFormatting.BLACK));
    public static final CompatTextColor DARK_BLUE = of(TextColor.fromLegacyFormat(ChatFormatting.DARK_BLUE));
    public static final CompatTextColor DARK_GREEN = of(TextColor.fromLegacyFormat(ChatFormatting.DARK_GREEN));
    public static final CompatTextColor DARK_AQUA = of(TextColor.fromLegacyFormat(ChatFormatting.DARK_AQUA));
    public static final CompatTextColor DARK_RED = of(TextColor.fromLegacyFormat(ChatFormatting.DARK_RED));
    public static final CompatTextColor DARK_PURPLE = of(TextColor.fromLegacyFormat(ChatFormatting.DARK_PURPLE));
    public static final CompatTextColor GOLD = of(TextColor.fromLegacyFormat(ChatFormatting.GOLD));
    public static final CompatTextColor GRAY = of(TextColor.fromLegacyFormat(ChatFormatting.GRAY));
    public static final CompatTextColor DARK_GRAY = of(TextColor.fromLegacyFormat(ChatFormatting.DARK_GRAY));
    public static final CompatTextColor BLUE = of(TextColor.fromLegacyFormat(ChatFormatting.BLUE));
    public static final CompatTextColor GREEN = of(TextColor.fromLegacyFormat(ChatFormatting.GREEN));
    public static final CompatTextColor AQUA = of(TextColor.fromLegacyFormat(ChatFormatting.AQUA));
    public static final CompatTextColor RED = of(TextColor.fromLegacyFormat(ChatFormatting.RED));
    public static final CompatTextColor LIGHT_PURPLE = of(TextColor.fromLegacyFormat(ChatFormatting.LIGHT_PURPLE));
    public static final CompatTextColor YELLOW = of(TextColor.fromLegacyFormat(ChatFormatting.YELLOW));
    public static final CompatTextColor WHITE = of(TextColor.fromLegacyFormat(ChatFormatting.WHITE));

    public CompatTextColor(TextColor textColor) {
        this.textColor = textColor;
    }

    public TextColor getRaw() {
        return textColor;
    }

    public static CompatTextColor of(TextColor textColor) {
        return new CompatTextColor(textColor);
    }

    public static CompatTextColor fromFormatting(ChatFormatting formatting) {
        return new CompatTextColor(TextColor.fromLegacyFormat(formatting));
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
        return new CompatTextColor(TextColor.parseColor(string).getOrThrow());
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
