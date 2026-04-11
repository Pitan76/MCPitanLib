package net.pitan76.mcpitanlib.api.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.Renderable;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.client.gui.narration.NarratableEntry;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.input.KeyEvent;
import net.minecraft.client.renderer.item.ItemModelResolver;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.pitan76.mcpitanlib.api.client.gui.widget.CompatibleTexturedButtonWidget;
import net.pitan76.mcpitanlib.api.client.render.DrawObjectDM;
import net.pitan76.mcpitanlib.api.client.render.handledscreen.*;
import net.pitan76.mcpitanlib.api.client.render.screen.RenderBackgroundTextureArgs;
import net.pitan76.mcpitanlib.api.util.CompatIdentifier;
import net.pitan76.mcpitanlib.api.util.IdentifierUtil;
import net.pitan76.mcpitanlib.api.util.client.RenderUtil;
import net.pitan76.mcpitanlib.api.util.client.ScreenUtil;

public abstract class SimpleScreen extends Screen {

    public int width, height;
    public Font textRenderer;
    public ItemModelResolver itemRenderer;

    public Component title;
    public Minecraft client;

    public SimpleScreen(Component title) {
        super(title);
        fixScreen();
        this.title = title;
    }

    public <T extends GuiEventListener & Renderable & NarratableEntry> T addDrawableChild_compatibility(T drawableElement) {
        return super.addRenderableWidget(drawableElement);
        // addButton
    }

    public <T extends GuiEventListener & NarratableEntry> T addSelectableChild_compatibility(T selectableElement) {
        return super.addWidget(selectableElement);
    }

    public CompatibleTexturedButtonWidget addDrawableCTBW(CompatibleTexturedButtonWidget widget) {
        return addDrawableChild_compatibility(widget);
    }

    public void callDrawTexture(DrawObjectDM drawObjectDM, Identifier texture, int x, int y, int u, int v, int width, int height) {
        ScreenUtil.RendererUtil.drawTexture(drawObjectDM, texture, x, y, u, v, width, height);
    }

    public void callDrawTexture(DrawObjectDM drawObjectDM, CompatIdentifier texture, int x, int y, int u, int v, int width, int height) {
        callDrawTexture(drawObjectDM, texture.toMinecraft(), x, y, u, v, width, height);
    }

    @Deprecated
    @Override
    public void extractBackground(GuiGraphicsExtractor context, int mouseX, int mouseY, float delta) {
        renderBackground(new RenderArgs(new DrawObjectDM(context, this), mouseX, mouseY, delta));
    }

    public void renderBackground(RenderArgs args) {
        super.extractBackground(args.drawObjectDM.getContext(), args.mouseX, args.mouseY, args.delta);
    }

    public void render(RenderArgs args) {
        super.extractRenderState(args.drawObjectDM.getContext(), args.mouseX, args.mouseY, args.delta);
    }

    public void resizeOverride(Minecraft client, int width, int height) {
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
    public void resize(int width, int height) {
        super.resize(width, height);
        fixScreen();
        resizeOverride(Minecraft.getInstance(), width, height);
    }

    public void fixScreen() {
        this.textRenderer = super.font;
        this.itemRenderer = Minecraft.getInstance().getItemModelResolver();
        this.width = super.width;
        this.height = super.height;
        if (super.minecraft == null)
            this.client = Minecraft.getInstance();
        else
            this.client = super.minecraft;
    }

    public void setTextRenderer(Font textRenderer) {
        this.textRenderer = textRenderer;
    }

    public void setItemRenderer(ItemModelResolver itemRenderer) {
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
    public void extractRenderState(GuiGraphicsExtractor context, int mouseX, int mouseY, float delta) {
        DrawObjectDM drawObjectDM = new DrawObjectDM(context, this);
        render(new RenderArgs(drawObjectDM, mouseX, mouseY, delta));
    }

    public boolean keyReleased(KeyEventArgs args) {
        return super.keyReleased(new KeyEvent(args.keyCode, args.scanCode, args.modifiers));
    }

    public boolean keyPressed(KeyEventArgs args) {
        return super.keyPressed(new KeyEvent(args.keyCode, args.scanCode, args.modifiers));
    }

    public void renderBackgroundTexture(RenderBackgroundTextureArgs args) {
        if (getBackgroundTexture() != null)
            Screen.extractMenuBackgroundTexture(args.getDrawObjectDM().getContext(), getBackgroundTexture(), 0, 0, 0, 0, this.width, this.height);

        RenderUtil.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        callDrawTexture(args.drawObjectDM, getBackgroundTexture(), 0, 0, 0, 0, width, height);
    }

    @Deprecated
    @Override
    public boolean keyReleased(KeyEvent keyInput) {
        return this.keyReleased(new KeyEventArgs(keyInput.key(), keyInput.scancode(), keyInput.modifiers()));
    }

    @Deprecated
    @Override
    public boolean keyPressed(KeyEvent keyInput) {
        return this.keyPressed(new KeyEventArgs(keyInput.key(), keyInput.scancode(), keyInput.modifiers()));
    }

    @Deprecated
    @Override
    public void extractMenuBackground(GuiGraphicsExtractor context) {
        this.renderBackgroundTexture(new RenderBackgroundTextureArgs(new DrawObjectDM(context, this), 0));
    }

    public void closeOverride() {
        super.onClose();
    }

    public void removedOverride() {
        super.removed();
    }

    @Override
    public void onClose() {
        closeOverride();
    }

    @Override
    public void removed() {
        removedOverride();
    }

    public Identifier getBackgroundTexture() {
        return IdentifierUtil.from(getCompatBackgroundTexture());
    }

    public CompatIdentifier getCompatBackgroundTexture() {
        return null;
    }
}
