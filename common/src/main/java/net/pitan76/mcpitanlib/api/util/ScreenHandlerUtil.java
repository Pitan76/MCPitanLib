package net.pitan76.mcpitanlib.api.util;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.Slot;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.ItemStack;
import net.pitan76.mcpitanlib.api.network.PacketByteUtil;
import net.pitan76.mcpitanlib.core.mc261.ExtendedMenuProvider;
import net.pitan76.mcpitanlib.core.mc261.MenuOpener;
import org.jspecify.annotations.Nullable;

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
        MenuOpener.openExtendedMenu(player, new ExtendedMenuProvider<>() {
            @Override
            public @Nullable AbstractContainerMenu createMenu(int containerId, Inventory inventory, Player player) {
                return provider.createMenu(containerId, inventory, player);
            }

            @Override
            public Component getDisplayName() {
                return provider.getDisplayName();
            }

            @Override
            public Object getScreenOpeningData(ServerPlayer player) {
                FriendlyByteBuf buf = PacketByteUtil.create();
                bufWriter.accept(buf);
                return buf;
            }
        });
    }

    public static void openExtendedMenu(ServerPlayer player, ExtendedMenuProvider provider) {
        MenuOpener.openExtendedMenu(player, provider);
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

    public static ItemStack getCursorStack(AbstractContainerMenu screenHandler) {
        return screenHandler.getCarried();
    }

    public static net.pitan76.mcpitanlib.midohra.item.ItemStack getCursorStackM(AbstractContainerMenu screenHandler) {
        return net.pitan76.mcpitanlib.midohra.item.ItemStack.of(getCursorStack(screenHandler));
    }

    public static void setCursorStack(AbstractContainerMenu screenHandler, ItemStack stack) {
        screenHandler.setCarried(stack);
    }

    public static void setCursorStackM(AbstractContainerMenu screenHandler, net.pitan76.mcpitanlib.midohra.item.ItemStack stack) {
        setCursorStack(screenHandler, stack.toMinecraft());
    }
}
