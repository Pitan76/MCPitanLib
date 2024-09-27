package net.pitan76.mcpitanlib.api.util;

import net.minecraft.text.MutableText;
import net.minecraft.text.Style;
import net.minecraft.text.Text;

public class TextUtil {
    public static MutableText literal(String string) {
        return Text.literal(string);
    }

    public static MutableText translatable(String key) {
        return Text.translatable(key);
    }

    public static MutableText translatable(String key, Object... args) {
        return Text.translatable(key, args);
    }

    public static MutableText empty() {
        return literal("");
    }

    public static MutableText keybind(String string) {
        return Text.keybind(string);
    }

    public static String txt2str(Text text) {
        return text.getString();
    }

    public static MutableText setStyle(MutableText text, Style style) {
        return text.setStyle(style);
    }

    public static Style getStyle(MutableText text) {
        return text.getStyle();
    }

    public static MutableText withColor(MutableText text, int color) {
        return setStyle(text, StyleUtil.withColor(text.getStyle(), color));
    }

    public static MutableText withBold(MutableText text, boolean bold) {
        return setStyle(text, StyleUtil.withBold(text.getStyle(), bold));
    }

    public static MutableText append(MutableText text, Text sibling) {
        return text.append(sibling);
    }

    public static MutableText append(MutableText text, String string) {
        return text.append(string);
    }

    public static MutableText of(String string) {
        return literal(string);
    }
}