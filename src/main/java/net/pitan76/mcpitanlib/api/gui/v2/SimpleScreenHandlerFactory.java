package net.pitan76.mcpitanlib.api.gui.v2;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.pitan76.mcpitanlib.api.event.container.factory.DisplayNameArgs;
import net.pitan76.mcpitanlib.api.gui.args.CreateMenuEvent;

public interface SimpleScreenHandlerFactory extends NamedScreenHandlerFactory {
    @Override
    default Text getDisplayName() {
        return getDisplayName(new DisplayNameArgs());
    }

    Text getDisplayName(DisplayNameArgs args);

    @Override
    default ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return createMenu(new CreateMenuEvent(syncId, playerInventory, player));
    }

    ScreenHandler createMenu(CreateMenuEvent event);
}
