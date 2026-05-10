package net.pitan76.mcpitanlib.api.client.gui.screen;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.pitan76.mcpitanlib.api.client.render.DrawObjectDM;
import net.pitan76.mcpitanlib.api.text.TextComponent;
import net.pitan76.mcpitanlib.api.util.CompatIdentifier;
import net.pitan76.mcpitanlib.api.util.inventory.CompatPlayerInventory;
import net.pitan76.mcpitanlib.guilib.api.render.SlotRenderer;

public abstract class CompatInventoryScreen<S extends ScreenHandler> extends SimpleInventoryScreen<S> {

    public CompatInventoryScreen(S handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
    }

    public CompatInventoryScreen(S handler, CompatPlayerInventory inventory, TextComponent title) {
        this(handler, inventory.getRaw(), title.getText());
    }

    public abstract CompatIdentifier getCompatTexture();

    @Deprecated
    @Override
    public Identifier getTexture() {
        return getCompatTexture().toMinecraft();
    }

    public void drawSlot(DrawObjectDM drawObjectDM, Slot slot) {
        SlotRenderer.drawSlot(drawObjectDM, slot, x, y);
    }

    public void drawSlots(DrawObjectDM drawObjectDM) {
        SlotRenderer.drawSlots(drawObjectDM, handler, x, y);
    }
}
