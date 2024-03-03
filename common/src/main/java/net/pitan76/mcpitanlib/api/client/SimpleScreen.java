package net.pitan76.mcpitanlib.api.client;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.Drawable;
import net.minecraft.client.gui.Element;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ClickableWidget;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.pitan76.mcpitanlib.api.client.gui.widget.CompatibleTexturedButtonWidget;
import net.pitan76.mcpitanlib.api.client.render.DrawObjectDM;
import net.pitan76.mcpitanlib.api.client.render.handledscreen.*;
import net.pitan76.mcpitanlib.api.util.client.ScreenUtil;
import net.pitan76.mcpitanlib.api.client.render.screen.RenderBackgroundTextureArgs;

public abstract class SimpleScreen extends Screen {

    public int width, height;
    public TextRenderer textRenderer;
    public ItemRenderer itemRenderer;

    public SimpleScreen(Text title) {
        super(title);
        fixScreen();
    }

    public <T extends Element & Drawable> T addDrawableChild_compatibility(T drawableElement) {
        if (drawableElement instanceof ClickableWidget)
            return (T) super.addButton((ClickableWidget) drawableElement);
        else
            return super.addChild(drawableElement);
    }

    public CompatibleTexturedButtonWidget addDrawableCTBW(CompatibleTexturedButtonWidget widget) {
        return addDrawableChild_compatibility(widget);
    }

    public void callDrawTexture(DrawObjectDM drawObjectDM, Identifier texture, int x, int y, int u, int v, int width, int height) {
        ScreenUtil.setBackground(texture);
        drawTexture(drawObjectDM.getStack(), x, y, u, v, width, height);
    }

    @Deprecated
    @Override
    public void renderBackground(MatrixStack matrices) {
        renderBackground(new RenderArgs(new DrawObjectDM(matrices), 0, 0, 0));
    }

    public void renderBackground(RenderArgs args) {
        super.renderBackground(args.drawObjectDM.getStack());
    }

    public void render(RenderArgs args) {
        super.render(args.drawObjectDM.getStack(), args.mouseX, args.mouseY, args.delta);
    }

    public void resizeOverride(MinecraftClient client, int width, int height) {
    }

    public void initOverride() {
    }

    @Deprecated
    @Override
    protected void init() {
        super.init();
        fixScreen();
        initOverride();
    }

    @Deprecated
    @Override
    public void resize(MinecraftClient client, int width, int height) {
        super.resize(client, width, height);
        fixScreen();
        resizeOverride(client, width, height);
    }

    public void fixScreen() {
        this.textRenderer = super.textRenderer;
        this.itemRenderer = MinecraftClient.getInstance().getItemRenderer();
        this.width = super.width;
        this.height = super.height;
    }

    public void setTextRenderer(TextRenderer textRenderer) {
        this.textRenderer = textRenderer;
        super.textRenderer = textRenderer;
    }

    public void setItemRenderer(ItemRenderer itemRenderer) {
        this.itemRenderer = itemRenderer;
    }

    public void setWidth(int width) {
        this.width = width;
        super.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
        super.height = height;
    }

    @Deprecated
    @Override
    public void render(MatrixStack stack, int mouseX, int mouseY, float delta) {
        DrawObjectDM drawObjectDM = new DrawObjectDM(stack);
        render(new RenderArgs(drawObjectDM, mouseX, mouseY, delta));
    }

    public boolean keyReleased(KeyEventArgs args) {
        return super.keyReleased(args.keyCode, args.scanCode, args.modifiers);
    }

    public boolean keyPressed(KeyEventArgs args) {
        return super.keyPressed(args.keyCode, args.scanCode, args.modifiers);
    }

    public void renderBackgroundTexture(RenderBackgroundTextureArgs args) {
        super.renderBackgroundTexture(args.getvOffset());
    }

    @Deprecated
    @Override
    public boolean keyReleased(int keyCode, int scanCode, int modifiers) {
        return this.keyReleased(new KeyEventArgs(keyCode, scanCode, modifiers));
    }

    @Deprecated
    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        return this.keyPressed(new KeyEventArgs(keyCode, scanCode, modifiers));
    }

    @Deprecated
    @Override
    public void renderBackgroundTexture(int vOffset) {
        this.renderBackgroundTexture(new RenderBackgroundTextureArgs(null, vOffset));
    }
}
