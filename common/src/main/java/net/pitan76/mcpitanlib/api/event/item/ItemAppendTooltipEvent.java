package net.pitan76.mcpitanlib.api.event.item;

import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import net.pitan76.mcpitanlib.api.event.BaseEvent;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ItemAppendTooltipEvent extends BaseEvent {
    public ItemStack stack;
    public World world;
    public List<Text> tooltip;

    public TooltipType type;
    public Item.TooltipContext context;

    public ItemAppendTooltipEvent(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipType type, Item.TooltipContext context) {
        this.stack = stack;
        this.world = world;
        this.tooltip = tooltip;
        this.type = type;
        this.context = context;
    }

    public ItemStack getStack() {
        return stack;
    }

    public World getWorld() {
        return world;
    }

    public List<Text> getTooltip() {
        return tooltip;
    }

    public Item.TooltipContext getContext() {
        return context;
    }

    public void addTooltip(Text text) {
        tooltip.add(text);
    }

    public void addTooltip(List<Text> texts) {
        tooltip.addAll(texts);
    }

    public boolean removeTooltip(Text text) {
        return tooltip.remove(text);
    }

    public boolean isCreative() {
        return type.isCreative();
    }

    public boolean isAdvanced() {
        return type.isAdvanced();
    }
}
