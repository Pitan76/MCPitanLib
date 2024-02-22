package net.pitan76.mcpitanlib.api.client;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.pitan76.mcpitanlib.api.client.render.handledscreen.DrawBackgroundArgs;
import net.pitan76.mcpitanlib.api.client.render.handledscreen.DrawMouseoverTooltipArgs;
import net.pitan76.mcpitanlib.api.client.render.handledscreen.RenderArgs;
import net.pitan76.mcpitanlib.api.util.client.RenderUtil;

public abstract class SimpleInventoryScreen extends SimpleHandledScreen {

    public SimpleInventoryScreen(ScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
    }

    public abstract Identifier getTexture();

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
