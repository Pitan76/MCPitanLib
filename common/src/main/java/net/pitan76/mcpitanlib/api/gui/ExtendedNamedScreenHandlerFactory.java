package net.pitan76.mcpitanlib.api.gui;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuConstructor;
import net.minecraft.network.chat.Component;
import net.pitan76.mcpitanlib.api.network.PacketByteUtil;
import net.pitan76.mcpitanlib.core.mc261.ExtendedMenuProvider;
import org.jetbrains.annotations.Nullable;

@Deprecated
public class ExtendedNamedScreenHandlerFactory implements ExtendedMenuProvider<FriendlyByteBuf> {

    private final Component name;
    private final MenuConstructor baseFactory;
    private final PacketByteBufFactory bufFactory;

    public ExtendedNamedScreenHandlerFactory(Component name, MenuConstructor baseFactory, PacketByteBufFactory bufFactory) {
        this.name = name;
        this.baseFactory = baseFactory;
        this.bufFactory = bufFactory;
    }

    @Override
    public Component getDisplayName() {
        return name;
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int syncId, Inventory inv, Player player) {
        return baseFactory.createMenu(syncId, inv, player);

    }

    @Override
    public FriendlyByteBuf getScreenOpeningData(ServerPlayer player) {
        FriendlyByteBuf buf = PacketByteUtil.create();
        bufFactory.saveExtraData(buf);
        return buf;
    }

    @FunctionalInterface
    public interface PacketByteBufFactory {
        void saveExtraData(FriendlyByteBuf buf);
    }
}
