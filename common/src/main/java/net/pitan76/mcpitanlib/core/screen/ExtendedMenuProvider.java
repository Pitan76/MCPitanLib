package net.pitan76.mcpitanlib.core.screen;

import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.server.network.ServerPlayerEntity;

public interface ExtendedMenuProvider extends NamedScreenHandlerFactory {
    void writeScreenOpeningData(ServerPlayerEntity player, PacketByteBuf buf);
}
