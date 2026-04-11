package net.pitan76.mcpitanlib.api.item.v2;

import net.minecraft.world.item.Item.Properties;
import net.minecraft.world.item.Item.TooltipContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.pitan76.mcpitanlib.api.event.item.ItemAppendTooltipEvent;
import net.pitan76.mcpitanlib.api.event.item.ItemUseOnBlockEvent;
import net.pitan76.mcpitanlib.api.util.CompatActionResult;
import net.pitan76.mcpitanlib.mixin.UseOnContextMixin;

import java.util.List;
import java.util.function.Consumer;

public class ExtendBlockItem extends BlockItem implements CompatItemProvider {

    public CompatibleItemSettings settings;

    @Deprecated
    public ExtendBlockItem(Block block, Properties settings) {
        super(block, settings);
    }

    public ExtendBlockItem(Block block, CompatibleItemSettings settings) {
        this(block, settings.build());
        this.settings = settings;
    }

    @Override
    public CompatibleItemSettings getCompatSettings() {
        return settings;
    }

    @Deprecated
    @Override
    public InteractionResult useOn(UseOnContext context) {
        UseOnContextMixin contextAccessor = (UseOnContextMixin) context;
        return onRightClickOnBlock(new ItemUseOnBlockEvent(context.getPlayer(), context.getHand(), contextAccessor.getHitResult())).toActionResult();
    }

    @Deprecated
    @Override
    public CompatActionResult onRightClickOnBlock(ItemUseOnBlockEvent event, Options options) {
        return CompatItemProvider.super.onRightClickOnBlock(event, options);
    }

    public CompatActionResult onRightClickOnBlock(ItemUseOnBlockEvent event) {
        return CompatActionResult.create(super.useOn(event.toIUC()));
    }

    @Deprecated
    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, TooltipDisplay displayComponent, Consumer<Component> textConsumer, TooltipFlag type) {
        appendTooltip(new ItemAppendTooltipEvent(stack, context, displayComponent, textConsumer, type));
    }

    @Deprecated
    @Override
    public void appendTooltip(ItemAppendTooltipEvent event, Options options) {
        CompatItemProvider.super.appendTooltip(event, options);
    }

    public void appendTooltip(ItemAppendTooltipEvent event) {
        super.appendHoverText(event.getStack(), event.getContext(), event.displayComponent, event.textConsumer, event.type);
    }

    @Override
    public Block getBlock() {
        return super.getBlock();
    }
}
