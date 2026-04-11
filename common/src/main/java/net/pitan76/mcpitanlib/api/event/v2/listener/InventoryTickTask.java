package net.pitan76.mcpitanlib.api.event.v2.listener;

import net.pitan76.mcpitanlib.api.event.item.InventoryTickEvent;

@FunctionalInterface
public interface InventoryTickTask {
    void inventoryTick(InventoryTickEvent event);
}