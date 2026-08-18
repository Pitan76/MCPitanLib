package net.pitan76.mcpitanlib.api.gui;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerFactory;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.pitan76.mcpitanlib.api.network.PacketByteUtil;
import net.pitan76.mcpitanlib.core.screen.ExtendedMenuProvider;
import org.jetbrains.annotations.Nullable;

@Deprecated
public class ExtendedNamedScreenHandlerFactory implements ExtendedMenuProvider<PacketByteBuf> {

    private final Text name;
    private final ScreenHandlerFactory baseFactory;
    private final PacketByteBufFactory bufFactory;

    public ExtendedNamedScreenHandlerFactory(Text name, ScreenHandlerFactory baseFactory, PacketByteBufFactory bufFactory) {
        this.name = name;
        this.baseFactory = baseFactory;
        this.bufFactory = bufFactory;
    }

    public void saveExtraData(PacketByteBuf buf) {
        bufFactory.saveExtraData(buf);
    }

    @Override
    public PacketByteBuf getScreenOpeningData(ServerPlayerEntity player) {
        PacketByteBuf buf = PacketByteUtil.create();
        saveExtraData(buf);
        return buf;
    }

    @Override
    public Text getDisplayName() {
        return name;
    }

    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory inv, PlayerEntity player) {
        return baseFactory.createMenu(syncId, inv, player);
    }

    @FunctionalInterface
    public interface PacketByteBufFactory {
        void saveExtraData(PacketByteBuf buf);
    }
}
