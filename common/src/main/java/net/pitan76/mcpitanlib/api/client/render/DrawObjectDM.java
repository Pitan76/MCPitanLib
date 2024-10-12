package net.pitan76.mcpitanlib.api.client.render;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import net.pitan76.mcpitanlib.api.text.TextComponent;
import net.pitan76.mcpitanlib.api.util.CompatIdentifier;
import net.pitan76.mcpitanlib.api.util.client.ScreenUtil.RendererUtil;

public class DrawObjectDM {
    private MatrixStack stack;

    private Screen screen = null;

    public DrawObjectDM(MatrixStack stack) {
        this.stack = stack;
    }

    public DrawObjectDM(MatrixStack stack, Screen screen) {
        this(stack);
        this.screen = screen;
    }

    public MatrixStack getStack() {
        return stack;
    }

    public Screen getScreen() {
        return screen;
    }

    public void setStack(MatrixStack stack) {
        this.stack = stack;
    }

    public void setScreen(Screen screen) {
        this.screen = screen;
    }

    public boolean hasScreen() {
        return screen != null;
    }

    public void drawTexture(CompatIdentifier texture, int x, int y, float u, float v, int width, int height) {
        RendererUtil.drawTexture(this, texture, x, y, u, v, width, height);
    }

    public void drawTexture(CompatIdentifier texture, int x, int y, float u, float v, int width, int height, int textureWidth, int textureHeight) {
        RendererUtil.drawTexture(this, texture, x, y, u, v, width, height, textureWidth, textureHeight);
    }

    public void drawText(Text text, int x, int y) {
        RendererUtil.drawText(RendererUtil.getTextRenderer(), this, text, x, y);
    }

    public void drawTooltip(Text text, int x, int y) {
        RendererUtil.drawTooltip(this, text, x, y);
    }

    public void drawText(TextComponent text, int x, int y) {
        RendererUtil.drawText(RendererUtil.getTextRenderer(), this, text, x, y);
    }

    public void drawTooltip(TextComponent text, int x, int y) {
        RendererUtil.drawTooltip(this, text, x, y);
    }

    public void drawBorder(int x, int y, int width, int height, int color) {
        RendererUtil.drawBorder(this, x, y, width, height, color);
    }

    public int getWidth() {
        return hasScreen() ? screen.width : -1;
    }

    public int getHeight() {
        return hasScreen() ? screen.height : -1;
    }
}
