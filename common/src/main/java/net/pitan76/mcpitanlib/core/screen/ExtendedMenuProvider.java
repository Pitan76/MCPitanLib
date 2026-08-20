package net.pitan76.mcpitanlib.core.screen;

import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.NamedScreenHandlerFactory;

public interface ExtendedMenuProvider extends NamedScreenHandlerFactory {
    void saveExtraData(PacketByteBuf buf);
}
