package net.pitan76.mcpitanlib.api.client.event;

import dev.architectury.event.events.client.ClientTooltipEvent;
import net.pitan76.mcpitanlib.api.client.event.listener.ItemTooltipListener;

public class ItemTooltipRegistry {
    public static void registerItemTooltip(ItemTooltipListener listener) {
        ClientTooltipEvent.ITEM.register(listener::onTooltip);
    }
}
