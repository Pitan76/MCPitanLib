package net.pitan76.mcpitanlib.forge;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.pitan76.mcpitanlib.MCPitanLib;
import net.pitan76.mcpitanlib.api.network.forge.ForgeNetworkRegistry;
import net.pitan76.mcpitanlib.api.potion.BrewingRecipeUtil;

@Mod(MCPitanLib.MOD_ID)
public class MCPitanLibForge {
    public MCPitanLibForge() {
        // All event handlers are registered automatically by @EventBusSubscriber.
        // Do not register them manually here: client-only classes (e.g. CompatRegistryClientImpl)
        // would then be loaded on dedicated servers and crash with NoClassDefFoundError.
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        ForgeNetworkRegistry.init();
        MCPitanLib.init();

        bus.addListener((FMLCommonSetupEvent event) -> event.enqueueWork(BrewingRecipeUtil::executeDeferredRecipes));
    }
}
