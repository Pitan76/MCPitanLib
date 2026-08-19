package net.pitan76.mcpitanlib.core.screen.forge;

import net.minecraft.server.network.ServerPlayerEntity;
import net.pitan76.mcpitanlib.core.screen.ExtendedMenuProvider;

public class MenuOpenerImpl {
    public static void openExtendedMenu(ServerPlayerEntity player, ExtendedMenuProvider provider) {
        player.openMenu(provider, buf -> provider.writeScreenOpeningData(player, buf));
    }
}

