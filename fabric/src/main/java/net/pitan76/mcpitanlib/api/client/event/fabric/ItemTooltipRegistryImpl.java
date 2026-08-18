package net.pitan76.mcpitanlib.api.client.event.fabric;

import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.pitan76.mcpitanlib.api.client.event.listener.ItemTooltipContext;
import net.pitan76.mcpitanlib.api.client.event.listener.ItemTooltipListener;

public class ItemTooltipRegistryImpl {
    public static void registerItemTooltip(ItemTooltipListener listener) {
        ItemTooltipCallback.EVENT.register((stack, tooltipContext, texts)
                -> listener.onTooltip(new ItemTooltipContext(stack, texts, tooltipContext)));
    }
}
