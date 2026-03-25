package net.pitan76.mcpitanlib.api.util;

import net.minecraft.network.chat.ClickEvent;
import net.minecraft.network.chat.HoverEvent;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.FontDescription;
import net.minecraft.ChatFormatting;

public class StyleUtil {
    public static Style emptyStyle() {
        return Style.EMPTY;
    }

    public static Style withColor(Style style, int color) {
        return style.withColor(color);
    }

    public static Style withBold(Style style, boolean bold) {
        return style.withBold(bold);
    }

    public static Style withItalic(Style style, boolean italic) {
        return style.withItalic(italic);
    }

    public static Style withUnderline(Style style, boolean underline) {
        return style.withUnderlined(underline);
    }

    public static Style withStrikethrough(Style style, boolean strikethrough) {
        return style.withStrikethrough(strikethrough);
    }

    public static Style withObfuscated(Style style, boolean obfuscated) {
        return style.withObfuscated(obfuscated);
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
        return style.withFont(new FontDescription.Resource(font.toMinecraft()));
    }

    public static Style withFormatting(Style style, ChatFormatting formatting) {
        return style.applyFormat(formatting);
    }

    public static Style withExclusiveFormatting(Style style, ChatFormatting formatting) {
        return style.applyLegacyFormat(formatting);
    }
}
