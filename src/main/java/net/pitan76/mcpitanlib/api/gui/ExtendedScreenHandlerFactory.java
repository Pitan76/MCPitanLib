package net.pitan76.mcpitanlib.api.gui;

import dev.architectury.registry.menu.ExtendedMenuProvider;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.pitan76.mcpitanlib.api.event.container.factory.DisplayNameArgs;
import net.pitan76.mcpitanlib.api.event.container.factory.ExtraDataArgs;

@Deprecated
public interface ExtendedScreenHandlerFactory extends ExtendedMenuProvider {
    @Override
    default Component getDisplayName() {
        return getDisplayName(new DisplayNameArgs());
    }

    @Override
    default void saveExtraData(FriendlyByteBuf buf) {
        writeExtraData(new ExtraDataArgs(buf));
    }

    Component getDisplayName(DisplayNameArgs args);

    void writeExtraData(ExtraDataArgs args);
}
