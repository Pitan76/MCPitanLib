package net.pitan76.mcpitanlib.api.block.v2;

import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.pitan76.mcpitanlib.api.block.CompatBlockRenderType;
import net.pitan76.mcpitanlib.api.block.ExtendBlock;
import net.pitan76.mcpitanlib.api.block.args.RenderTypeArgs;
import net.pitan76.mcpitanlib.api.block.args.RotateArgs;
import net.pitan76.mcpitanlib.api.block.args.SideInvisibleArgs;
import net.pitan76.mcpitanlib.api.block.args.v2.*;
import net.pitan76.mcpitanlib.api.util.math.random.CompatRandom;
import net.pitan76.mcpitanlib.midohra.block.BlockWrapper;
import org.jetbrains.annotations.Nullable;

public class CompatBlock extends ExtendBlock {

    public CompatibleBlockSettings settings;

    public CompatBlock(CompatibleBlockSettings settings) {
        super(settings);
        this.settings = settings;
    }

    public CompatibleBlockSettings getCompatSettings() {
        return settings;
    }

    public BlockWrapper getWrapper() {
        return BlockWrapper.of(this);
    }

    @Override
    @Deprecated
    protected RenderShape getRenderShape(BlockState state) {
        return getRenderType(new RenderTypeArgs(state)).renderType;
    }

    public CompatBlockRenderType getRenderType(RenderTypeArgs args) {
        return new CompatBlockRenderType(super.getRenderShape(args.state));
    }

    @Override
    @Deprecated
    protected BlockState rotate(BlockState state, Rotation rotation) {
        return rotate(new RotateArgs(state, rotation)).toMinecraft();
    }

    public net.pitan76.mcpitanlib.midohra.block.BlockState rotate(RotateArgs args) {
        return net.pitan76.mcpitanlib.midohra.block.BlockState.of(super.rotate(args.state, args.rotation));
    }

    @Override
    @Deprecated
    protected boolean skipRendering(BlockState state, BlockState stateFrom, Direction direction) {
        return isSideInvisible(new SideInvisibleArgs(state, stateFrom, direction));
    }

    public boolean isSideInvisible(SideInvisibleArgs args) {
        return super.skipRendering(args.state, args.stateFrom, args.direction);
    }

    /**
     * Compatible for getDefaultState()
     * @return default block state
     */
    public net.pitan76.mcpitanlib.midohra.block.BlockState getDefaultMidohraState() {
        return net.pitan76.mcpitanlib.midohra.block.BlockState.of(getNewDefaultState());
    }

    /**
     * Compatible for setDefaultState()
     * @param state BlockState
     */
    public void setDefaultState(net.pitan76.mcpitanlib.midohra.block.BlockState state) {
        setNewDefaultState(state.toMinecraft());
    }

    public @Nullable net.pitan76.mcpitanlib.midohra.block.BlockState getPlacementState(PlacementStateArgs args) {
        return net.pitan76.mcpitanlib.midohra.block.BlockState.of(super.getStateForPlacement(args.ctx));
    }

    @Override
    public @Nullable BlockState getStateForPlacement(BlockPlaceContext ctx) {
        return getPlacementState(new PlacementStateArgs(ctx)).toMinecraft();
    }

    @Deprecated
    @Override
    public @Nullable BlockState getPlacementState(net.pitan76.mcpitanlib.api.event.block.PlacementStateArgs args) {
        return super.getPlacementState(args);
    }

    public net.pitan76.mcpitanlib.midohra.block.BlockState getStateForNeighborUpdate(StateForNeighborUpdateArgs args) {
        return net.pitan76.mcpitanlib.midohra.block.BlockState.of(super.updateShape(args.state, args.world, args.tickView, args.pos, args.direction, args.neighborPos, args.neighborState, args.random.getMcRandom()));
    }

    @Override
    protected BlockState updateShape(BlockState state, LevelReader world, ScheduledTickAccess tickView, BlockPos pos, Direction direction, BlockPos neighborPos, BlockState neighborState, RandomSource random) {
        return getStateForNeighborUpdate(new StateForNeighborUpdateArgs(state, direction, neighborState, world, pos, neighborPos, tickView, new CompatRandom(random))).toMinecraft();
    }

    @Deprecated
    @Override
    public BlockState getStateForNeighborUpdate(net.pitan76.mcpitanlib.api.event.block.StateForNeighborUpdateArgs args) {
        return super.getStateForNeighborUpdate((args));
    }

    public VoxelShape getOutlineShape(OutlineShapeEvent e) {
        net.pitan76.mcpitanlib.midohra.util.shape.VoxelShape shape = getOutlineShapeM(e);
        if (shape != null) return shape.toMinecraft();

        return super.getShape(e.state.toMinecraft(), e.world.getRaw(), e.pos.toMinecraft(), e.context);
    }

    /**
     * Override this instead of {@link #getOutlineShape(OutlineShapeEvent)} to return a midohra VoxelShape.
     * @param e OutlineShapeEvent
     * @return midohra VoxelShape (null to fall back to the default shape)
     */
    @Nullable
    public net.pitan76.mcpitanlib.midohra.util.shape.VoxelShape getOutlineShapeM(OutlineShapeEvent e) {
        return null;
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return getOutlineShape(new OutlineShapeEvent(state, world, pos, context));
    }

    @Deprecated
    @Override
    public VoxelShape getOutlineShape(net.pitan76.mcpitanlib.api.event.block.OutlineShapeEvent e) {
        return super.getOutlineShape(e);
    }

    public VoxelShape getCollisionShape(CollisionShapeEvent e) {
        net.pitan76.mcpitanlib.midohra.util.shape.VoxelShape shape = getCollisionShapeM(e);
        if (shape != null) return shape.toMinecraft();

        return super.getCollisionShape(e.state.toMinecraft(), e.world.getRaw(), e.pos.toMinecraft(), e.context);
    }

    /**
     * Override this instead of {@link #getCollisionShape(CollisionShapeEvent)} to return a midohra VoxelShape.
     * @param e CollisionShapeEvent
     * @return midohra VoxelShape (null to fall back to the default shape)
     */
    @Nullable
    public net.pitan76.mcpitanlib.midohra.util.shape.VoxelShape getCollisionShapeM(CollisionShapeEvent e) {
        return null;
    }

    @Deprecated
    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return getCollisionShape(new CollisionShapeEvent(state, world, pos, context));
    }

    @Deprecated
    @Override
    public VoxelShape getCollisionShape(net.pitan76.mcpitanlib.api.event.block.CollisionShapeEvent e) {
        return super.getCollisionShape(e);
    }

    @Deprecated
    @Override
    public boolean hasAnalogOutputSignal(BlockState state) {
        return hasComparatorOutput(new HasComparatorOutputArgs(state));
    }

    public boolean hasComparatorOutput(HasComparatorOutputArgs args) {
        return super.hasAnalogOutputSignal(args.state);
    }

    @Deprecated
    @Override
    public int getAnalogOutputSignal(BlockState state, Level world, BlockPos pos, Direction direction) {
        return getComparatorOutput(new GetComparatorOutputArgs(state, world, pos, direction));
    }

    public int getComparatorOutput(GetComparatorOutputArgs args) {
        return super.getAnalogOutputSignal(args.state, args.world, args.pos, args.direction);
    }

    @Deprecated
    @Override
    protected boolean canSurvive(BlockState state, LevelReader world, BlockPos pos) {
        return canPlaceAt(new CanPlaceAtArgs(state, world, pos));
    }

    public boolean canPlaceAt(CanPlaceAtArgs args) {
        return super.canSurvive(args.state, args.world, args.pos);
    }
}
