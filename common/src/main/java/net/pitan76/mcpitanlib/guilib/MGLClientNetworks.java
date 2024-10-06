package net.pitan76.mcpitanlib.guilib;

import net.pitan76.mcpitanlib.api.network.v2.ClientNetworking;
import net.pitan76.mcpitanlib.guilib.api.container.ExtendedBlockEntityContainerGui;

import static net.pitan76.mcpitanlib.guilib.api.NetworkDefines.SYNC_GUI_WITH_TILE;

public class MGLClientNetworks {
    public static void init() {
        ClientNetworking.registerReceiver(SYNC_GUI_WITH_TILE, (e) -> {
            if (e.player.getCurrentScreenHandler() instanceof ExtendedBlockEntityContainerGui) {
                ExtendedBlockEntityContainerGui<?> gui = (ExtendedBlockEntityContainerGui<?>) e.player.getCurrentScreenHandler();
                gui.receiveSync(e.buf);
            }
        });
    }
}
