package net.pitan76.mcpitanlib.guilib.api.screen;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.pitan76.mcpitanlib.api.client.gui.screen.CompatInventoryScreen;
import net.pitan76.mcpitanlib.api.client.render.DrawObjectDM;
import net.pitan76.mcpitanlib.api.client.render.handledscreen.DrawBackgroundArgs;
import net.pitan76.mcpitanlib.api.client.render.handledscreen.DrawForegroundArgs;
import net.pitan76.mcpitanlib.api.util.CompatIdentifier;
import net.pitan76.mcpitanlib.api.util.ScreenHandlerUtil;
import net.pitan76.mcpitanlib.guilib.GuiTextures;
import net.pitan76.mcpitanlib.guilib.api.container.ContainerGui;
import net.pitan76.mcpitanlib.guilib.api.render.SlotRenderer;

public class ContainerGuiScreen<T extends ContainerGui> extends CompatInventoryScreen<T> {
    public ContainerGuiScreen(T handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
        setBackgroundWidth(handler.getScreenBackgroundWidth());
        setBackgroundHeight(handler.getScreenBackgroundHeight());

        if (handler.getScreenX() != -1)
            setX(handler.getScreenX());
        if (handler.getScreenY() != -1)
            setY(handler.getScreenY());

        if (handler.getScreenTitleX() != -1)
            setTitleX(handler.getScreenTitleX());
        if (handler.getScreenTitleY() != -1)
            setTitleY(handler.getScreenTitleY());
    }

    @Override
    public void initOverride() {
        super.initOverride();
    }

    @Override
    public void drawBackgroundOverride(DrawBackgroundArgs args) {
        super.drawBackgroundOverride(args);

        if (handler.hasMainInventory && !containPlayerInventoryInBackground())
            drawPlayerMainInventorySlots(args.drawObjectDM);

        if (handler.hasHotbar && !containPlayerInventoryInBackground())
            drawPlayerHotbarSlots(args.drawObjectDM);

        drawSlots(args.drawObjectDM);
    }

    @Override
    protected void drawForegroundOverride(DrawForegroundArgs args) {
        if (!handler.hasMainInventory && !containPlayerInventoryInBackground()) {
            drawText(args.drawObjectDM, title, titleX, titleY);
            return;
        }

        super.drawForegroundOverride(args);
    }

    @Override
    public CompatIdentifier getCompatTexture() {
        return handler.getTexture();
    }

    public void drawPlayerMainInventorySlots(DrawObjectDM drawObjectDM) {
        SlotRenderer.drawPlayerMainInventorySlots(drawObjectDM, handler, x + handler.playerMainInventoryX - 1, y + handler.playerMainInventoryY - 1);
    }

    public void drawPlayerHotbarSlots(DrawObjectDM drawObjectDM) {
        SlotRenderer.drawPlayerHotbarSlots(drawObjectDM, handler, x + handler.playerHotbarX - 1, y + handler.playerHotbarY - 1);
    }

    public boolean containPlayerInventoryInBackground() {
        return getCompatTexture() == GuiTextures.BASE_FURNACE_BACKGROUND;
    }

    @Override
    public void drawSlots(DrawObjectDM drawObjectDM) {
        ScreenHandlerUtil.getSlots(handler).forEach((slot) -> {
            if (slot.inventory instanceof PlayerInventory || containPlayerInventoryInBackground())
                return;

            SlotRenderer.drawSlot(drawObjectDM, slot, x, y);
        });
    }
}
