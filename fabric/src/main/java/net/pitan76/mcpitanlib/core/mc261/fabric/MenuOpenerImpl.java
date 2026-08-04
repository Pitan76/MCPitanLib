package net.pitan76.mcpitanlib.core.mc261.fabric;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.pitan76.mcpitanlib.core.mc261.ExtendedMenuProvider;
import org.jspecify.annotations.Nullable;

public class MenuOpenerImpl {

    public static void openExtendedMenu(ServerPlayer player, ExtendedMenuProvider<?> provider) {
        player.openMenu(bridge(provider));
    }

    private static <D> FabricBridge<D> bridge(ExtendedMenuProvider<D> provider) {
        return new FabricBridge<>(provider);
    }

    /**
     * Fabric refuses to open a menu whose type is an {@code ExtendedMenuType} unless the
     * provider also implements Fabric's own {@code ExtendedMenuProvider}, so wrap ours in one.
     */
    private record FabricBridge<D>(ExtendedMenuProvider<D> delegate)
            implements net.fabricmc.fabric.api.menu.v1.ExtendedMenuProvider<D> {

        @Override
        public D getScreenOpeningData(ServerPlayer player) {
            return delegate.getScreenOpeningData(player);
        }

        @Override
        public Component getDisplayName() {
            return delegate.getDisplayName();
        }

        @Override
        public @Nullable AbstractContainerMenu createMenu(int containerId, Inventory inventory, Player player) {
            return delegate.createMenu(containerId, inventory, player);
        }

        @Override
        public boolean shouldCloseCurrentScreen() {
            return delegate.shouldCloseCurrentScreen();
        }
    }
}
