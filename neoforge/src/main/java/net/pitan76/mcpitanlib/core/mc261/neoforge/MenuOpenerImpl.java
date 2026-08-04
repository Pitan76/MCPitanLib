package net.pitan76.mcpitanlib.core.mc261.neoforge;

import net.minecraft.server.level.ServerPlayer;
import net.pitan76.mcpitanlib.core.mc261.ExtendedMenuProvider;

public class MenuOpenerImpl {

    public static void openExtendedMenu(ServerPlayer player, ExtendedMenuProvider<?> provider) {
        player.openMenu(provider);
    }
}
