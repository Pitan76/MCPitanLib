package net.pitan76.mcpitanlib.api.event.v1.listener;

import net.pitan76.mcpitanlib.api.event.block.BlockPlacedEvent;

@FunctionalInterface
public interface BlockPlacedTask {
    void onPlaced(BlockPlacedEvent event);
}