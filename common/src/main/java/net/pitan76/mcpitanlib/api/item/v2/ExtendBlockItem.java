package net.pitan76.mcpitanlib.api.item.v2;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.pitan76.mcpitanlib.api.event.item.ItemAppendTooltipEvent;
import net.pitan76.mcpitanlib.api.event.item.ItemUseOnBlockEvent;
import net.pitan76.mcpitanlib.api.item.CompatibleItemSettings;
import net.pitan76.mcpitanlib.api.item.ExtendItemProvider;
import net.pitan76.mcpitanlib.mixin.ItemUsageContextMixin;

import java.util.List;

public class ExtendBlockItem extends BlockItem implements ExtendItemProvider {
    public ExtendBlockItem(Block block, Settings settings) {
        super(block, settings);
    }

    public ExtendBlockItem(Block block, CompatibleItemSettings settings) {
        this(block, settings.build());
    }

    @Deprecated
    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        ItemUsageContextMixin contextAccessor = (ItemUsageContextMixin) context;
        return onRightClickOnBlock(new ItemUseOnBlockEvent(context.getPlayer(), context.getHand(), contextAccessor.getHit()));
    }

    @Deprecated
    @Override
    public ActionResult onRightClickOnBlock(ItemUseOnBlockEvent event, Options options) {
        return ExtendItemProvider.super.onRightClickOnBlock(event, options);
    }

    public ActionResult onRightClickOnBlock(ItemUseOnBlockEvent event) {
        return super.useOnBlock(event.toIUC());
    }

    @Deprecated
    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        appendTooltip(new ItemAppendTooltipEvent(stack, null, tooltip, type, context));
    }

    @Deprecated
    @Override
    public void appendTooltip(ItemAppendTooltipEvent event, Options options) {
        ExtendItemProvider.super.appendTooltip(event, options);
    }

    public void appendTooltip(ItemAppendTooltipEvent event) {
        super.appendTooltip(event.getStack(), event.getContext(), event.getTooltip(), event.type);
    }

    @Override
    public Block getBlock() {
        return super.getBlock();
    }
}
