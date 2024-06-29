package net.pitan76.mcpitanlib.api.client.event.listener;

import net.minecraft.client.item.TooltipType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;

import java.util.List;

public class ItemTooltipContext {

    public ItemStack stack;
    public List<Text> texts;
    public Item.TooltipContext tooltipContext;

    @Deprecated
    public TooltipType type;

    public ItemTooltipContext(ItemStack stack, List<Text> texts, Item.TooltipContext tooltipContext, TooltipType type) {
        this.stack = stack;
        this.texts = texts;
        this.tooltipContext = tooltipContext;
        this.type = type;
    }

    public ItemStack getStack() {
        return stack;
    }

    public List<Text> getTexts() {
        return texts;
    }

    public Item.TooltipContext getTooltipContext() {
        return tooltipContext;
    }

    @Deprecated
    public TooltipType getType() {
        return type;
    }

    public void addTooltip(Text text) {
        texts.add(text);
    }

    public void addTooltip(List<Text> texts) {
        this.texts.addAll(texts);
    }

    public boolean isAdvanced() {
        return type.isAdvanced();
    }

    public boolean isCreative() {
        return type.isCreative();
    }
}
