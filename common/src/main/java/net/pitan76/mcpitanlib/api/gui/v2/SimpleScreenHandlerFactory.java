package net.pitan76.mcpitanlib.api.gui.v2;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.network.chat.Component;
import net.pitan76.mcpitanlib.api.event.container.factory.DisplayNameArgs;
import net.pitan76.mcpitanlib.api.gui.args.CreateMenuEvent;

public interface SimpleScreenHandlerFactory extends MenuProvider {
    @Override
    default Component getDisplayName() {
        return getDisplayName(new DisplayNameArgs());
    }

    Component getDisplayName(DisplayNameArgs args);

    @Override
    default AbstractContainerMenu createMenu(int syncId, Inventory playerInventory, Player player) {
        return createMenu(new CreateMenuEvent(syncId, playerInventory, player));
    }

    AbstractContainerMenu createMenu(CreateMenuEvent event);
}
