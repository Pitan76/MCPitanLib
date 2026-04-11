package net.pitan76.mcpitanlib.api.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.state.properties.SlabType;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.pitan76.mcpitanlib.api.block.v2.CompatBlockProvider;
import net.pitan76.mcpitanlib.api.block.v2.CompatibleBlockSettings;
import net.pitan76.mcpitanlib.api.event.block.AppendPropertiesArgs;
import net.pitan76.mcpitanlib.api.event.block.CanPathfindThroughArgs;
import net.pitan76.mcpitanlib.api.event.block.PlacementStateArgs;
import net.pitan76.mcpitanlib.api.state.property.BooleanProperty;
import net.pitan76.mcpitanlib.api.state.property.CompatProperties;
import net.pitan76.mcpitanlib.api.state.property.EnumProperty;
import net.pitan76.mcpitanlib.core.serialization.CompatMapCodec;

public class CompatSlabBlock extends SlabBlock implements CompatBlockProvider {

    public static final EnumProperty<SlabType> TYPE = CompatProperties.of(SlabBlock.TYPE);
    public static final BooleanProperty WATERLOGGED = CompatProperties.of(SlabBlock.WATERLOGGED);

    public CompatibleBlockSettings settings;

    @Override
    public CompatibleBlockSettings getCompatSettings() {
        return settings;
    }

    public CompatSlabBlock(Properties settings) {
        super(settings);
    }

    public CompatSlabBlock(CompatibleBlockSettings settings) {
        this(settings.build());
    }

    public void appendProperties(AppendPropertiesArgs args) {
        super.createBlockStateDefinition(args.builder);
    }

    public BlockState getPlacementState(PlacementStateArgs args) {
        return super.getStateForPlacement(args.ctx);
    }

    @Deprecated
    @Override
    public void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        appendProperties(new AppendPropertiesArgs(builder));
    }

    @Deprecated
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext ctx) {
        return getPlacementState(new PlacementStateArgs(ctx));
    }

    // ExtendBlockProvider
    @Deprecated
    @Override
    public void appendProperties(AppendPropertiesArgs args, Options options) {
        CompatBlockProvider.super.appendProperties(args, options);
    }

    @Deprecated
    @Override
    public BlockState getPlacementState(PlacementStateArgs args, Options options) {
        return CompatBlockProvider.super.getPlacementState(args, options);
    }

    @Deprecated
    @Override
    public MapCodec<? extends SlabBlock> codec() {
        return getCompatCodec().getCodec();
    }

    public CompatMapCodec<? extends SlabBlock> getCompatCodec() {
        return CompatMapCodec.of(super.codec());
    }

    @Deprecated
    @Override
    public boolean isPathfindable(BlockState state, PathComputationType type) {
        return canPathfindThrough(new CanPathfindThroughArgs(state, type));
    }

    public boolean canPathfindThrough(CanPathfindThroughArgs args) {
        return super.isPathfindable(args.state, args.type);
    }

    @Override
    public Boolean canPathfindThrough(CanPathfindThroughArgs args, Options options) {
        return CompatBlockProvider.super.canPathfindThrough(args, options);
    }
}
