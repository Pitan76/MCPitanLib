package net.pitan76.mcpitanlib.core.screen.fabric;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.pitan76.mcpitanlib.core.screen.ExtendedMenuProvider;
import org.jetbrains.annotations.Nullable;

public class MenuOpenerImpl {

    public static void openExtendedMenu(ServerPlayerEntity player, ExtendedMenuProvider<?> provider) {
        player.openHandledScreen(bridge(provider));
    }

    private static <D> FabricBridge<D> bridge(ExtendedMenuProvider<D> provider) {
        return new FabricBridge<>(provider);
    }

    /**
     * FabricはExtendedScreenHandlerTypeのメニューを開く際、
     * FabricのExtendedScreenHandlerFactoryを実装していないと拒否するのでラップする。
     */
    private record FabricBridge<D>(ExtendedMenuProvider<D> delegate)
            implements net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory<D> {

        @Override
        public D getScreenOpeningData(ServerPlayerEntity player) {
            return delegate.getScreenOpeningData(player);
        }

        @Override
        public Text getDisplayName() {
            return delegate.getDisplayName();
        }

        @Nullable
        @Override
        public ScreenHandler createMenu(int syncId, PlayerInventory inventory, PlayerEntity player) {
            return delegate.createMenu(syncId, inventory, player);
        }

        @Override
        public boolean shouldCloseCurrentScreen() {
            return delegate.shouldCloseCurrentScreen();
        }
    }
}
