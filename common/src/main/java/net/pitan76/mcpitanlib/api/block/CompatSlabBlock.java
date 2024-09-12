package net.pitan76.mcpitanlib.api.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.StairsBlock;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.pitan76.mcpitanlib.api.event.block.AppendPropertiesArgs;
import net.pitan76.mcpitanlib.api.event.block.CanPathfindThroughArgs;
import net.pitan76.mcpitanlib.api.event.block.PlacementStateArgs;
import net.pitan76.mcpitanlib.core.serialization.CompatMapCodec;

public class CompatSlabBlock extends SlabBlock implements ExtendBlockProvider {
    public CompatSlabBlock(Settings settings) {
        super(settings);
    }

    public CompatSlabBlock(CompatibleBlockSettings settings) {
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

    @Deprecated
    @Override
    public MapCodec<? extends SlabBlock> getCodec() {
        return getCompatCodec().getCodec();
    }

    public CompatMapCodec<? extends SlabBlock> getCompatCodec() {
        return CompatMapCodec.of(super.getCodec());
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
