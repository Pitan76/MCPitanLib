package net.pitan76.mcpitanlib.api.gui;

import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.pitan76.mcpitanlib.api.event.container.factory.DisplayNameArgs;
import net.pitan76.mcpitanlib.api.event.container.factory.ExtraDataArgs;
import net.pitan76.mcpitanlib.api.network.PacketByteUtil;
import net.pitan76.mcpitanlib.core.screen.ExtendedMenuProvider;

@Deprecated
public interface ExtendedScreenHandlerFactory extends ExtendedMenuProvider<PacketByteBuf> {
    @Override
    default Text getDisplayName() {
        return getDisplayName(new DisplayNameArgs());
    }

    @Override
    default PacketByteBuf getScreenOpeningData(ServerPlayerEntity player) {
        PacketByteBuf buf = PacketByteUtil.create();
        writeExtraData(new ExtraDataArgs(buf));
        return buf;
    }

    Text getDisplayName(DisplayNameArgs args);

    void writeExtraData(ExtraDataArgs args);
}
