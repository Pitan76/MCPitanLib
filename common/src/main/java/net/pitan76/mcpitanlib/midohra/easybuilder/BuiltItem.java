package net.pitan76.mcpitanlib.midohra.easybuilder;

import net.pitan76.mcpitanlib.api.event.item.ItemAppendTooltipEvent;
import net.pitan76.mcpitanlib.api.event.item.ItemUseEvent;
import net.pitan76.mcpitanlib.api.event.item.ItemUseOnBlockEvent;
import net.pitan76.mcpitanlib.api.event.item.ItemUseOnEntityEvent;
import net.pitan76.mcpitanlib.api.item.v2.CompatItem;
import net.pitan76.mcpitanlib.api.item.v2.CompatibleItemSettings;
import net.pitan76.mcpitanlib.api.util.CompatActionResult;
import net.pitan76.mcpitanlib.api.util.CompatIdentifier;
import net.pitan76.mcpitanlib.api.util.StackActionResult;

import java.util.function.Consumer;
import java.util.function.Function;

public class BuiltItem extends CompatItem {

    protected Function<ItemUseEvent, StackActionResult> onRightClick;
    protected Function<ItemUseOnBlockEvent, CompatActionResult> onRightClickOnBlock;
    protected Function<ItemUseOnEntityEvent, CompatActionResult> onRightClickOnEntity;
    protected Consumer<ItemAppendTooltipEvent> onAppendTooltip;

    protected BuiltItem(CompatibleItemSettings settings) {
        super(settings);
    }

    public BuiltItem(ItemBuilder builder) {
        this(builder.settingsBuilder.build());

        this.onRightClick = builder.onRightClick;
        this.onRightClickOnBlock = builder.onRightClickOnBlock;
        this.onAppendTooltip = builder.onAppendTooltip;
    }

    public BuiltItem(ItemBuilder builder, CompatIdentifier id) {
        this(builder.settingsBuilder.build(id));

        this.onRightClick = builder.onRightClick;
        this.onRightClickOnBlock = builder.onRightClickOnBlock;
        this.onAppendTooltip = builder.onAppendTooltip;
    }

    @Override
    public StackActionResult onRightClick(ItemUseEvent e) {
        if (onRightClick == null)
            return e.pass();

        return onRightClick.apply(e);
    }

    @Override
    public CompatActionResult onRightClickOnBlock(ItemUseOnBlockEvent e) {
        if (onRightClickOnBlock == null)
            return e.pass();

        return onRightClickOnBlock.apply(e);
    }

    @Override
    public CompatActionResult onRightClickOnEntity(ItemUseOnEntityEvent e) {
        if (onRightClickOnEntity == null)
            return e.pass();

        return onRightClickOnEntity.apply(e);
    }

    @Override
    public void appendTooltip(ItemAppendTooltipEvent e) {
        if (onAppendTooltip != null)
            onAppendTooltip.accept(e);
    }
}
