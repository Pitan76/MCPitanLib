package net.pitan76.mcpitanlib.api.gui.v2;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.ScreenHandler;
import net.pitan76.mcpitanlib.api.gui.args.CreateMenuEvent;

@SuppressWarnings("deprecation")
public interface ExtendedScreenHandlerFactory extends net.pitan76.mcpitanlib.api.gui.ExtendedScreenHandlerFactory {

    @Override
    default ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return createMenu(new CreateMenuEvent(syncId, playerInventory, player));
    }

    ScreenHandler createMenu(CreateMenuEvent event);
}
