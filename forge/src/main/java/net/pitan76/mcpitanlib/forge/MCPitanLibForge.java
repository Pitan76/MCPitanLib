package net.pitan76.mcpitanlib.forge;

import dev.architectury.platform.forge.EventBuses;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.pitan76.mcpitanlib.MCPitanLib;
import net.pitan76.mcpitanlib.api.client.event.forge.WorldRenderRegistryImpl;

@Mod(MCPitanLib.MOD_ID)
public class MCPitanLibForge {
    public MCPitanLibForge() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        EventBuses.registerModEventBus(MCPitanLib.MOD_ID, bus);

        bus.addListener(WorldRenderRegistryImpl::renderOutlineEvent);

        MCPitanLib.init();
    }
}