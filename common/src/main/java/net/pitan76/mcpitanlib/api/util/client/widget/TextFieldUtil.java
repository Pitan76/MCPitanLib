package net.pitan76.mcpitanlib.api.util.client.widget;

import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.input.CharacterEvent;
import net.minecraft.client.input.KeyEvent;
import net.minecraft.network.chat.Component;
import net.pitan76.mcpitanlib.api.client.gui.widget.CompatTextFieldWidget;
import net.pitan76.mcpitanlib.api.client.render.handledscreen.RenderArgs;
import net.pitan76.mcpitanlib.api.util.TextUtil;

public class TextFieldUtil {
    public static void setFocused(EditBox widget, boolean focused) {
        widget.setFocused(focused);
    }

    public static void render(EditBox widget, RenderArgs args) {
        widget.extractRenderState(args.drawObjectDM.getContext(), args.mouseX, args.mouseY, args.delta);
    }

    public static void setEditable(EditBox widget, boolean editable) {
        widget.setEditable(editable);
    }

    public static void setMaxLength(EditBox widget, int maxLength) {
        widget.setMaxLength(maxLength);
    }

    public static void setSuggestion(EditBox widget, String suggestion) {
        widget.setSuggestion(suggestion);
    }

    public static void setText(EditBox widget, String text) {
        widget.setValue(text);
    }

    public static String getText(EditBox widget) {
        return widget.getValue();
    }

    public static void setDrawsBackground(EditBox widget, boolean drawsBackground) {
        widget.setBordered(drawsBackground);
    }

    public static void setFocusUnlocked(EditBox widget, boolean focusUnlocked) {
        widget.setCanLoseFocus(focusUnlocked);
    }

    public static boolean isFocused(EditBox widget) {
        return widget.isFocused();
    }

    public static boolean keyPressed(EditBox widget, int keyCode, int scanCode, int modifiers) {
        return widget.keyPressed(new KeyEvent(keyCode, scanCode, modifiers));
    }

    public static boolean keyReleased(EditBox widget, int keyCode, int scanCode, int modifiers) {
        return widget.keyReleased(new KeyEvent(keyCode, scanCode, modifiers));
    }

    public static boolean charTyped(EditBox widget, char chr, int modifiers) {
        return widget.charTyped(new CharacterEvent(chr));
    }

    public static EditBox create(Font renderer, int x, int y, int width, int height, Component text) {
        return new EditBox(renderer, x, y, width, height, text);
    }

    public static EditBox create(Font renderer, int width, int height, Component text) {
        return new EditBox(renderer, width, height, text);
    }

    public static EditBox create(Font renderer, int x, int y, int width, int height) {
        return new EditBox(renderer, x, y, width, height, TextUtil.empty());
    }

    public static EditBox create(Font renderer, int width, int height) {
        return new EditBox(renderer, width, height, TextUtil.empty());
    }

    public static CompatTextFieldWidget createCompat(Font renderer, int x, int y, int width, int height, Component text) {
        return new CompatTextFieldWidget(renderer, x, y, width, height, text);
    }

    public static CompatTextFieldWidget createCompat(Font renderer, int width, int height, Component text) {
        return new CompatTextFieldWidget(renderer, width, height, text);
    }

    public static CompatTextFieldWidget createCompat(Font renderer, int x, int y, int width, int height) {
        return new CompatTextFieldWidget(renderer, x, y, width, height);
    }

    public static CompatTextFieldWidget createCompat(Font renderer, int width, int height) {
        return new CompatTextFieldWidget(renderer, width, height);
    }
}
