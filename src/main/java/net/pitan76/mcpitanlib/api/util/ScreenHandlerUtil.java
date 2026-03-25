package net.pitan76.mcpitanlib.api.util;

import net.fabricmc.fabric.api.menu.v1.ExtendedMenuProvider;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.Slot;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.core.NonNullList;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class ScreenHandlerUtil {
    public static NonNullList<Slot> getSlots(AbstractContainerMenu screenHandler) {
        return screenHandler.slots;
    }

    public static Slot getSlot(AbstractContainerMenu screenHandler, int index) {
        return screenHandler.getSlot(index);
    }

    public static List<MenuType<?>> getAllScreenHandlerTypes() {
        List<MenuType<?>> screenHandlerTypes = new ArrayList<>();
        for (MenuType<?> screenHandler : BuiltInRegistries.MENU) {
            screenHandlerTypes.add(screenHandler);
        }
        return screenHandlerTypes;
    }

    public static void openExtendedMenu(ServerPlayer player, MenuProvider provider, Consumer<FriendlyByteBuf> bufWriter) {
        // TODO: Fabric API does not support opening a menu with a bufWriter, so we need to find a way to do this.
//        player.openMenu(player, provider, bufWriter);
    }

    public static void openExtendedMenu(ServerPlayer player, ExtendedMenuProvider provider) {
        player.openMenu(provider);
    }

    public static void openMenu(ServerPlayer player, MenuProvider provider) {
        player.openMenu(provider);
    }

    public static int getRawId(MenuType<?> type) {
        return BuiltInRegistries.MENU.getId(type);
    }

    public static MenuType<?> fromIndex(int index) {
        return BuiltInRegistries.MENU.byId(index);
    }
}
