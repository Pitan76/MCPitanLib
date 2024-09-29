package net.pitan76.mcpitanlib.neoforge;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.pitan76.mcpitanlib.MCPitanLib;
import net.pitan76.mcpitanlib.api.client.event.neoforge.WorldRenderRegistryImpl;
import net.pitan76.mcpitanlib.api.util.PlatformUtil;

@Mod(MCPitanLib.MOD_ID)
public class MCPitanLibNeoForge {
    public MCPitanLibNeoForge(ModContainer modContainer) {
        IEventBus bus = modContainer.getEventBus();

        if (PlatformUtil.isClient()) {
            bus.addListener(WorldRenderRegistryImpl::renderOutlineEventBlock);
            bus.addListener(WorldRenderRegistryImpl::renderOutlineEvent);
            bus.addListener(WorldRenderRegistryImpl::renderLevelStageEvent);
        }

        MCPitanLib.init();
    }
}