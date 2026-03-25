package net.pitan76.mcpitanlib.api.event.item;

import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.Level;
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
    public Level world;

    @Deprecated
    public List<Component> tooltip;

    public TooltipFlag type;
    public Item.TooltipContext context;

    public TooltipDisplay displayComponent;
    public Consumer<Component> textConsumer;

    public ItemAppendTooltipEvent(ItemStack stack, @Nullable Level world, List<Component> tooltip, TooltipFlag type, Item.TooltipContext context) {
        this.stack = stack;
        this.world = world;
        this.tooltip = tooltip;
        this.type = type;
        this.context = context;
    }

    public ItemAppendTooltipEvent(ItemStack stack, Item.TooltipContext context, TooltipDisplay displayComponent, Consumer<Component> textConsumer, TooltipFlag type) {
        this.stack = stack;
        this.context = context;
        this.displayComponent = displayComponent;
        this.textConsumer = textConsumer;
        this.type = type;
    }

    public ItemStack getStack() {
        return stack;
    }

    public Level getWorld() {
        return world;
    }

    public List<Component> getTooltip() {
        return new ArrayList<>();
    }

    public Item.TooltipContext getContext() {
        return context;
    }

    public void addTooltip(Component text) {
        textConsumer.accept(text);
    }

    public void addTooltip(List<Component> texts) {
        for (Component text : texts) {
            addTooltip(text);
        }
    }

    public boolean removeTooltip(Component text) {
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
