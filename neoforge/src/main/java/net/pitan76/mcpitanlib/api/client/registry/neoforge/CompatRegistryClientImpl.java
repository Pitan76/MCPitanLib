package net.pitan76.mcpitanlib.api.client.registry.neoforge;

import dev.architectury.platform.hooks.EventBusesHooks;
import dev.architectury.registry.menu.MenuRegistry;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ingame.ScreenHandlerProvider;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import net.pitan76.mcpitanlib.api.client.registry.CompatRegistryClient;

import java.util.Objects;

public class CompatRegistryClientImpl {
    public static <H extends ScreenHandler, S extends Screen & ScreenHandlerProvider<H>> void registerScreen(String modId, ScreenHandlerType<? extends H> type, CompatRegistryClient.ScreenFactory<H, S> factory) {
        EventBusesHooks.whenAvailable(modId, (bus) -> {
            bus.addListener(RegisterMenuScreensEvent.class, (event) -> {
                Objects.requireNonNull(factory);
                event.register(type, factory::create);
            });
        });
    }
}
