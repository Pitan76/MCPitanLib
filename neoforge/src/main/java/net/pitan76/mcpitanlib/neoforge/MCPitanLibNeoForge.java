package net.pitan76.mcpitanlib.neoforge;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.pitan76.mcpitanlib.MCPitanLib;
import net.pitan76.mcpitanlib.api.potion.BrewingRecipeUtil;

@Mod(MCPitanLib.MOD_ID)
public class MCPitanLibNeoForge {
    public MCPitanLibNeoForge(ModContainer modContainer) {
        IEventBus bus = modContainer.getEventBus();

        MCPitanLib.init();

        bus.addListener((FMLCommonSetupEvent event) -> event.enqueueWork(BrewingRecipeUtil::executeDeferredRecipes));
    }
}
