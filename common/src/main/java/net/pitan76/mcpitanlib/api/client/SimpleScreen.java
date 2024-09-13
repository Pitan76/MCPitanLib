package net.pitan76.mcpitanlib.api.client;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.Drawable;
import net.minecraft.client.gui.Element;
import net.minecraft.client.gui.Selectable;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.pitan76.mcpitanlib.api.client.gui.widget.CompatibleTexturedButtonWidget;
import net.pitan76.mcpitanlib.api.client.render.DrawObjectDM;
import net.pitan76.mcpitanlib.api.client.render.handledscreen.*;
import net.pitan76.mcpitanlib.api.client.render.screen.RenderBackgroundTextureArgs;
import net.pitan76.mcpitanlib.api.util.CompatIdentifier;

public abstract class SimpleScreen extends Screen {

    public int width, height;
    public TextRenderer textRenderer;
    public ItemRenderer itemRenderer;

    public Text title;
    public MinecraftClient client;

    public SimpleScreen(Text title) {
        super(title);
        fixScreen();
        this.title = title;
    }

    public <T extends Element & Drawable & Selectable> T addDrawableChild_compatibility(T drawableElement) {
        return super.addDrawableChild(drawableElement);
        // addButton
    }

    public <T extends Element & Selectable> T addSelectableChild_compatibility(T selectableElement) {
        return super.addSelectableChild(selectableElement);
    }

    public CompatibleTexturedButtonWidget addDrawableCTBW(CompatibleTexturedButtonWidget widget) {
        return addDrawableChild_compatibility(widget);
    }

    public void callDrawTexture(DrawObjectDM drawObjectDM, Identifier texture, int x, int y, int u, int v, int width, int height) {
        //ScreenUtil.setBackground(GUI);
        //super.drawTexture(matrices, x, y, u, v, width, height);
        drawObjectDM.getContext().drawTexture(texture, x, y, u, v, width, height);
    }

    public void callDrawTexture(DrawObjectDM drawObjectDM, CompatIdentifier texture, int x, int y, int u, int v, int width, int height) {
        callDrawTexture(drawObjectDM, texture.toMinecraft(), x, y, u, v, width, height);
    }

    @Deprecated
    @Override
    public void renderBackground(DrawContext context, int mouseX, int mouseY, float delta) {
        renderBackground(new RenderArgs(new DrawObjectDM(context), mouseX, mouseY, delta));
    }


    public void renderBackground(RenderArgs args) {
        super.renderBackground(args.drawObjectDM.getContext(), args.mouseX, args.mouseY, args.delta);
    }

    public void render(RenderArgs args) {
        super.render(args.drawObjectDM.getContext(), args.mouseX, args.mouseY, args.delta);
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
        if (super.client == null)
            this.client = MinecraftClient.getInstance();
        else
            this.client = super.client;
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
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        DrawObjectDM drawObjectDM = new DrawObjectDM(context);
        render(new RenderArgs(drawObjectDM, mouseX, mouseY, delta));
    }

    public boolean keyReleased(KeyEventArgs args) {
        return super.keyReleased(args.keyCode, args.scanCode, args.modifiers);
    }

    public boolean keyPressed(KeyEventArgs args) {
        return super.keyPressed(args.keyCode, args.scanCode, args.modifiers);
    }

    public void renderBackgroundTexture(RenderBackgroundTextureArgs args) {
        if (getBackgroundTexture() != null)
            Screen.renderBackgroundTexture(args.getDrawObjectDM().getContext(), getBackgroundTexture(), 0, 0, 0, 0, this.width, this.height);
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
    public void renderDarkening(DrawContext context) {
        this.renderBackgroundTexture(new RenderBackgroundTextureArgs(new DrawObjectDM(context), 0));
    }

    public void closeOverride() {
        super.close();
    }

    public void removedOverride() {
        super.removed();
    }

    @Override
    public void close() {
        closeOverride();
    }

    @Override
    public void removed() {
        removedOverride();
    }

    public Identifier getBackgroundTexture() {
        return getCompatBackgroundTexture().toMinecraft();
    }

    public CompatIdentifier getCompatBackgroundTexture() {
        return null;
    }
}
