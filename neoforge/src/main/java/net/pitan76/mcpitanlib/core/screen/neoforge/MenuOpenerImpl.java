package net.pitan76.mcpitanlib.core.screen.neoforge;

import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import net.pitan76.mcpitanlib.core.screen.ExtendedMenuProvider;

public class MenuOpenerImpl {

    public static void openExtendedMenu(ServerPlayerEntity player, ExtendedMenuProvider<?> provider) {
        player.openMenu(provider, buf -> {
            Object data = provider.getScreenOpeningData(player);
            if (data instanceof PacketByteBuf) {
                PacketByteBuf packetByteBuf = (PacketByteBuf) data;
                buf.writeBytes(packetByteBuf.copy());
            }
        });
    }
}
