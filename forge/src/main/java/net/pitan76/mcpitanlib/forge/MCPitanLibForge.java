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
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        ForgeNetworkRegistry.init();
        MCPitanLib.init();

        bus.addListener((FMLCommonSetupEvent event) -> event.enqueueWork(BrewingRecipeUtil::executeDeferredRecipes));
    }
}