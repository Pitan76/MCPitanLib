package net.pitan76.mcpitanlib.api.client.registry.fabric;

import dev.architectury.registry.client.gui.MenuScreenRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.minecraft.block.Block;
import net.minecraft.client.color.block.BlockColorProvider;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ingame.ScreenHandlerProvider;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;
import net.pitan76.mcpitanlib.api.client.registry.CompatRegistryClient;

public class CompatRegistryClientImpl {
    public static <H extends ScreenHandler, S extends Screen & ScreenHandlerProvider<H>> void registerScreen(String modId, ScreenHandlerType<? extends H> type, CompatRegistryClient.ScreenFactory<H, S> factory) {
        MenuScreenRegistry.registerScreenFactory(type, factory::create);
    }

    public static void registerColorProviderBlock(BlockColorProvider provider, Block... blocks) {
        if (blocks == null || blocks.length == 0) {
            ColorProviderRegistry.BLOCK.register(provider);
        } else {
            ColorProviderRegistry.BLOCK.register(provider, blocks);
        }
    }
}
