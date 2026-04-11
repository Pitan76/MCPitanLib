package net.pitan76.mcpitanlib.api.client;

import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.pitan76.mcpitanlib.api.util.CompatIdentifier;

@Deprecated
public abstract class CompatInventoryScreen extends SimpleInventoryScreen {

    public CompatInventoryScreen(AbstractContainerMenu handler, Inventory inventory, Component title) {
        super(handler, inventory, title);
    }

    public abstract CompatIdentifier getCompatTexture();

    @Override
    public Identifier getTexture() {
        return getCompatTexture().toMinecraft();
    }
}
