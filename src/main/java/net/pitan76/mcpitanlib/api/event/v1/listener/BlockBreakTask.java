package net.pitan76.mcpitanlib.api.event.v1.listener;

import net.pitan76.mcpitanlib.api.event.block.BlockBreakEvent;
import net.pitan76.mcpitanlib.api.event.block.result.BlockBreakResult;

@FunctionalInterface
public interface BlockBreakTask {
    BlockBreakResult onBreak(BlockBreakEvent event);
}