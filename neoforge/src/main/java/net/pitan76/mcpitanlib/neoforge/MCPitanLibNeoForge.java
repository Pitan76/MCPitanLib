package net.pitan76.mcpitanlib.neoforge;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForge;
import net.pitan76.mcpitanlib.MCPitanLib;
import net.pitan76.mcpitanlib.api.client.event.neoforge.WorldRenderRegistryImpl;
import net.pitan76.mcpitanlib.api.util.PlatformUtil;

@Mod(MCPitanLib.MOD_ID)
public class MCPitanLibNeoForge {
    public MCPitanLibNeoForge(ModContainer modContainer) {
        IEventBus bus = modContainer.getEventBus();

        if (PlatformUtil.isClient()) {
            IEventBus nfBus = NeoForge.EVENT_BUS;
            nfBus.addListener(WorldRenderRegistryImpl::renderOutlineEventBlock);
            nfBus.addListener(WorldRenderRegistryImpl::renderOutlineEvent);
            nfBus.addListener(WorldRenderRegistryImpl::renderLevelStageEvent);
        }

        MCPitanLib.init();
    }
}