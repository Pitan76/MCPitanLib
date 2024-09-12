package net.pitan76.mcpitanlib.api.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.*;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.pitan76.mcpitanlib.api.event.block.AppendPropertiesArgs;
import net.pitan76.mcpitanlib.api.event.block.CanPathfindThroughArgs;
import net.pitan76.mcpitanlib.api.event.block.OutlineShapeEvent;
import net.pitan76.mcpitanlib.api.event.block.PlacementStateArgs;
import net.pitan76.mcpitanlib.core.serialization.CompatMapCodec;

public class CompatStairsBlock extends StairsBlock implements ExtendBlockProvider {
    public CompatStairsBlock(BlockState baseBlockState, Settings settings) {
        super(baseBlockState, settings);
    }

    public CompatStairsBlock(BlockState baseBlockState, CompatibleBlockSettings settings) {
        this(baseBlockState, settings.build());
    }

    public VoxelShape getOutlineShape(OutlineShapeEvent event) {
        return super.getOutlineShape(event.state, event.world, event.pos, event.context);
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

    @Deprecated
    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return getOutlineShape(new OutlineShapeEvent(state, world, pos, context));
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

    @Deprecated
    @Override
    public VoxelShape getOutlineShape(OutlineShapeEvent event, Options options) {
        return ExtendBlockProvider.super.getOutlineShape(event, options);
    }

    public CompatMapCodec<? extends StairsBlock> getCompatCodec() {
        return CompatMapCodec.of();
    }

    @Deprecated
    @Override
    public boolean canPathfindThrough(BlockState state, BlockView world, BlockPos pos, NavigationType type) {
        return canPathfindThrough(new CanPathfindThroughArgs(state, world, pos, type));
    }

    @SuppressWarnings("removal")
    public boolean canPathfindThrough(CanPathfindThroughArgs args) {
        return super.canPathfindThrough(args.state, args.getBlockView(), args.getPos(), args.type);
    }

    @Override
    public Boolean canPathfindThrough(CanPathfindThroughArgs args, Options options) {
        return ExtendBlockProvider.super.canPathfindThrough(args, options);
    }
}
