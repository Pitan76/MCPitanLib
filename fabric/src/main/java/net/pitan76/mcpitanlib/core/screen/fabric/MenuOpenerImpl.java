package net.pitan76.mcpitanlib.core.screen.fabric;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.pitan76.mcpitanlib.core.screen.ExtendedMenuProvider;
import org.jetbrains.annotations.Nullable;

public class MenuOpenerImpl {

    public static void openExtendedMenu(ServerPlayerEntity player, ExtendedMenuProvider provider) {
        player.openHandledScreen(bridge(provider));
    }

    private static FabricBridge bridge(ExtendedMenuProvider provider) {
        return new FabricBridge(provider);
    }

    private record FabricBridge(ExtendedMenuProvider delegate)
            implements net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory {

        @Override
        public void writeScreenOpeningData(ServerPlayerEntity player, PacketByteBuf buf) {
            delegate.writeScreenOpeningData(player, buf);
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

    }
}
