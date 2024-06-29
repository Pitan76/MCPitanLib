package net.pitan76.mcpitanlib.api.client.event;

import me.shedaniel.architectury.event.events.TooltipEvent;
import net.pitan76.mcpitanlib.api.client.event.listener.ItemTooltipListener;

public class ItemTooltipRegistry {
    public static void registerItemTooltip(ItemTooltipListener listener) {
        TooltipEvent.ITEM.register(listener::onTooltip);
    }
}
