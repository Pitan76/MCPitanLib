package net.pitan76.mcpitanlib.api.client.event.neoforge;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;
import net.pitan76.mcpitanlib.api.client.event.listener.ItemTooltipListener;

import java.util.ArrayList;
import java.util.List;

public class ItemTooltipRegistryImpl {
    private static final List<ItemTooltipListener> listeners = new ArrayList<>();

    public static void registerItemTooltip(ItemTooltipListener listener) {
        listeners.add(listener);
    }

    @SubscribeEvent
    public static void onItemTooltip(ItemTooltipEvent event) {
        for (ItemTooltipListener listener : listeners) {
            listener.onTooltip(event.getItemStack(), event.getToolTip(), event.getContext(), event.getFlags());
        }
    }
}
