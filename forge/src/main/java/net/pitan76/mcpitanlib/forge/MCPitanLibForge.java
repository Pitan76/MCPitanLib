package net.pitan76.mcpitanlib.forge;

import dev.architectury.platform.forge.EventBuses;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.pitan76.mcpitanlib.MCPitanLib;
import net.pitan76.mcpitanlib.api.network.forge.ForgeNetworkRegistry;
import net.pitan76.mcpitanlib.api.potion.BrewingRecipeUtil;

@Mod(MCPitanLib.MOD_ID)
@SuppressWarnings("deprecation")
public class MCPitanLibForge {
    public MCPitanLibForge() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        EventBuses.registerModEventBus(MCPitanLib.MOD_ID, bus);

        ForgeNetworkRegistry.init();
        MCPitanLib.init();

        bus.addListener((FMLCommonSetupEvent event) -> event.enqueueWork(BrewingRecipeUtil::executeDeferredRecipes));
    }
}
