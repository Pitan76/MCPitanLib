package net.pitan76.mcpitanlib.api.block;

import net.minecraft.block.Block;
import net.minecraft.component.type.TooltipDisplayComponent;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.pitan76.mcpitanlib.api.event.item.ItemAppendTooltipEvent;

import java.util.function.Consumer;

@Deprecated
public class BlockItemByExtendBlock1215 extends BlockItem {
    private ExtendBlock block;
    private ExtendBlockProvider provider;

    public BlockItemByExtendBlock1215(ExtendBlock block, Settings settings) {
        super(block, settings);
        this.block = block;
    }

    public BlockItemByExtendBlock1215(ExtendBlockProvider provider, Settings settings) {
        super((Block) provider, settings);
        this.provider = provider;
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, TooltipDisplayComponent displayComponent, Consumer<Text> textConsumer, TooltipType type) {
        if (block != null)
            block.appendTooltip(new ItemAppendTooltipEvent(stack, context, displayComponent, textConsumer, type));

        if (provider != null)
            provider.appendTooltip(new ItemAppendTooltipEvent(stack, context, displayComponent, textConsumer, type), new ExtendBlockProvider.Options());
    }
}
