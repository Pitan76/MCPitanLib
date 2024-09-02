package net.pitan76.mcpitanlib.api.client.registry.forge;

import dev.architectury.registry.menu.MenuRegistry;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ingame.ScreenHandlerProvider;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;
import net.pitan76.mcpitanlib.api.client.registry.CompatRegistryClient;

public class CompatRegistryClientImpl {
    public static <H extends ScreenHandler, S extends Screen & ScreenHandlerProvider<H>> void registerScreen(String modId, ScreenHandlerType<? extends H> type, CompatRegistryClient.ScreenFactory<H, S> factory) {
        MenuRegistry.registerScreenFactory(type, factory::create);
    }
}
