package net.pitan76.mcpitanlib.api.client.gui.screen;

import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.pitan76.mcpitanlib.api.client.render.DrawObjectDM;
import net.pitan76.mcpitanlib.api.util.CompatIdentifier;
import net.pitan76.mcpitanlib.guilib.api.render.SlotRenderer;

public abstract class CompatInventoryScreen<S extends AbstractContainerMenu> extends SimpleInventoryScreen<S> {

    public CompatInventoryScreen(S handler, Inventory inventory, Component title) {
        super(handler, inventory, title);
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
