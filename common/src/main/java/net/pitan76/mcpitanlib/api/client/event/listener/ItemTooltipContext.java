package net.pitan76.mcpitanlib.api.client.event.listener;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;

import java.util.List;

public class ItemTooltipContext {

    public ItemStack stack;
    public List<Text> texts;
    public TooltipContext tooltipContext;

    public ItemTooltipContext(ItemStack stack, List<Text> texts, TooltipContext tooltipContext) {
        this.stack = stack;
        this.texts = texts;
        this.tooltipContext = tooltipContext;
    }

    public ItemStack getStack() {
        return stack;
    }

    public List<Text> getTexts() {
        return texts;
    }

    public TooltipContext getTooltipContext() {
        return tooltipContext;
    }

    public void addTooltip(Text text) {
        texts.add(text);
    }

    public void addTooltip(List<Text> texts) {
        this.texts.addAll(texts);
    }

    public boolean isAdvanced() {
        return tooltipContext.isAdvanced();
    }

    public boolean isCreative() {
        return !tooltipContext.isAdvanced();
    }
}
