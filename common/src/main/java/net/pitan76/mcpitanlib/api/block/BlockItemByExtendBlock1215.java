package net.pitan76.mcpitanlib.api.block;

import net.minecraft.world.item.Item.Properties;
import net.minecraft.world.item.Item.TooltipContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.network.chat.Component;
import net.pitan76.mcpitanlib.api.event.item.ItemAppendTooltipEvent;

import java.util.function.Consumer;

@Deprecated
public class BlockItemByExtendBlock1215 extends BlockItem {
    private ExtendBlock block;
    private ExtendBlockProvider provider;

    public BlockItemByExtendBlock1215(ExtendBlock block, Properties settings) {
        super(block, settings);
        this.block = block;
    }

    public BlockItemByExtendBlock1215(ExtendBlockProvider provider, Properties settings) {
        super((Block) provider, settings);
        this.provider = provider;
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, TooltipDisplay displayComponent, Consumer<Component> textConsumer, TooltipFlag type) {
        if (block != null)
            block.appendTooltip(new ItemAppendTooltipEvent(stack, context, displayComponent, textConsumer, type));

        if (provider != null)
            provider.appendTooltip(new ItemAppendTooltipEvent(stack, context, displayComponent, textConsumer, type), new ExtendBlockProvider.Options());
    }
}
