package net.pitan76.mcpitanlib.api.event.v2;

import net.pitan76.mcpitanlib.api.event.v2.listener.InventoryTickTask;

public class ItemEventRegistry {
    public static OrderedEvent<InventoryTickTask> INVENTORY_TICK = new OrderedEvent<>();
}
