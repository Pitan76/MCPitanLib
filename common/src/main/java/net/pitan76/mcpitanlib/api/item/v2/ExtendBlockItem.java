package net.pitan76.mcpitanlib.api.item.v2;

import net.minecraft.block.Block;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.world.World;
import net.pitan76.mcpitanlib.api.event.item.ItemAppendTooltipEvent;
import net.pitan76.mcpitanlib.api.event.item.ItemUseOnBlockEvent;
import net.pitan76.mcpitanlib.api.item.CompatibleItemSettings;
import net.pitan76.mcpitanlib.api.item.ExtendItemProvider;
import net.pitan76.mcpitanlib.mixin.ItemUsageContextMixin;
import org.jetbrains.annotations.Nullable;

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
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        appendTooltip(new ItemAppendTooltipEvent(stack, world, tooltip, context));
    }

    @Deprecated
    @Override
    public void appendTooltip(ItemAppendTooltipEvent event, Options options) {
        ExtendItemProvider.super.appendTooltip(event, options);
    }

    public void appendTooltip(ItemAppendTooltipEvent event) {
        super.appendTooltip(event.getStack(), event.getWorld(), event.getTooltip(), event.getContext());
    }

    @Override
    public Block getBlock() {
        return super.getBlock();
    }
}
