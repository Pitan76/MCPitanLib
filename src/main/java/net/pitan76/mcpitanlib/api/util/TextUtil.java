package net.pitan76.mcpitanlib.api.util;

import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.Component;
import net.pitan76.mcpitanlib.api.text.TextConverter;

public class TextUtil {
    public static MutableComponent literal(String string) {
        return Component.literal(string);
    }

    public static MutableComponent translatable(String key) {
        return Component.translatable(key);
    }

    public static MutableComponent translatable(String key, Object... args) {
        return Component.translatable(key, args);
    }

    public static MutableComponent empty() {
        return literal("");
    }

    public static MutableComponent keybind(String string) {
        return Component.keybind(string);
    }

    public static String txt2str(Component text) {
        return text.getString();
    }

    public static MutableComponent setStyle(MutableComponent text, Style style) {
        return text.setStyle(style);
    }

    public static Style getStyle(MutableComponent text) {
        return text.getStyle();
    }

    public static MutableComponent withColor(MutableComponent text, int color) {
        return setStyle(text, StyleUtil.withColor(text.getStyle(), color));
    }

    public static MutableComponent withBold(MutableComponent text, boolean bold) {
        return setStyle(text, StyleUtil.withBold(text.getStyle(), bold));
    }

    public static MutableComponent append(MutableComponent text, Component sibling) {
        return text.append(sibling);
    }

    public static MutableComponent append(MutableComponent text, String string) {
        return text.append(string);
    }

    public static MutableComponent of(String string) {
        return literal(string);
    }

    public static MutableComponent convert(String text) {
        return TextConverter.convert(text, false);
    }

    public static MutableComponent convertWithTranslatable(String text) {
        return TextConverter.convert(text, true);
    }

    public static boolean contains(Component text, Component text1) {
        return text.contains(text1);
    }
}