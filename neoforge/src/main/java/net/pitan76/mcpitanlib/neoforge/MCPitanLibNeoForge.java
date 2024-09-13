package net.pitan76.mcpitanlib.neoforge;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.pitan76.mcpitanlib.MCPitanLib;
import net.pitan76.mcpitanlib.api.client.event.neoforge.WorldRenderRegistryImpl;

@Mod(MCPitanLib.MOD_ID)
public class MCPitanLibNeoForge {
    public MCPitanLibNeoForge(ModContainer modContainer) {
        IEventBus bus = modContainer.getEventBus();
        if (bus != null) {
            bus.addListener(WorldRenderRegistryImpl::renderOutlineEvent);
        }

        MCPitanLib.init();
    }
}