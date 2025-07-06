package net.pitan76.mcpitanlib.api.client.registry.neoforge;

import net.minecraft.block.Block;
import net.minecraft.client.color.block.BlockColorProvider;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;

import java.util.HashMap;
import java.util.Map;

public class CompatRegistryClientImpl {

    public static Map<BlockColorProvider, Block[]> blockColorProviders = new HashMap<>();

    public static void registerColorProviderBlock(BlockColorProvider provider, Block... blocks) {
        blockColorProviders.put(provider, blocks);
    }

    @SubscribeEvent
    public static void registerBlockColors(RegisterColorHandlersEvent.Block event){
        if (blockColorProviders.isEmpty()) return;

        for (Map.Entry<BlockColorProvider, Block[]> entry : blockColorProviders.entrySet()) {
            BlockColorProvider provider = entry.getKey();
            Block[] blocks = entry.getValue();

            if (blocks == null || blocks.length == 0) {
                event.register(provider);
            } else {
                event.register(provider, blocks);
            }
        }
    }
}
