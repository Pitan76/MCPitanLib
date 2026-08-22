package net.pitan76.mcpitanlib.core.mc261.neoforge;

import io.netty.buffer.ByteBuf;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.pitan76.mcpitanlib.api.util.LoggerUtil;
import net.pitan76.mcpitanlib.core.mc261.ExtendedMenuProvider;

public class MenuOpenerImpl {

    public static void openExtendedMenu(ServerPlayer player, ExtendedMenuProvider<?> provider) {
        player.openMenu(provider, buf -> writeScreenOpeningData(buf, provider, player));
    }

    private static <D> void writeScreenOpeningData(RegistryFriendlyByteBuf out, ExtendedMenuProvider<D> provider, ServerPlayer player) {
        D data = provider.getScreenOpeningData(player);
        if (data == null) return;

        if (!(data instanceof ByteBuf)) {
            LoggerUtil.getLogger(MenuOpenerImpl.class).error("Screen opening data of " + provider.getClass().getName() + " is not a buf, so it cannot be sent: " + data.getClass().getName());
            return;
        }

        ByteBuf source = (ByteBuf) data;
        out.writeBytes(source, source.readerIndex(), source.readableBytes());
    }
}
