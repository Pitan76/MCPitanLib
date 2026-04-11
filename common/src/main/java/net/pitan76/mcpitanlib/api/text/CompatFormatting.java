package net.pitan76.mcpitanlib.api.text;

import net.minecraft.ChatFormatting;
import net.pitan76.mcpitanlib.api.util.CompatStringIdentifiable;

public class CompatFormatting implements CompatStringIdentifiable {
    private final ChatFormatting formatting;

    public static final CompatFormatting BLACK = of(ChatFormatting.BLACK);
    public static final CompatFormatting DARK_BLUE = of(ChatFormatting.DARK_BLUE);
    public static final CompatFormatting DARK_GREEN = of(ChatFormatting.DARK_GREEN);
    public static final CompatFormatting DARK_AQUA = of(ChatFormatting.DARK_AQUA);
    public static final CompatFormatting DARK_RED = of(ChatFormatting.DARK_RED);
    public static final CompatFormatting DARK_PURPLE = of(ChatFormatting.DARK_PURPLE);
    public static final CompatFormatting GOLD = of(ChatFormatting.GOLD);
    public static final CompatFormatting GRAY = of(ChatFormatting.GRAY);
    public static final CompatFormatting DARK_GRAY = of(ChatFormatting.DARK_GRAY);
    public static final CompatFormatting BLUE = of(ChatFormatting.BLUE);
    public static final CompatFormatting GREEN = of(ChatFormatting.GREEN);
    public static final CompatFormatting AQUA = of(ChatFormatting.AQUA);
    public static final CompatFormatting RED = of(ChatFormatting.RED);
    public static final CompatFormatting LIGHT_PURPLE = of(ChatFormatting.LIGHT_PURPLE);
    public static final CompatFormatting YELLOW = of(ChatFormatting.YELLOW);
    public static final CompatFormatting WHITE = of(ChatFormatting.WHITE);
    public static final CompatFormatting OBFUSCATED = of(ChatFormatting.OBFUSCATED);
    public static final CompatFormatting BOLD = of(ChatFormatting.BOLD);
    public static final CompatFormatting STRIKETHROUGH = of(ChatFormatting.STRIKETHROUGH);
    public static final CompatFormatting UNDERLINE = of(ChatFormatting.UNDERLINE);
    public static final CompatFormatting ITALIC = of(ChatFormatting.ITALIC);
    public static final CompatFormatting RESET = of(ChatFormatting.RESET);

    public CompatFormatting(ChatFormatting formatting) {
        this.formatting = formatting;
    }

    public static CompatFormatting of(ChatFormatting formatting) {
        return new CompatFormatting(formatting);
    }

    public ChatFormatting getRaw() {
        return formatting;
    }

    @Override
    public String asString_compat() {
        return formatting.getSerializedName();
    }

    public boolean isColor() {
        return formatting.isColor();
    }

    public boolean isModifier() {
        return formatting.isFormat();
    }

    public char getCode() {
        return formatting.getChar();
    }

    public Integer getColorValue() {
        return formatting.getColor();
    }

    public int getColorIndex() {
        return formatting.getId();
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
