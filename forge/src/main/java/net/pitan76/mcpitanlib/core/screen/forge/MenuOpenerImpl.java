package net.pitan76.mcpitanlib.core.screen.forge;

import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraftforge.fml.network.NetworkHooks;
import net.pitan76.mcpitanlib.core.screen.ExtendedMenuProvider;

import java.util.function.Consumer;

public class MenuOpenerImpl {

    public static void openExtendedMenu(ServerPlayerEntity player, final ExtendedMenuProvider provider) {
        NetworkHooks.openGui(player, provider, new Consumer<PacketByteBuf>() {
            @Override
            public void accept(PacketByteBuf buf) {
                provider.saveExtraData(buf);
            }
        });
    }

    public static void openExtendedMenu(ServerPlayerEntity player, NamedScreenHandlerFactory provider, Consumer<PacketByteBuf> bufWriter) {
        NetworkHooks.openGui(player, provider, bufWriter);
    }
}
