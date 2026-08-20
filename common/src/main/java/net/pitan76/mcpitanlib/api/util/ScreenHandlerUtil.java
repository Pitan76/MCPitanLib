package net.pitan76.mcpitanlib.api.util;

import net.pitan76.mcpitanlib.core.screen.ExtendedMenuProvider;
import net.pitan76.mcpitanlib.core.screen.MenuOpener;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.screen.slot.Slot;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.registry.Registry;
import net.pitan76.mcpitanlib.api.util.client.ClientUtil;
import net.pitan76.mcpitanlib.midohra.server.MCServer;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class ScreenHandlerUtil {
    public static DefaultedList<Slot> getSlots(ScreenHandler screenHandler) {
        DefaultedList<Slot> slots = DefaultedList.of();
        slots.addAll(screenHandler.slots);
        return slots;
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
        MenuOpener.openExtendedMenu(player, provider, bufWriter);
    }

    public static void openExtendedMenu(ServerPlayerEntity player, ExtendedMenuProvider provider) {
        MenuOpener.openExtendedMenu(player, provider);
    }

    public static void openMenu(ServerPlayerEntity player, NamedScreenHandlerFactory provider) {
        MenuOpener.openMenu(player, provider);
    }

    public static int getRawId(ScreenHandlerType<?> type) {
        return Registry.SCREEN_HANDLER.getRawId(type);
    }

    public static ScreenHandlerType<?> fromIndex(int index) {
        return Registry.SCREEN_HANDLER.get(index);
    }

    public static ItemStack getCursorStack(ScreenHandler screenHandler) {
        if (PlatformUtil.isClient()) {
            return ClientUtil.getClientPlayer().inventory.getCursorStack();
        }
        for (Slot slot : screenHandler.slots) {
            if (slot.inventory instanceof PlayerInventory) {
                PlayerInventory inventory = (PlayerInventory) slot.inventory;
                return inventory.getCursorStack();
            }
        }
        for (ItemStack tmp : screenHandler.getStacks()) {
            if (tmp.getHolder() instanceof ServerPlayerEntity) {
                ServerPlayerEntity player = (ServerPlayerEntity) tmp.getHolder();
                return player.inventory.getCursorStack();
            }
        }
        return ItemStack.EMPTY;
    }

    public static net.pitan76.mcpitanlib.midohra.item.ItemStack getCursorStackM(ScreenHandler screenHandler) {
        return net.pitan76.mcpitanlib.midohra.item.ItemStack.of(getCursorStack(screenHandler));
    }

    public static void setCursorStack(ScreenHandler screenHandler, ItemStack stack) {
        if (PlatformUtil.isClient()) {
            ClientUtil.getClientPlayer().inventory.setCursorStack(stack);
            return;
        }
        for (Slot slot : screenHandler.slots) {
            if (slot.inventory instanceof PlayerInventory) {
                PlayerInventory inventory = (PlayerInventory) slot.inventory;
                inventory.setCursorStack(stack);
                return;
            }
        }
        for (ItemStack tmp : screenHandler.getStacks()) {
            if (tmp.getHolder() instanceof ServerPlayerEntity) {
                ServerPlayerEntity player = (ServerPlayerEntity) tmp.getHolder();
                player.inventory.setCursorStack(stack);
                return;
            }
        }
    }

    public static void setCursorStackM(ScreenHandler screenHandler, net.pitan76.mcpitanlib.midohra.item.ItemStack stack) {
        setCursorStack(screenHandler, stack.toMinecraft());
    }
}
