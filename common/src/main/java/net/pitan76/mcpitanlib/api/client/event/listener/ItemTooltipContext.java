package net.pitan76.mcpitanlib.api.client.event.listener;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.network.chat.Component;
import net.pitan76.mcpitanlib.api.text.TextComponent;
import net.pitan76.mcpitanlib.api.util.TextUtil;

import java.util.List;

public class ItemTooltipContext {

    public ItemStack stack;
    public List<Component> texts;
    public Item.TooltipContext tooltipContext;

    @Deprecated
    public TooltipFlag type;

    public ItemTooltipContext(ItemStack stack, List<Component> texts, Item.TooltipContext tooltipContext, TooltipFlag type) {
        this.stack = stack;
        this.texts = texts;
        this.tooltipContext = tooltipContext;
        this.type = type;
    }

    public ItemStack getStack() {
        return stack;
    }

    public List<Component> getTexts() {
        return texts;
    }

    public Item.TooltipContext getTooltipContext() {
        return tooltipContext;
    }

    @Deprecated
    public TooltipFlag getType() {
        return type;
    }

    public void addTooltip(Component text) {
        texts.add(text);
    }

    public void addTooltip(List<Component> texts) {
        this.texts.addAll(texts);
    }

    public boolean isAdvanced() {
        return type.isAdvanced();
    }

    public boolean isCreative() {
        return type.isCreative();
    }

    public void addTooltip(TextComponent textComponent) {
        addTooltip(textComponent.getText());
    }

    public void addTooltip(String text) {
        addTooltip(TextUtil.literal(text));
    }
}
