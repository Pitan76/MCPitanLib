package net.pitan76.mcpitanlib.api.gui.v2;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.pitan76.mcpitanlib.api.gui.args.CreateMenuEvent;

@SuppressWarnings("deprecation")
public interface ExtendedScreenHandlerFactory extends net.pitan76.mcpitanlib.api.gui.ExtendedScreenHandlerFactory {

    @Override
    default AbstractContainerMenu createMenu(int syncId, Inventory playerInventory, Player player) {
        return createMenu(new CreateMenuEvent(syncId, playerInventory, player));
    }

    AbstractContainerMenu createMenu(CreateMenuEvent event);
}
