package net.pitan76.mcpitanlib.core.screen.fabric;

import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.pitan76.mcpitanlib.core.screen.ExtendedMenuProvider;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

public class MenuOpenerImpl {

    public static void openExtendedMenu(ServerPlayerEntity player, ExtendedMenuProvider provider) {
        player.openHandledScreen(new FabricBridge(provider));
    }

    public static void openExtendedMenu(ServerPlayerEntity player, NamedScreenHandlerFactory provider, Consumer<PacketByteBuf> bufWriter) {
        player.openHandledScreen(new FabricConsumerBridge(provider, bufWriter));
    }

    private static class FabricBridge implements ExtendedScreenHandlerFactory {
        private final ExtendedMenuProvider delegate;

        FabricBridge(ExtendedMenuProvider delegate) {
            this.delegate = delegate;
        }

        @Override
        public void writeScreenOpeningData(ServerPlayerEntity player, PacketByteBuf buf) {
            delegate.saveExtraData(buf);
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

    private static class FabricConsumerBridge implements ExtendedScreenHandlerFactory {
        private final NamedScreenHandlerFactory delegate;
        private final Consumer<PacketByteBuf> bufWriter;

        FabricConsumerBridge(NamedScreenHandlerFactory delegate, Consumer<PacketByteBuf> bufWriter) {
            this.delegate = delegate;
            this.bufWriter = bufWriter;
        }

        @Override
        public void writeScreenOpeningData(ServerPlayerEntity player, PacketByteBuf buf) {
            bufWriter.accept(buf);
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
