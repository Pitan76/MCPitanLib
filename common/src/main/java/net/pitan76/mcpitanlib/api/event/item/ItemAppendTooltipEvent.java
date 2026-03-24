package net.pitan76.mcpitanlib.api.event.item;

import net.minecraft.component.type.TooltipDisplayComponent;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import net.pitan76.mcpitanlib.api.event.BaseEvent;
import net.pitan76.mcpitanlib.api.registry.CompatRegistryLookup;
import net.pitan76.mcpitanlib.api.text.TextComponent;
import net.pitan76.mcpitanlib.api.util.RegistryLookupUtil;
import net.pitan76.mcpitanlib.api.util.TextUtil;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class ItemAppendTooltipEvent extends BaseEvent {
    public ItemStack stack;

    @Deprecated
    public World world;

    @Deprecated
    public List<Text> tooltip;

    public TooltipType type;
    public Item.TooltipContext context;

    public TooltipDisplayComponent displayComponent;
    public Consumer<Text> textConsumer;

    public ItemAppendTooltipEvent(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipType type, Item.TooltipContext context) {
        this.stack = stack;
        this.world = world;
        this.tooltip = tooltip;
        this.type = type;
        this.context = context;
    }

    public ItemAppendTooltipEvent(ItemStack stack, Item.TooltipContext context, TooltipDisplayComponent displayComponent, Consumer<Text> textConsumer, TooltipType type) {
        this.stack = stack;
        this.context = context;
        this.displayComponent = displayComponent;
        this.textConsumer = textConsumer;
        this.type = type;
    }

    public ItemStack getStack() {
        return stack;
    }

    public World getWorld() {
        return world;
    }

    public List<Text> getTooltip() {
        return new ArrayList<>();
    }

    public Item.TooltipContext getContext() {
        return context;
    }

    public void addTooltip(Text text) {
        textConsumer.accept(text);
    }

    public void addTooltip(List<Text> texts) {
        for (Text text : texts) {
            addTooltip(text);
        }
    }

    public boolean removeTooltip(Text text) {
        return false;
    }

    public boolean isCreative() {
        return type.isCreative();
    }

    public boolean isAdvanced() {
        return type.isAdvanced();
    }

    public CompatRegistryLookup getRegistryLookup() {
        return RegistryLookupUtil.getRegistryLookup(this);
    }

    public void addTooltip(TextComponent textComponent) {
        addTooltip(textComponent.getText());
    }

    public void addTooltip(String text) {
        addTooltip(TextUtil.literal(text));
    }
}
