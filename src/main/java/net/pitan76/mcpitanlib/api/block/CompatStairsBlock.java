package net.pitan76.mcpitanlib.api.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Half;
import net.minecraft.world.level.block.state.properties.StairsShape;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.pitan76.mcpitanlib.api.block.v2.CompatBlockProvider;
import net.pitan76.mcpitanlib.api.block.v2.CompatibleBlockSettings;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.level.BlockGetter;
import net.pitan76.mcpitanlib.api.event.block.AppendPropertiesArgs;
import net.pitan76.mcpitanlib.api.event.block.CanPathfindThroughArgs;
import net.pitan76.mcpitanlib.api.event.block.OutlineShapeEvent;
import net.pitan76.mcpitanlib.api.event.block.PlacementStateArgs;
import net.pitan76.mcpitanlib.api.state.property.CompatProperties;
import net.pitan76.mcpitanlib.api.state.property.DirectionProperty;
import net.pitan76.mcpitanlib.core.serialization.CompatMapCodec;

public class CompatStairsBlock extends StairBlock implements CompatBlockProvider {

    public static final DirectionProperty FACING = CompatProperties.HORIZONTAL_FACING;
    public static final EnumProperty<Half> HALF = StairBlock.HALF;
    public static final EnumProperty<StairsShape> SHAPE = StairBlock.SHAPE;
    public static final BooleanProperty WATERLOGGED = StairBlock.WATERLOGGED;

    public CompatibleBlockSettings compatSettings;

    /**
     * get compatible block settings
     * @return CompatibleBlockSettings
     */
    @Override
    public CompatibleBlockSettings getCompatSettings() {
        return compatSettings;
    }

    public CompatStairsBlock(BlockState baseBlockState, Properties settings) {
        super(baseBlockState, settings);
    }

    public CompatStairsBlock(BlockState baseBlockState, CompatibleBlockSettings settings) {
        this(baseBlockState, settings.build());
        this.compatSettings = settings;
    }

    public VoxelShape getOutlineShape(OutlineShapeEvent event) {
        return super.getShape(event.state, event.world, event.pos, event.context);
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

    @Deprecated
    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return getOutlineShape(new OutlineShapeEvent(state, world, pos, context));
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
    public VoxelShape getOutlineShape(OutlineShapeEvent event, Options options) {
        return CompatBlockProvider.super.getOutlineShape(event, options);
    }

    @Deprecated
    @Override
    public MapCodec<? extends StairBlock> codec() {
        return getCompatCodec().getCodec();
    }

    public CompatMapCodec<? extends StairBlock> getCompatCodec() {
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

    public BlockState getBaseBlockState() {
        return super.baseState;
    }
}
