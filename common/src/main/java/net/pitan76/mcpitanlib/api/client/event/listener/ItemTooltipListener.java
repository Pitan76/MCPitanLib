package net.pitan76.mcpitanlib.api.client.event.listener;

import net.minecraft.client.item.TooltipType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;

import java.util.List;

@FunctionalInterface
public interface ItemTooltipListener {
    void onTooltip(ItemTooltipContext context);

    default void onTooltip(ItemStack stack, List<Text> texts, Item.TooltipContext tooltipContext, TooltipType type) {
        onTooltip(new ItemTooltipContext(stack, texts, tooltipContext, type));
    }
}