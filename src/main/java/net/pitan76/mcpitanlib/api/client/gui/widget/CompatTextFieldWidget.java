package net.pitan76.mcpitanlib.api.client.gui.widget;

import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.input.CharInput;
import net.minecraft.client.input.KeyInput;
import net.minecraft.text.Text;
import net.pitan76.mcpitanlib.api.util.TextUtil;
import org.jetbrains.annotations.Nullable;

public class CompatTextFieldWidget extends TextFieldWidget {
    public CompatTextFieldWidget(TextRenderer textRenderer, int width, int height) {
        this(textRenderer, width, height, TextUtil.empty());
    }

    public CompatTextFieldWidget(TextRenderer textRenderer, int x, int y, int width, int height) {
        this(textRenderer, x, y, width, height, TextUtil.empty());
    }

    // ----

    public CompatTextFieldWidget(TextRenderer textRenderer, int width, int height, Text text) {
        super(textRenderer, width, height, text);
    }

    public CompatTextFieldWidget(TextRenderer textRenderer, int x, int y, int width, int height, Text text) {
        super(textRenderer, x, y, width, height, text);
    }

    public CompatTextFieldWidget(TextRenderer textRenderer, int x, int y, int width, int height, @Nullable TextFieldWidget copyFrom, Text text) {
        super(textRenderer, x, y, width, height, copyFrom, text);
    }

    // ----

    @Deprecated
    @Override
    public void setDrawsBackground(boolean drawsBackground) {
        callSetDrawsBackground(drawsBackground);
    }

    public void callSetDrawsBackground(boolean drawsBackground) {
        super.setDrawsBackground(drawsBackground);
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
    public void setFocusUnlocked(boolean focusUnlocked) {
        callSetFocusUnlocked(focusUnlocked);
    }

    public void callSetFocusUnlocked(boolean focusUnlocked) {
        super.setFocusUnlocked(focusUnlocked);
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
    public void setText(String text) {
        callSetText(text);
    }

    public void callSetText(String text) {
        super.setText(text);
    }

    @Deprecated
    @Override
    public String getText() {
        return callGetText();
    }

    public String callGetText() {
        return super.getText();
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
    public boolean keyPressed(KeyInput input) {
        return callKeyPressed(input.key(), input.scancode(), input.modifiers());
    }

    public boolean callKeyPressed(int keyCode, int scanCode, int modifiers) {
        return super.keyPressed(new KeyInput(keyCode, scanCode, modifiers));
    }

    @Deprecated
    @Override
    public boolean keyReleased(KeyInput input) {
        return callKeyReleased(input.key(), input.scancode(), input.modifiers());
    }

    public boolean callKeyReleased(int keyCode, int scanCode, int modifiers) {
        return super.keyReleased(new KeyInput(keyCode, scanCode, modifiers));
    }

    @Deprecated
    @Override
    public boolean charTyped(CharInput input) {
        return callCharTyped((char) input.codepoint(), input.modifiers());
    }

    public boolean callCharTyped(char chr, int modifiers) {
        return super.charTyped(new CharInput(chr, modifiers));
    }
}
