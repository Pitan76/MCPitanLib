package net.pitan76.mcpitanlib.api.gui;

import dev.architectury.registry.menu.ExtendedMenuProvider;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.text.Text;
import net.pitan76.mcpitanlib.api.event.container.factory.DisplayNameArgs;
import net.pitan76.mcpitanlib.api.event.container.factory.ExtraDataArgs;

@Deprecated
public interface ExtendedScreenHandlerFactory extends ExtendedMenuProvider {
    @Override
    default Text getDisplayName() {
        return getDisplayName(new DisplayNameArgs());
    }

    @Override
    default void saveExtraData(PacketByteBuf buf) {
        writeExtraData(new ExtraDataArgs(buf));
    }

    Text getDisplayName(DisplayNameArgs args);

    void writeExtraData(ExtraDataArgs args);
}
