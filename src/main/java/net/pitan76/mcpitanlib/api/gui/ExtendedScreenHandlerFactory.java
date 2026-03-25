package net.pitan76.mcpitanlib.api.gui;

import net.fabricmc.fabric.api.menu.v1.ExtendedMenuProvider;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.pitan76.mcpitanlib.api.event.container.factory.DisplayNameArgs;
import net.pitan76.mcpitanlib.api.event.container.factory.ExtraDataArgs;
import net.pitan76.mcpitanlib.api.network.PacketByteUtil;

@Deprecated
public interface ExtendedScreenHandlerFactory extends ExtendedMenuProvider<FriendlyByteBuf> {
    @Override
    default Component getDisplayName() {
        return getDisplayName(new DisplayNameArgs());
    }

    @Override
    default FriendlyByteBuf getScreenOpeningData(ServerPlayer player) {
        FriendlyByteBuf buf = PacketByteUtil.create();
        writeExtraData(new ExtraDataArgs(buf));
        return buf;
    }

    Component getDisplayName(DisplayNameArgs args);

    void writeExtraData(ExtraDataArgs args);
}
