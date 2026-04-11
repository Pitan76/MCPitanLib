package net.pitan76.mcpitanlib.api.client.gui.widget;

import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.input.CharacterEvent;
import net.minecraft.client.input.KeyEvent;
import net.minecraft.network.chat.Component;
import net.pitan76.mcpitanlib.api.util.TextUtil;
import org.jetbrains.annotations.Nullable;

public class CompatTextFieldWidget extends EditBox {
    public CompatTextFieldWidget(Font textRenderer, int width, int height) {
        this(textRenderer, width, height, TextUtil.empty());
    }

    public CompatTextFieldWidget(Font textRenderer, int x, int y, int width, int height) {
        this(textRenderer, x, y, width, height, TextUtil.empty());
    }

    // ----

    public CompatTextFieldWidget(Font textRenderer, int width, int height, Component text) {
        super(textRenderer, width, height, text);
    }

    public CompatTextFieldWidget(Font textRenderer, int x, int y, int width, int height, Component text) {
        super(textRenderer, x, y, width, height, text);
    }

    public CompatTextFieldWidget(Font textRenderer, int x, int y, int width, int height, @Nullable EditBox copyFrom, Component text) {
        super(textRenderer, x, y, width, height, copyFrom, text);
    }

    // ----

    @Deprecated
    @Override
    public void setBordered(boolean drawsBackground) {
        callSetDrawsBackground(drawsBackground);
    }

    public void callSetDrawsBackground(boolean drawsBackground) {
        super.setBordered(drawsBackground);
    }

    @Deprecated
    @Override
    public void setFocused(boolean focused) {
        callSetFocused(focused);
    }

    public void callSetFocused(boolean focused) {
        super.setFocused(focused);
    }

    @Deprecated
    @Override
    public void setCanLoseFocus(boolean focusUnlocked) {
        callSetFocusUnlocked(focusUnlocked);
    }

    public void callSetFocusUnlocked(boolean focusUnlocked) {
        super.setCanLoseFocus(focusUnlocked);
    }

    @Deprecated
    @Override
    public void setMaxLength(int maxLength) {
        callSetMaxLength(maxLength);
    }

    public void callSetMaxLength(int maxLength) {
        super.setMaxLength(maxLength);
    }

    @Deprecated
    @Override
    public void setValue(String text) {
        callSetText(text);
    }

    public void callSetText(String text) {
        super.setValue(text);
    }

    @Deprecated
    @Override
    public String getValue() {
        return callGetText();
    }

    public String callGetText() {
        return super.getValue();
    }

    @Deprecated
    @Override
    public void setEditable(boolean editable) {
        callSetEditable(editable);
    }

    public void callSetEditable(boolean editable) {
        super.setEditable(editable);
    }

    @Deprecated
    @Override
    public boolean isFocused() {
        return callIsFocused();
    }

    public boolean callIsFocused() {
        return super.isFocused();
    }

    @Deprecated
    @Override
    public boolean keyPressed(KeyEvent input) {
        return callKeyPressed(input.key(), input.scancode(), input.modifiers());
    }

    public boolean callKeyPressed(int keyCode, int scanCode, int modifiers) {
        return super.keyPressed(new KeyEvent(keyCode, scanCode, modifiers));
    }

    @Deprecated
    @Override
    public boolean keyReleased(KeyEvent input) {
        return callKeyReleased(input.key(), input.scancode(), input.modifiers());
    }

    public boolean callKeyReleased(int keyCode, int scanCode, int modifiers) {
        return super.keyReleased(new KeyEvent(keyCode, scanCode, modifiers));
    }

    @Deprecated
    @Override
    public boolean charTyped(CharacterEvent input) {
        return callCharTyped((char) input.codepoint(), -1);
    }

    public boolean callCharTyped(char chr, int modifiers) {
        return super.charTyped(new CharacterEvent(chr));
    }
}
