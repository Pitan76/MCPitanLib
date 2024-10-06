package net.pitan76.mcpitanlib.guilib.api.screen;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.pitan76.mcpitanlib.guilib.api.container.SimpleContainerGui;

public class SimpleContainerGuiScreen extends ContainerGuiScreen<SimpleContainerGui> {
    public SimpleContainerGuiScreen(SimpleContainerGui handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
    }

    @Override
    public void initOverride() {
        super.initOverride();
    }
}
