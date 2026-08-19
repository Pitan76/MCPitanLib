package net.pitan76.mcpitanlib.api.util;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.pitan76.mcpitanlib.api.network.PacketByteUtil;
import net.pitan76.mcpitanlib.core.screen.ExtendedMenuProvider;
import net.pitan76.mcpitanlib.core.screen.MenuOpener;
import org.jetbrains.annotations.Nullable;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.screen.slot.Slot;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.registry.Registry;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class ScreenHandlerUtil {
    public static DefaultedList<Slot> getSlots(ScreenHandler screenHandler) {
        return screenHandler.slots;
    }

    public static Slot getSlot(ScreenHandler screenHandler, int index) {
        return screenHandler.getSlot(index);
    }

    public static List<ScreenHandlerType<?>> getAllScreenHandlerTypes() {
        List<ScreenHandlerType<?>> screenHandlerTypes = new ArrayList<>();
        for (ScreenHandlerType<?> screenHandler : Registry.SCREEN_HANDLER) {
            screenHandlerTypes.add(screenHandler);
        }
        return screenHandlerTypes;
    }

    public static void openExtendedMenu(ServerPlayerEntity player, NamedScreenHandlerFactory provider, Consumer<PacketByteBuf> bufWriter) {
        MenuOpener.openExtendedMenu(player, new ExtendedMenuProvider() {
            @Nullable
            @Override
            public ScreenHandler createMenu(int syncId, PlayerInventory inventory, PlayerEntity player) {
                return provider.createMenu(syncId, inventory, player);
            }

            @Override
            public Text getDisplayName() {
                return provider.getDisplayName();
            }

            @Override
            public void writeScreenOpeningData(ServerPlayerEntity player, PacketByteBuf buf) {
                bufWriter.accept(buf);
            }
        });
    }

    public static void openExtendedMenu(ServerPlayerEntity player, ExtendedMenuProvider provider) {
        MenuOpener.openExtendedMenu(player, provider);
    }

    public static void openMenu(ServerPlayerEntity player, NamedScreenHandlerFactory provider) {
        player.openHandledScreen(provider);
    }

    public static int getRawId(ScreenHandlerType<?> type) {
        return Registry.SCREEN_HANDLER.getRawId(type);
    }

    public static ScreenHandlerType<?> fromIndex(int index) {
        return Registry.SCREEN_HANDLER.get(index);
    }

    public static ItemStack getCursorStack(ScreenHandler screenHandler) {
        return screenHandler.getCursorStack();
    }

    public static net.pitan76.mcpitanlib.midohra.item.ItemStack getCursorStackM(ScreenHandler screenHandler) {
        return net.pitan76.mcpitanlib.midohra.item.ItemStack.of(getCursorStack(screenHandler));
    }

    public static void setCursorStack(ScreenHandler screenHandler, ItemStack stack) {
        screenHandler.setCursorStack(stack);
    }

    public static void setCursorStackM(ScreenHandler screenHandler, net.pitan76.mcpitanlib.midohra.item.ItemStack stack) {
        setCursorStack(screenHandler, stack.toMinecraft());
    }
}
