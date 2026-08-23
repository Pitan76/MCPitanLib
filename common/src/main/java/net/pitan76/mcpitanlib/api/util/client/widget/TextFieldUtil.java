package net.pitan76.mcpitanlib.api.util.client.widget;

import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.text.Text;
import net.pitan76.mcpitanlib.api.client.gui.widget.CompatTextFieldWidget;
import net.pitan76.mcpitanlib.api.client.render.handledscreen.RenderArgs;
import net.pitan76.mcpitanlib.api.util.TextUtil;

public class TextFieldUtil {
    public static void setFocused(TextFieldWidget widget, boolean focused) {
        widget.setFocused(focused);
    }

    public static void render(TextFieldWidget widget, RenderArgs args) {
        widget.render(args.drawObjectDM.getContext(), args.mouseX, args.mouseY, args.delta);
    }

    public static void setEditable(TextFieldWidget widget, boolean editable) {
        widget.setEditable(editable);
    }

    public static void setMaxLength(TextFieldWidget widget, int maxLength) {
        widget.setMaxLength(maxLength);
    }

    public static void setSuggestion(TextFieldWidget widget, String suggestion) {
        widget.setSuggestion(suggestion);
    }

    public static void setText(TextFieldWidget widget, String text) {
        widget.setText(text);
    }

    public static String getText(TextFieldWidget widget) {
        return widget.getText();
    }

    public static void setDrawsBackground(TextFieldWidget widget, boolean drawsBackground) {
        widget.setDrawsBackground(drawsBackground);
    }

    public static void setFocusUnlocked(TextFieldWidget widget, boolean focusUnlocked) {
        widget.setFocusUnlocked(focusUnlocked);
    }

    public static boolean isFocused(TextFieldWidget widget) {
        return widget.isFocused();
    }

    public static boolean keyPressed(TextFieldWidget widget, int keyCode, int scanCode, int modifiers) {
        return widget.keyPressed(keyCode, scanCode, modifiers);
    }

    public static boolean keyReleased(TextFieldWidget widget, int keyCode, int scanCode, int modifiers) {
        return widget.keyReleased(keyCode, scanCode, modifiers);
    }

    public static boolean charTyped(TextFieldWidget widget, char chr, int modifiers) {
        return widget.charTyped(chr, modifiers);
    }

    public static TextFieldWidget create(TextRenderer renderer, int x, int y, int width, int height, Text text) {
        return new TextFieldWidget(renderer, x, y, width, height, text);
    }

    public static TextFieldWidget create(TextRenderer renderer, int width, int height, Text text) {
        return new TextFieldWidget(renderer, width, height, text);
    }

    public static TextFieldWidget create(TextRenderer renderer, int x, int y, int width, int height) {
        return new TextFieldWidget(renderer, x, y, width, height, TextUtil.empty());
    }

    public static TextFieldWidget create(TextRenderer renderer, int width, int height) {
        return new TextFieldWidget(renderer, width, height, TextUtil.empty());
    }

    public static CompatTextFieldWidget createCompat(TextRenderer renderer, int x, int y, int width, int height, Text text) {
        return new CompatTextFieldWidget(renderer, x, y, width, height, text);
    }

    public static CompatTextFieldWidget createCompat(TextRenderer renderer, int width, int height, Text text) {
        return new CompatTextFieldWidget(renderer, width, height, text);
    }

    public static CompatTextFieldWidget createCompat(TextRenderer renderer, int x, int y, int width, int height) {
        return new CompatTextFieldWidget(renderer, x, y, width, height);
    }

    public static CompatTextFieldWidget createCompat(TextRenderer renderer, int width, int height) {
        return new CompatTextFieldWidget(renderer, width, height);
    }
}
