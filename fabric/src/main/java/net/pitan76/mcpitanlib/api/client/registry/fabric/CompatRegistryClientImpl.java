package net.pitan76.mcpitanlib.api.client.registry.fabric;

import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.minecraft.block.Block;
import net.minecraft.client.color.block.BlockColorProvider;

public class CompatRegistryClientImpl {

    public static void registerColorProviderBlock(BlockColorProvider provider, Block... blocks) {
        if (blocks == null || blocks.length == 0) {
            ColorProviderRegistry.BLOCK.register(provider);
        } else {
            ColorProviderRegistry.BLOCK.register(provider, blocks);
        }
    }
}
