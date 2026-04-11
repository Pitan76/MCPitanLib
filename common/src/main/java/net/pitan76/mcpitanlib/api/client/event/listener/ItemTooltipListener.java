package net.pitan76.mcpitanlib.api.client.event.listener;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.network.chat.Component;

import java.util.List;

@FunctionalInterface
public interface ItemTooltipListener {
    void onTooltip(ItemTooltipContext context);

    default void onTooltip(ItemStack stack, List<Component> texts, Item.TooltipContext tooltipContext, TooltipFlag type) {
        onTooltip(new ItemTooltipContext(stack, texts, tooltipContext, type));
    }
}