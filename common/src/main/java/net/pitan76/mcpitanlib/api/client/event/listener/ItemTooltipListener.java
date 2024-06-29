package net.pitan76.mcpitanlib.api.client.event.listener;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;

import java.util.List;

@FunctionalInterface
public interface ItemTooltipListener {
    void onTooltip(ItemTooltipContext context);

    default void onTooltip(ItemStack stack, List<Text> texts, TooltipContext tooltipContext) {
        onTooltip(new ItemTooltipContext(stack, texts, tooltipContext));
    }
}