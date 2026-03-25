package net.pitan76.mcpitanlib.guilib.api.render;

import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;
import net.pitan76.mcpitanlib.api.client.render.DrawObjectDM;
import net.pitan76.mcpitanlib.api.util.ScreenHandlerUtil;
import net.pitan76.mcpitanlib.api.util.client.ScreenUtil.RendererUtil;
import net.pitan76.mcpitanlib.guilib.GuiTextures;

public class SlotRenderer {

    public static void drawSlot(DrawObjectDM drawObjectDM, Slot slot, int x, int y) {
        RendererUtil.drawTexture(drawObjectDM, GuiTextures.GUI_PARTS, x + slot.x - 1, y + slot.y - 1, 0, 0, 18, 18);
    }

    public static void drawSlots(DrawObjectDM drawObjectDM, ScreenHandler handler, int x, int y) {
        ScreenHandlerUtil.getSlots(handler).forEach((slot) -> drawSlot(drawObjectDM, slot, x, y));
    }

    public static void drawPlayerMainInventorySlots(DrawObjectDM drawObjectDM, ScreenHandler handler, int x, int y) {
        RendererUtil.drawTexture(drawObjectDM, GuiTextures.GUI_PARTS, x, y, 0, 32, 162, 54);
    }

    public static void drawPlayerHotbarSlots(DrawObjectDM drawObjectDM, ScreenHandler handler, int x, int y) {
        RendererUtil.drawTexture(drawObjectDM, GuiTextures.GUI_PARTS, x, y, 0, 90, 162, 18);
    }
}
