package net.pitan76.mcpitanlib.api.client.gui.screen;

import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.pitan76.mcpitanlib.api.client.render.handledscreen.DrawBackgroundArgs;
import net.pitan76.mcpitanlib.api.client.render.handledscreen.DrawMouseoverTooltipArgs;
import net.pitan76.mcpitanlib.api.client.render.handledscreen.RenderArgs;
import net.pitan76.mcpitanlib.api.text.TextComponent;
import net.pitan76.mcpitanlib.api.util.client.RenderUtil;
import net.pitan76.mcpitanlib.api.util.inventory.CompatPlayerInventory;

public abstract class SimpleInventoryScreen<S extends AbstractContainerMenu> extends SimpleHandledScreen<S> {

    public SimpleInventoryScreen(S handler, Inventory inventory, Component title) {
        super(handler, inventory, title);
    }

    public SimpleInventoryScreen(S handler, CompatPlayerInventory inventory, TextComponent title) {
        this(handler, inventory.getRaw(), title.getText());
    }

    public abstract Identifier getTexture();

    @Override
    public Identifier getBackgroundTexture() {
        return getTexture();
    }

    @Override
    public void drawBackgroundOverride(DrawBackgroundArgs args) {
        RenderUtil.setShaderToPositionTexProgram();
        RenderUtil.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        callDrawTexture(args.drawObjectDM, getTexture(), x, y, 0, 0, backgroundWidth, backgroundHeight);
    }

    @Override
    public void renderOverride(RenderArgs args) {
        this.callRenderBackground(args);
        super.renderOverride(args);
        this.callDrawMouseoverTooltip(new DrawMouseoverTooltipArgs(args.drawObjectDM, args.mouseX, args.mouseY));
    }
}
