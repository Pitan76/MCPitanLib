package net.pitan76.mcpitanlib.midohra.easybuilder.built;

import net.pitan76.mcpitanlib.api.event.item.*;
import net.pitan76.mcpitanlib.api.item.v2.CompatItem;
import net.pitan76.mcpitanlib.api.item.v2.CompatibleItemSettings;
import net.pitan76.mcpitanlib.api.util.CompatActionResult;
import net.pitan76.mcpitanlib.api.util.CompatIdentifier;
import net.pitan76.mcpitanlib.api.util.StackActionResult;
import net.pitan76.mcpitanlib.midohra.easybuilder.ItemBuilder;

import java.util.function.Consumer;
import java.util.function.Function;

public class BuiltItem extends CompatItem {

    protected Function<ItemUseEvent, StackActionResult> onRightClick;
    protected Function<ItemUseOnBlockEvent, CompatActionResult> onRightClickOnBlock;
    protected Function<ItemUseOnEntityEvent, CompatActionResult> onRightClickOnEntity;
    protected Consumer<ItemAppendTooltipEvent> onAppendTooltip;
    protected Function<ItemBarColorArgs, Integer> onItemBarColor;
    protected Function<ItemBarStepArgs, Integer> onItemBarStep;

    protected BuiltItem(CompatibleItemSettings settings) {
        super(settings);
    }

    public BuiltItem(ItemBuilder builder) {
        this(builder.settingsBuilder.build());
        init(builder);
    }

    public BuiltItem(ItemBuilder builder, CompatIdentifier id) {
        this(builder.settingsBuilder.build(id));
        init(builder);
    }

    protected void init(ItemBuilder builder) {
        this.onRightClick = builder.onRightClick;
        this.onRightClickOnBlock = builder.onRightClickOnBlock;
        this.onRightClickOnEntity = builder.onRightClickOnEntity;
        this.onAppendTooltip = builder.onAppendTooltip;
        this.onItemBarColor = builder.onItemBarColor;
        this.onItemBarStep = builder.onItemBarStep;
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

    @Override
    public int getItemBarColor(ItemBarColorArgs args) {
        if (onItemBarColor != null)
            return onItemBarColor.apply(args);

        return super.getItemBarColor(args);
    }

    @Override
    public int getItemBarStep(ItemBarStepArgs args) {
        if (onItemBarStep != null)
            return onItemBarStep.apply(args);

        return super.getItemBarStep(args);
    }
}
