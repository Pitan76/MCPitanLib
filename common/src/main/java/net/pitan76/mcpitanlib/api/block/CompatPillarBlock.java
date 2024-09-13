package net.pitan76.mcpitanlib.api.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.PillarBlock;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.util.math.Direction;
import net.pitan76.mcpitanlib.api.event.block.AppendPropertiesArgs;
import net.pitan76.mcpitanlib.api.event.block.PlacementStateArgs;
import net.pitan76.mcpitanlib.core.serialization.CompatMapCodec;

public class CompatPillarBlock extends PillarBlock implements ExtendBlockProvider {

    public static final EnumProperty<Direction.Axis> AXIS = PillarBlock.AXIS;

    public CompatPillarBlock(Settings settings) {
        super(settings);
    }

    public CompatPillarBlock(CompatibleBlockSettings settings) {
        this(settings.build());
    }

    public void appendProperties(AppendPropertiesArgs args) {
        super.appendProperties(args.builder);
    }

    public BlockState getPlacementState(PlacementStateArgs args) {
        return super.getPlacementState(args.ctx);
    }

    @Deprecated
    @Override
    public void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        appendProperties(new AppendPropertiesArgs(builder));
    }

    @Deprecated
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return getPlacementState(new PlacementStateArgs(ctx));
    }

    // ExtendBlockProvider
    @Deprecated
    @Override
    public void appendProperties(AppendPropertiesArgs args, Options options) {
        ExtendBlockProvider.super.appendProperties(args, options);
    }

    @Deprecated
    @Override
    public BlockState getPlacementState(PlacementStateArgs args, Options options) {
        return ExtendBlockProvider.super.getPlacementState(args, options);
    }

    public CompatMapCodec<? extends PillarBlock> getCompatCodec() {
        return CompatMapCodec.of();
    }
}
