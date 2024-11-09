package net.pitan76.mcpitanlib.midohra.easybuilder;

import net.pitan76.mcpitanlib.api.block.v2.CompatBlock;
import net.pitan76.mcpitanlib.api.block.v2.CompatibleBlockSettings;
import net.pitan76.mcpitanlib.api.event.block.AppendPropertiesArgs;
import net.pitan76.mcpitanlib.api.event.block.BlockUseEvent;
import net.pitan76.mcpitanlib.api.event.block.StateReplacedEvent;
import net.pitan76.mcpitanlib.api.event.item.ItemAppendTooltipEvent;
import net.pitan76.mcpitanlib.api.util.CompatActionResult;
import net.pitan76.mcpitanlib.api.util.CompatIdentifier;

import java.util.function.Consumer;
import java.util.function.Function;

public class BuiltBlock extends CompatBlock {

    protected Function<BlockUseEvent, CompatActionResult> onRightClick;
    protected Consumer<StateReplacedEvent> onStateReplaced;
    protected Consumer<ItemAppendTooltipEvent> onAppendTooltip;
    protected Consumer<AppendPropertiesArgs> onAppendProperties;

    public BuiltBlock(CompatibleBlockSettings settings) {
        super(settings);
    }

    public BuiltBlock(BlockBuilder builder) {
        this(builder.settingsBuilder.build());

        this.onRightClick = builder.onRightClick;
        this.onStateReplaced = builder.onStateReplaced;
        this.onAppendTooltip = builder.onAppendTooltip;
        this.onAppendProperties = builder.onAppendProperties;
    }

    public BuiltBlock(BlockBuilder builder, CompatIdentifier id) {
        this(builder.settingsBuilder.build(id));

        this.onRightClick = builder.onRightClick;
        this.onStateReplaced = builder.onStateReplaced;
        this.onAppendTooltip = builder.onAppendTooltip;
        this.onAppendProperties = builder.onAppendProperties;
    }

    @Override
    public CompatActionResult onRightClick(BlockUseEvent e) {
        if (onRightClick == null)
            return e.pass();

        return onRightClick.apply(e);
    }

    @Override
    public void onStateReplaced(StateReplacedEvent e) {
        if (onStateReplaced != null)
            onStateReplaced.accept(e);
    }

    @Override
    public void appendTooltip(ItemAppendTooltipEvent e) {
        if (onAppendTooltip != null)
            onAppendTooltip.accept(e);
    }

    @Override
    public void appendProperties(AppendPropertiesArgs args) {
        if (onAppendProperties != null)
            onAppendProperties.accept(args);
    }
}
