package net.pitan76.mcpitanlib.guilib.api.screen;

import net.minecraft.world.entity.player.Inventory;
import net.minecraft.network.chat.Component;
import net.pitan76.mcpitanlib.api.text.TextComponent;
import net.pitan76.mcpitanlib.api.util.inventory.CompatPlayerInventory;
import net.pitan76.mcpitanlib.guilib.api.container.SimpleContainerGui;

public class SimpleContainerGuiScreen extends ContainerGuiScreen<SimpleContainerGui> {
    public SimpleContainerGuiScreen(SimpleContainerGui handler, Inventory inventory, Component title) {
        super(handler, inventory, title);
    }

    public SimpleContainerGuiScreen(SimpleContainerGui handler, CompatPlayerInventory inventory, TextComponent title) {
        super(handler, inventory, title);
    }

    @Override
    public void initOverride() {
        super.initOverride();
    }
}
