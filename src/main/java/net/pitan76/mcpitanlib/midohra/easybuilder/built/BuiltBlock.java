package net.pitan76.mcpitanlib.midohra.easybuilder.built;

import net.minecraft.item.ItemStack;
import net.minecraft.util.shape.VoxelShape;
import net.pitan76.mcpitanlib.api.block.CompatBlockRenderType;
import net.pitan76.mcpitanlib.api.block.args.RenderTypeArgs;
import net.pitan76.mcpitanlib.api.block.args.v2.CollisionShapeEvent;
import net.pitan76.mcpitanlib.api.block.args.v2.OutlineShapeEvent;
import net.pitan76.mcpitanlib.api.block.args.v2.PlacementStateArgs;
import net.pitan76.mcpitanlib.api.block.args.v2.StateForNeighborUpdateArgs;
import net.pitan76.mcpitanlib.api.block.v2.CompatBlock;
import net.pitan76.mcpitanlib.api.block.v2.CompatibleBlockSettings;
import net.pitan76.mcpitanlib.api.event.block.AppendPropertiesArgs;
import net.pitan76.mcpitanlib.api.event.block.BlockUseEvent;
import net.pitan76.mcpitanlib.api.event.block.DroppedStacksArgs;
import net.pitan76.mcpitanlib.api.event.block.StateReplacedEvent;
import net.pitan76.mcpitanlib.api.event.item.ItemAppendTooltipEvent;
import net.pitan76.mcpitanlib.api.util.CompatActionResult;
import net.pitan76.mcpitanlib.api.util.CompatIdentifier;
import net.pitan76.mcpitanlib.midohra.block.BlockState;
import net.pitan76.mcpitanlib.midohra.easybuilder.BlockBuilder;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public class BuiltBlock extends CompatBlock {

    protected Function<BlockUseEvent, CompatActionResult> onRightClick;
    protected Consumer<StateReplacedEvent> onStateReplaced;
    protected Consumer<ItemAppendTooltipEvent> onAppendTooltip;
    protected Consumer<AppendPropertiesArgs> onAppendProperties;
    protected Function<OutlineShapeEvent, VoxelShape> onOutlineShape;
    protected Function<CollisionShapeEvent, VoxelShape> onCollisionShape;
    protected Function<RenderTypeArgs, CompatBlockRenderType> onRenderType;
    protected Function<PlacementStateArgs, @Nullable BlockState> onPlacementState;
    protected Function<StateForNeighborUpdateArgs, BlockState> onStateForNeighborUpdate;
    protected Function<DroppedStacksArgs, List<ItemStack>> onDroppedStacks;

    public BuiltBlock(CompatibleBlockSettings settings) {
        super(settings);
    }

    public BuiltBlock(BlockBuilder builder) {
        this(builder.settingsBuilder.build());
        init(builder);
    }

    public BuiltBlock(BlockBuilder builder, CompatIdentifier id) {
        this(builder.settingsBuilder.build(id));
        init(builder);
    }

    protected void init(BlockBuilder builder) {
        this.onRightClick = builder.onRightClick;
        this.onStateReplaced = builder.onStateReplaced;
        this.onAppendTooltip = builder.onAppendTooltip;
        this.onAppendProperties = builder.onAppendProperties;
        this.onOutlineShape = builder.onOutlineShape;
        this.onCollisionShape = builder.onCollisionShape;
        this.onRenderType = builder.onRenderType;
        this.onPlacementState = builder.onPlacementState;
        this.onStateForNeighborUpdate = builder.onStateForNeighborUpdate;

        if (builder.onInit != null)
            builder.onInit.accept(this.getWrapper(), builder);

        if (builder.defaultState != null)
            this.setDefaultState(builder.defaultState);
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

    @Override
    public VoxelShape getOutlineShape(OutlineShapeEvent e) {
        if (onOutlineShape != null)
            return onOutlineShape.apply(e);

        return super.getOutlineShape(e);
    }

    @Override
    public VoxelShape getCollisionShape(CollisionShapeEvent e) {
        if (onCollisionShape != null)
            return onCollisionShape.apply(e);

        return super.getCollisionShape(e);
    }

    @Override
    public CompatBlockRenderType getRenderType(RenderTypeArgs args) {
        if (onRenderType != null)
            return onRenderType.apply(args);

        return super.getRenderType(args);
    }

    @Override
    public @Nullable BlockState getPlacementState(PlacementStateArgs args) {
        if (onPlacementState != null)
            return onPlacementState.apply(args);

        return super.getPlacementState(args);
    }

    @Override
    public BlockState getStateForNeighborUpdate(StateForNeighborUpdateArgs args) {
        if (onStateForNeighborUpdate != null)
            return onStateForNeighborUpdate.apply(args);

        return super.getStateForNeighborUpdate(args);
    }

    @Override
    public List<ItemStack> getDroppedStacks(DroppedStacksArgs args) {
        if (onDroppedStacks != null)
            return onDroppedStacks.apply(args);

        return super.getDroppedStacks(args);
    }
}
