package net.pitan76.mcpitanlib.api.client.gui.screen;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.pitan76.mcpitanlib.api.util.CompatIdentifier;

public abstract class CompatInventoryScreen<S extends ScreenHandler> extends SimpleInventoryScreen<S> {

    public CompatInventoryScreen(S handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
    }

    public abstract CompatIdentifier getCompatTexture();

    @Override
    public Identifier getTexture() {
        return getCompatTexture().toMinecraft();
    }
}
