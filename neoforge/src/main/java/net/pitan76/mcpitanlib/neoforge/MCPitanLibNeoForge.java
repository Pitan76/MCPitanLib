package net.pitan76.mcpitanlib.neoforge;

import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.pitan76.mcpitanlib.MCPitanLib;

@Mod(MCPitanLib.MOD_ID)
public class MCPitanLibNeoForge {
    public MCPitanLibNeoForge(ModContainer modContainer) {
//        IEventBus bus = modContainer.getEventBus();
//
//        if (PlatformUtil.isClient()) {
//            IEventBus nfBus = NeoForge.EVENT_BUS;
//            // TODO: re-implement outline rendering event
////            nfBus.addListener(WorldRenderRegistryImpl::renderOutlineEventBlock);
////            nfBus.addListener(WorldRenderRegistryImpl::renderOutlineEvent);
////            nfBus.addListener(WorldRenderRegistryImpl::renderLevelStageEvent);
//        }

        MCPitanLib.init();
    }
}