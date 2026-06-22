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
        return formatting.toString();
    }

    public boolean isColor() {
        switch (formatting) {
            case BLACK, DARK_BLUE, DARK_GREEN, DARK_AQUA, DARK_RED, DARK_PURPLE, GOLD, GRAY, DARK_GRAY, BLUE, GREEN, AQUA, RED, LIGHT_PURPLE, YELLOW, WHITE:
                return true;
            default:
                return false;
        }
    }

    public boolean isModifier() {
        switch (formatting) {
            case OBFUSCATED, BOLD, STRIKETHROUGH, UNDERLINE, ITALIC:
                return true;
            default:
                return false;
        }
    }

    public char getCode() {
        return formatting.toString().charAt(1);
    }

    public Integer getColorValue() {
        switch (formatting) {
            case BLACK: return 0x000000;
            case DARK_BLUE: return 0x0000AA;
            case DARK_GREEN: return 0x00AA00;
            case DARK_AQUA: return 0x00AAAA;
            case DARK_RED: return 0xAA0000;
            case DARK_PURPLE: return 0xAA00AA;
            case GOLD: return 0xFFAA00;
            case GRAY: return 0xAAAAAA;
            case DARK_GRAY: return 0x555555;
            case BLUE: return 0x5555FF;
            case GREEN: return 0x55FF55;
            case AQUA: return 0x55FFFF;
            case RED: return 0xFF5555;
            case LIGHT_PURPLE: return 0xFF55FF;
            case YELLOW: return 0xFFFF55;
            case WHITE: return 0xFFFFFF;
            default: return null; // Not a color
        }
    }

    public int getColorIndex() {
        return getCode() - '0';
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
