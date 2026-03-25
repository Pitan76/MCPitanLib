package net.pitan76.mcpitanlib.api.gui;

import dev.architectury.registry.menu.ExtendedMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuConstructor;
import net.minecraft.network.chat.Component;
import org.jetbrains.annotations.Nullable;

@Deprecated
public class ExtendedNamedScreenHandlerFactory implements ExtendedMenuProvider {

    private final Component name;
    private final MenuConstructor baseFactory;
    private final PacketByteBufFactory bufFactory;

    public ExtendedNamedScreenHandlerFactory(Component name, MenuConstructor baseFactory, PacketByteBufFactory bufFactory) {
        this.name = name;
        this.baseFactory = baseFactory;
        this.bufFactory = bufFactory;
    }

    @Override
    public void saveExtraData(FriendlyByteBuf buf) {
        bufFactory.saveExtraData(buf);
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

    @FunctionalInterface
    public interface PacketByteBufFactory {
        void saveExtraData(FriendlyByteBuf buf);
    }
}
