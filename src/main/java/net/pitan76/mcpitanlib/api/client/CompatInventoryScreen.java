package net.pitan76.mcpitanlib.api.client;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.resources.Identifier;
import net.pitan76.mcpitanlib.api.util.CompatIdentifier;

@Deprecated
public abstract class CompatInventoryScreen extends SimpleInventoryScreen {

    public CompatInventoryScreen(ScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
    }

    public abstract CompatIdentifier getCompatTexture();

    @Override
    public Identifier getTexture() {
        return getCompatTexture().toMinecraft();
    }
}
