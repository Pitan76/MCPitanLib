package net.pitan76.mcpitanlib.api.client.render;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.network.chat.Component;
import net.pitan76.mcpitanlib.api.text.TextComponent;
import net.pitan76.mcpitanlib.api.util.CompatIdentifier;
import net.pitan76.mcpitanlib.api.util.client.ScreenUtil.RendererUtil;
import org.joml.Matrix3x2f;
import org.joml.Matrix3x2fStack;

public class DrawObjectDM {
    private PoseStack stack;
    private Matrix3x2fStack matrix3x2fStack;
    private GuiGraphics context;

    private Screen screen = null;

    public DrawObjectDM(GuiGraphics context) {
        this.context = context;
        this.matrix3x2fStack = context.pose();
    }

    public DrawObjectDM(Matrix3x2fStack matrix3x2fStack) {
        this.matrix3x2fStack = matrix3x2fStack;
    }

    public DrawObjectDM(PoseStack stack) {
        this.stack = stack;
    }

    public DrawObjectDM(GuiGraphics context, Screen screen) {
        this(context);
        this.screen = screen;
    }

    public GuiGraphics getContext() {
        return context;
    }

    public PoseStack getStack() {
        return stack;
    }

    public Screen getScreen() {
        return screen;
    }

    public void setContext(GuiGraphics context) {
        this.context = context;
    }

    public void setStack(PoseStack stack) {
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

    public void drawText(Component text, int x, int y) {
        RendererUtil.drawText(RendererUtil.getTextRenderer(), this, text, x, y);
    }

    public void drawTooltip(Component text, int x, int y) {
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
