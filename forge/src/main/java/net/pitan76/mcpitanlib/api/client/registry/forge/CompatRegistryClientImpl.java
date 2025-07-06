package net.pitan76.mcpitanlib.api.client.registry.forge;

import net.minecraft.block.Block;
import net.minecraft.client.color.block.BlockColorProvider;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.pitan76.mcpitanlib.MCPitanLib;

import java.util.HashMap;
import java.util.Map;

@Mod.EventBusSubscriber(modid = MCPitanLib.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
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
