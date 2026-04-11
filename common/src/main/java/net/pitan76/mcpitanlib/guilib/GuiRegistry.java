package net.pitan76.mcpitanlib.guilib;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.MenuAccess;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.pitan76.mcpitanlib.api.client.registry.CompatRegistryClient;
import net.pitan76.mcpitanlib.api.gui.ExtendedScreenHandlerTypeBuilder;
import net.pitan76.mcpitanlib.api.gui.SimpleScreenHandlerTypeBuilder;
import net.pitan76.mcpitanlib.api.registry.result.SupplierResult;
import net.pitan76.mcpitanlib.api.registry.v2.CompatRegistryV2;
import net.pitan76.mcpitanlib.api.util.CompatIdentifier;
import net.pitan76.mcpitanlib.guilib.api.container.SimpleContainerGui;
import net.pitan76.mcpitanlib.guilib.api.screen.SimpleContainerGuiScreen;

import java.util.function.Supplier;

public class GuiRegistry {
    public static <T extends AbstractContainerMenu> SupplierResult<MenuType<T>> register(CompatRegistryV2 registry, CompatIdentifier id, SimpleScreenHandlerTypeBuilder.Factory<T> factory) {
        return register(registry, id, new SimpleScreenHandlerTypeBuilder<>(factory));
    }

    public static <T extends AbstractContainerMenu> Supplier<MenuType<T>> register(CompatRegistryV2 registry, CompatIdentifier id, MenuType<T> type) {
        return registry.registerScreenHandlerTypeSavingGenerics(id, () -> type);
    }

    @Environment(EnvType.CLIENT)
    public static <T extends AbstractContainerMenu, U extends Screen & MenuAccess<T>> void register(String id, MenuType<T> type, CompatRegistryClient.ScreenFactory<T, U> factory) {
        CompatRegistryClient.registerScreen(id, type, factory);
    }

    @Environment(EnvType.CLIENT)
    public static <T extends SimpleContainerGui> void registerSimpleContainerGui(String id, MenuType<T> type) {
        CompatRegistryClient.registerScreen(id, type, SimpleContainerGuiScreen::new);
    }

    public static <T extends AbstractContainerMenu> SupplierResult<MenuType<T>> register(CompatRegistryV2 registry, CompatIdentifier id, SimpleScreenHandlerTypeBuilder<T> builder) {
        return registry.registerScreenHandlerType(id, builder);
    }

    public static <T extends AbstractContainerMenu> SupplierResult<MenuType<T>> register(CompatRegistryV2 registry, CompatIdentifier id, ExtendedScreenHandlerTypeBuilder<T> builder) {
        return registry.registerScreenHandlerType(id, builder);
    }
}
