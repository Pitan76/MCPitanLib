package net.pitan76.mcpitanlib.api.event.item;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.pitan76.mcpitanlib.api.event.BaseEvent;
import net.pitan76.mcpitanlib.api.registry.CompatRegistryLookup;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ItemAppendTooltipEvent extends BaseEvent {
    public ItemStack stack;
    public World world;
    public BlockView blockView;
    public List<Text> tooltip;
    public TooltipContext context;

    public ItemAppendTooltipEvent(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        this.stack = stack;
        this.world = world;
        this.tooltip = tooltip;
        this.context = context;
    }

    public ItemAppendTooltipEvent(ItemStack stack, @Nullable BlockView world, List<Text> tooltip, TooltipContext context) {
        this.stack = stack;
        this.blockView = world;
        this.tooltip = tooltip;
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

    public TooltipContext getContext() {
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
        return context.isAdvanced();
    }

    public boolean isAdvanced() {
        return context.isAdvanced();
    }

    public CompatRegistryLookup getRegistryLookup() {
        if (world == null) return new CompatRegistryLookup();
        return new CompatRegistryLookup(world.getRegistryManager());
    }
}
