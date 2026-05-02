package net.pitan76.mcpitanlib.api.client.gui.screen;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.Renderable;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.client.gui.narration.NarratableEntry;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.input.CharacterEvent;
import net.minecraft.client.input.KeyEvent;
import net.minecraft.client.input.MouseButtonEvent;
import net.minecraft.client.renderer.item.ItemModelResolver;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.pitan76.mcpitanlib.api.client.gui.widget.CompatibleTexturedButtonWidget;
import net.pitan76.mcpitanlib.api.client.render.DrawObjectDM;
import net.pitan76.mcpitanlib.api.client.render.handledscreen.*;
import net.pitan76.mcpitanlib.api.client.render.screen.RenderBackgroundTextureArgs;
import net.pitan76.mcpitanlib.api.text.TextComponent;
import net.pitan76.mcpitanlib.api.util.CompatIdentifier;
import net.pitan76.mcpitanlib.api.util.IdentifierUtil;
import net.pitan76.mcpitanlib.api.util.client.ClientUtil;
import net.pitan76.mcpitanlib.api.util.client.RenderUtil;
import net.pitan76.mcpitanlib.api.util.client.ScreenUtil;
import net.pitan76.mcpitanlib.core.datafixer.Pair;

public abstract class SimpleHandledScreen<S extends AbstractContainerMenu> extends AbstractContainerScreen<S> {

    public int width, height, backgroundWidth, backgroundHeight, x, y;
    public S handler;
    public Font textRenderer;
    public ItemModelResolver itemRenderer;

    public Component title;
    public Minecraft client;
    public SimpleHandledScreen(S handler, Inventory inventory, Component title) {
        super(handler, inventory, title);
        fixScreen();
        this.handler = handler;
        this.title = title;
    }

    @Deprecated
    @Override
    public S getMenu() {
        return getScreenHandlerOverride();
    }

    public S getScreenHandlerOverride() {
        return super.getMenu();
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

    @Deprecated
    @Override
    public void extractBackground(GuiGraphicsExtractor context, int mouseX, int mouseY, float delta) {
        super.extractBackground(context, mouseX, mouseY, delta);
        DrawObjectDM drawObjectDM = new DrawObjectDM(context, this);
        drawBackgroundOverride(new DrawBackgroundArgs(drawObjectDM, delta, mouseX, mouseY));
    }

    public abstract void drawBackgroundOverride(DrawBackgroundArgs args);

    @Deprecated
    @Override
    protected void extractLabels(GuiGraphicsExtractor context, int mouseX, int mouseY) {
        DrawObjectDM drawObjectDM = new DrawObjectDM(context, this);
        drawForegroundOverride(new DrawForegroundArgs(drawObjectDM, mouseX, mouseY));
    }

    protected void drawForegroundOverride(DrawForegroundArgs args) {
        super.extractLabels(args.drawObjectDM.getContext(), args.mouseX, args.mouseY);
    }

    public void callDrawTexture(DrawObjectDM drawObjectDM, Identifier texture, int x, int y, int u, int v, int width, int height) {
        ScreenUtil.RendererUtil.drawTexture(drawObjectDM, texture, x, y, u, v, width, height);
    }

    public void callDrawTexture(DrawObjectDM drawObjectDM, CompatIdentifier texture, int x, int y, int u, int v, int width, int height) {
        callDrawTexture(drawObjectDM, texture.toMinecraft(), x, y, u, v, width, height);
    }

    @Deprecated
    public void callRenderBackground(DrawObjectDM drawObjectDM) {
        callRenderBackground(new RenderArgs(drawObjectDM, 0, 0, 0));
    }


    public void callRenderBackground(RenderArgs args) {
        // TODO: 以前のバージョンではどう機能しているかチェックする必要がある。このバージョンで利用すると全体が暗くなる
        //        super.extractBackground(args.drawObjectDM.getContext(), args.mouseX, args.mouseY, args.delta);
    }

    public void callDrawMouseoverTooltip(DrawMouseoverTooltipArgs args) {
        super.extractTooltip(args.drawObjectDM.getContext(), args.mouseX, args.mouseY);
    }

    public void renderOverride(RenderArgs args) {
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
        this.backgroundWidth = getBackgroundWidth();
        this.backgroundHeight = getBackgroundHeight();
        this.x = super.leftPos; //(this.width - this.backgroundWidth) / 2;
        this.y = super.topPos; //(this.height - this.backgroundHeight) / 2;
        this.textRenderer = super.font;
        this.itemRenderer = Minecraft.getInstance().getItemModelResolver();
        this.width = super.width;
        this.height = super.height;
        if (super.minecraft == null)
            this.client = Minecraft.getInstance();
        else
            this.client = super.minecraft;
    }

    public void setX(int x) {
        this.x = x;
        super.leftPos = x;
    }

    public void setY(int y) {
        this.y = y;
        super.topPos = y;
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

    public void setBackgroundWidth(int backgroundWidth) {
        this.backgroundWidth = backgroundWidth;
        super.imageWidth = backgroundWidth;
    }

    public void setBackgroundHeight(int backgroundHeight) {
        this.backgroundHeight = backgroundHeight;
        super.imageHeight = backgroundHeight;
    }

    public void setHeight(int height) {
        this.height = height;
        super.height = height;
    }

    public int getBackgroundWidth() {
        return super.imageWidth;
    }

    public int getBackgroundHeight() {
        return super.imageHeight;
    }

    @Deprecated
    @Override
    public void extractRenderState(GuiGraphicsExtractor context, int mouseX, int mouseY, float delta) {
        DrawObjectDM drawObjectDM = new DrawObjectDM(context, this);
        renderOverride(new RenderArgs(drawObjectDM, mouseX, mouseY, delta));
    }

    public boolean keyReleased(KeyEventArgs args) {
        return super.keyReleased(new KeyEvent(args.keyCode, args.scanCode, args.modifiers));
    }

    public boolean keyPressed(KeyEventArgs args) {
        return super.keyPressed(new KeyEvent(args.keyCode, args.scanCode, args.modifiers));
    }

    public void renderBackgroundTexture(RenderBackgroundTextureArgs args) {
        if (getBackgroundTexture() != null)
            Screen.extractMenuBackgroundTexture(args.getDrawObjectDM().getContext(), getBackgroundTexture(), x, y, 0, 0, this.width, this.height);

        RenderUtil.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        callDrawTexture(args.drawObjectDM, getBackgroundTexture(), 0, 0, 0, 0, width, height);
    }

    @Deprecated
    @Override
    public boolean keyReleased(KeyEvent input) {
        return this.keyReleased(new KeyEventArgs(input.key(), input.scancode(), input.modifiers()));
    }

    @Deprecated
    @Override
    public boolean keyPressed(KeyEvent input) {
        return this.keyPressed(new KeyEventArgs(input.key(), input.scancode(), input.modifiers()));
    }

    @Deprecated
    @Override
    public void extractMenuBackground(GuiGraphicsExtractor context) {
        callRenderBackground(new RenderArgs(new DrawObjectDM(context, this), 0, 0, 0));
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

    public void setTitleX(int x) {
        this.titleLabelX = x;
    }

    public void setTitleY(int y) {
        this.titleLabelY = y;
    }

    public void setTitlePos(int x, int y) {
        setTitleX(x);
        setTitleY(y);
    }

    public void setTitleXCenter() {
        if (textRenderer == null)
            textRenderer = ClientUtil.getTextRenderer();

        setTitleX(backgroundWidth / 2 - textRenderer.width(title) / 2);
    }

    public int getTitleX() {
        return titleLabelX;
    }

    public int getTitleY() {
        return titleLabelY;
    }

    public void drawText(DrawObjectDM drawObjectDM, Component text, int x, int y, int color) {
        ScreenUtil.RendererUtil.drawText(textRenderer, drawObjectDM, text, x, y, color);
    }

    public void drawText(DrawObjectDM drawObjectDM, TextComponent text, int x, int y, int color) {
        ScreenUtil.RendererUtil.drawText(textRenderer, drawObjectDM, text, x, y, color);
    }

    public void drawText(DrawObjectDM drawObjectDM, Component text, int x, int y) {
        ScreenUtil.RendererUtil.drawText(textRenderer, drawObjectDM, text, x, y);
    }

    public void drawText(DrawObjectDM drawObjectDM, TextComponent text, int x, int y) {
        ScreenUtil.RendererUtil.drawText(textRenderer, drawObjectDM, text, x, y);
    }

    @Deprecated
    @Override
    public Component getTitle() {
        return callGetTitle();
    }

    public Component callGetTitle() {
        return super.getTitle();
    }

    public Pair<Integer, Integer> getTitlePosP() {
        return new Pair<>(getTitleX(), getTitleY());
    }

    public int getPlayerInvTitleX() {
        return inventoryLabelX;
    }

    public int getPlayerInvTitleY() {
        return inventoryLabelY;
    }

    public void setPlayerInvTitleX(int x) {
        inventoryLabelX = x;
    }

    public void setPlayerInvTitleY(int y) {
        inventoryLabelY = y;
    }

    public void setPlayerInvTitle(int x, int y) {
        setPlayerInvTitleX(x);
        setPlayerInvTitleY(y);
    }

    public Font callGetTextRenderer() {
        if (textRenderer != null)
            return textRenderer;

        if (super.getFont() != null)
            return super.getFont();

        return ClientUtil.getTextRenderer();
    }

    public ItemModelResolver callGetItemRenderer() {
        if (itemRenderer != null)
            return itemRenderer;

        return ClientUtil.getItemRenderer();
    }

    public Component getPlayerInvTitle() {
        return playerInventoryTitle;
    }

    public boolean charTyped(CharEventArgs args) {
        return super.charTyped(new CharacterEvent(args.getCharacter()));
    }

    @Deprecated
    @Override
    public boolean charTyped(CharacterEvent event) {
        return charTyped(new CharEventArgs(event.codepoint()));
    }

    public boolean mouseScrolled(MouseScrolledArgs args) {
        return super.mouseScrolled(args.getMouseX(), args.getMouseY(), args.getScrollX(), args.getScrollY());
    }

    @Deprecated
    @Override
    public boolean mouseScrolled(double x, double y, double scrollX, double scrollY) {
        return mouseScrolled(new MouseScrolledArgs(x, y, scrollX, scrollY));
    }

    public boolean mouseClicked(MouseClickedArgs args) {
        return super.mouseClicked(new MouseButtonEvent(args.getX(), args.getY(), args.getButtonInfo()), args.isDoubleClick());
    }

    @Deprecated
    @Override
    public boolean mouseClicked(MouseButtonEvent event, boolean doubleClick) {
        return mouseClicked(new MouseClickedArgs(event.x(), event.y(), event.buttonInfo(), doubleClick));
    }

    public boolean mouseDragged(MouseDraggedArgs args) {
        return super.mouseDragged(new MouseButtonEvent(args.getX(), args.getY(), args.getButtonInfo()), args.getDeltaX(), args.getDeltaY());
    }

    @Deprecated
    @Override
    public boolean mouseDragged(MouseButtonEvent event, double dx, double dy) {
        return mouseDragged(new MouseDraggedArgs(event.x(), event.y(), event.buttonInfo(), dx, dy));
    }

    public boolean mouseReleased(MouseReleasedArgs args) {
        return super.mouseReleased(new MouseButtonEvent(args.getX(), args.getY(), args.getButtonInfo()));
    }

    @Deprecated
    @Override
    public boolean mouseReleased(MouseButtonEvent event) {
        return mouseReleased(new MouseReleasedArgs(event.x(), event.y(), event.buttonInfo()));
    }
}
