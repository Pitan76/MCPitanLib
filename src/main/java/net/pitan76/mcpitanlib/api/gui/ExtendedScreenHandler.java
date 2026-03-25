package net.pitan76.mcpitanlib.api.gui;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.inventory.MenuType;
import org.jetbrains.annotations.Nullable;

public class ExtendedScreenHandler extends SimpleScreenHandler {
    protected ExtendedScreenHandler(@Nullable MenuType<?> type, int syncId, FriendlyByteBuf buf) {
        this(type, syncId);
    }

    protected ExtendedScreenHandler(@Nullable MenuType<?> type, int syncId) {
        super(type, syncId);
    }
}
