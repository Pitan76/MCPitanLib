package net.pitan76.mcpitanlib.core.screen;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.server.network.ServerPlayerEntity;

import java.util.function.Consumer;

public class MenuOpener {
    @ExpectPlatform
    public static void openExtendedMenu(ServerPlayerEntity player, ExtendedMenuProvider provider) {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static void openExtendedMenu(ServerPlayerEntity player, NamedScreenHandlerFactory provider, Consumer<PacketByteBuf> bufWriter) {
        throw new AssertionError();
    }

    public static void openMenu(ServerPlayerEntity player, NamedScreenHandlerFactory provider) {
        player.openHandledScreen(provider);
    }
}
