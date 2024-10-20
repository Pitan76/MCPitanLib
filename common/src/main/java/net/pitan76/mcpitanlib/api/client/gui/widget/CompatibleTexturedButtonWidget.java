package net.pitan76.mcpitanlib.api.client.gui.widget;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ButtonTextures;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.TexturedButtonWidget;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.pitan76.mcpitanlib.api.util.CompatIdentifier;
import net.pitan76.mcpitanlib.api.util.TextUtil;

public class CompatibleTexturedButtonWidget extends TexturedButtonWidget {
    private final Identifier texture;
    private final int u;
    private final int v;
    private final int hoveredVOffset;
    private final int textureWidth;
    private final int textureHeight;

    public CompatibleTexturedButtonWidget(int x, int y, int width, int height, int u, int v, Identifier texture, ButtonWidget.PressAction pressAction) {
        this(x, y, width, height, u, v, height, texture, 256, 256, pressAction);
    }

    public CompatibleTexturedButtonWidget(int x, int y, int width, int height, int u, int v, int hoveredVOffset, Identifier texture, ButtonWidget.PressAction pressAction) {
        this(x, y, width, height, u, v, hoveredVOffset, texture, 256, 256, pressAction);
    }

    public CompatibleTexturedButtonWidget(int x, int y, int width, int height, int u, int v, int hoveredVOffset, Identifier texture, int textureWidth, int textureHeight, ButtonWidget.PressAction pressAction) {
        this(x, y, width, height, u, v, hoveredVOffset, texture, textureWidth, textureHeight, pressAction, TextUtil.empty());
    }

    public CompatibleTexturedButtonWidget(int x, int y, int width, int height, int u, int v, int hoveredVOffset, Identifier texture, int textureWidth, int textureHeight, ButtonWidget.PressAction pressAction, Text text) {
        super(x, y, width, height, new ButtonTextures(texture, texture), pressAction, text);
        this.textureWidth = textureWidth;
        this.textureHeight = textureHeight;
        this.u = u;
        this.v = v;
        this.hoveredVOffset = hoveredVOffset;
        this.texture = texture;
    }

    public CompatibleTexturedButtonWidget(int x, int y, int width, int height, int u, int v, CompatIdentifier texture, ButtonWidget.PressAction pressAction) {
        this(x, y, width, height, u, v, texture.toMinecraft(), pressAction);
    }

    public CompatibleTexturedButtonWidget(int x, int y, int width, int height, int u, int v, int hoveredVOffset, CompatIdentifier texture, ButtonWidget.PressAction pressAction) {
        this(x, y, width, height, u, v, hoveredVOffset, texture.toMinecraft(), pressAction);
    }

    public CompatibleTexturedButtonWidget(int x, int y, int width, int height, int u, int v, int hoveredVOffset, CompatIdentifier texture, int textureWidth, int textureHeight, ButtonWidget.PressAction pressAction) {
        this(x, y, width, height, u, v, hoveredVOffset, texture.toMinecraft(), textureWidth, textureHeight, pressAction);
    }

    public CompatibleTexturedButtonWidget(int x, int y, int width, int height, int u, int v, int hoveredVOffset, CompatIdentifier texture, int textureWidth, int textureHeight, ButtonWidget.PressAction pressAction, Text text) {
        this(x, y, width, height, u, v, hoveredVOffset, texture.toMinecraft(), textureWidth, textureHeight, pressAction, text);
    }

    public void setPos(int x, int y) {
        setX(x);
        setY(y);
    }

    @Override
    public void renderWidget(DrawContext context, int mouseX, int mouseY, float delta) {
        int i = v;
        if (!this.isNarratable()) {
            i = v + hoveredVOffset * 2;
        } else if (this.isHovered()) {
            i += hoveredVOffset;
        }
        context.drawTexture(texture, this.getX(), this.getY(), u, i, this.width, this.height, textureWidth, textureHeight);
    }
}
