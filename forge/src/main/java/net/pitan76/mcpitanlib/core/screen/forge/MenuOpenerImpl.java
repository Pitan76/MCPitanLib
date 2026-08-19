package net.pitan76.mcpitanlib.core.screen.forge;

import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraftforge.network.NetworkHooks;
import net.pitan76.mcpitanlib.core.screen.ExtendedMenuProvider;

public class MenuOpenerImpl {
    public static void openExtendedMenu(ServerPlayerEntity player, ExtendedMenuProvider provider) {
        NetworkHooks.openScreen(player, provider, buf -> provider.writeScreenOpeningData(player, buf));
    }
}

