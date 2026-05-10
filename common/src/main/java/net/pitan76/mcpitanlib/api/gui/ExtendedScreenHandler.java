package net.pitan76.mcpitanlib.api.gui;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.inventory.MenuType;
import net.pitan76.mcpitanlib.midohra.network.IByteBuf;
import net.pitan76.mcpitanlib.midohra.screen.ScreenHandlerTypeWrapper;
import org.jetbrains.annotations.Nullable;

public class ExtendedScreenHandler extends SimpleScreenHandler {
    protected ExtendedScreenHandler(@Nullable MenuType<?> type, int syncId, FriendlyByteBuf buf) {
        this(type, syncId);
    }

    protected ExtendedScreenHandler(@Nullable MenuType<?> type, int syncId) {
        super(type, syncId);
    }

    protected ExtendedScreenHandler(@Nullable MenuType<?> type, int syncId, IByteBuf buf) {
        this(type, syncId, buf.toRaw());
    }

    protected ExtendedScreenHandler(ScreenHandlerTypeWrapper type, int syncId) {
        this(type.get(), syncId);
    }

    protected ExtendedScreenHandler(ScreenHandlerTypeWrapper type, int syncId, IByteBuf buf) {
        this(type.get(), syncId, buf);
    }

    protected ExtendedScreenHandler(ScreenHandlerTypeWrapper type, int syncId, net.pitan76.mcpitanlib.midohra.network.PacketByteBuf buf) {
        this(type.get(), syncId, buf);
    }
}
