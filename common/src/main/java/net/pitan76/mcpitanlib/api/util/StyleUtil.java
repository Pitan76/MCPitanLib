package net.pitan76.mcpitanlib.api.util;

import net.minecraft.text.ClickEvent;
import net.minecraft.text.HoverEvent;
import net.minecraft.text.Style;
import net.minecraft.text.TextColor;
import net.minecraft.util.Formatting;

public class StyleUtil {
    public static Style emptyStyle() {
        return Style.EMPTY;
    }

    public static Style withColor(Style style, int color) {
        return style.withColor(TextColor.fromRgb(color));
    }

    public static Style withBold(Style style, boolean bold) {
        return style.withBold(bold);
    }

    public static Style withItalic(Style style, boolean italic) {
        return style.withItalic(italic);
    }

    public static Style withUnderline(Style style, boolean underline) {
        return style.withUnderline(underline);
    }

    public static Style withStrikethrough(Style style, boolean strikethrough) {
        if (strikethrough) return style.withFormatting(Formatting.STRIKETHROUGH);
        return style;
    }

    public static Style withObfuscated(Style style, boolean obfuscated) {
        if (obfuscated) return style.withFormatting(Formatting.OBFUSCATED);
        return style;
    }

    public static Style withInsertion(Style style, String insertion) {
        return style.withInsertion(insertion);
    }

    public static Style withClickEvent(Style style, ClickEvent clickEvent) {
        return style.withClickEvent(clickEvent);
    }

    public static Style withHoverEvent(Style style, HoverEvent hoverEvent) {
        return style.withHoverEvent(hoverEvent);
    }

    public static Style withFont(Style style, CompatIdentifier font) {
        return style.withFont(font.toMinecraft());
    }

    public static Style withFormatting(Style style, Formatting formatting) {
        return style.withFormatting(formatting);
    }

    public static Style withExclusiveFormatting(Style style, Formatting formatting) {
        return style.withExclusiveFormatting(formatting);
    }
}
