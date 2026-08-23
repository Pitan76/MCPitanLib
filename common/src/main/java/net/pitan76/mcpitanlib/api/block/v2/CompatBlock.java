package net.pitan76.mcpitanlib.api.block.v2;

import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import net.minecraft.world.tick.ScheduledTickView;
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
    protected BlockRenderType getRenderType(BlockState state) {
        return getRenderType(new RenderTypeArgs(state)).renderType;
    }

    public CompatBlockRenderType getRenderType(RenderTypeArgs args) {
        return new CompatBlockRenderType(super.getRenderType(args.state));
    }

    @Override
    @Deprecated
    protected BlockState rotate(BlockState state, BlockRotation rotation) {
        return rotate(new RotateArgs(state, rotation)).toMinecraft();
    }

    public net.pitan76.mcpitanlib.midohra.block.BlockState rotate(RotateArgs args) {
        return net.pitan76.mcpitanlib.midohra.block.BlockState.of(super.rotate(args.state, args.rotation));
    }

    @Override
    @Deprecated
    protected boolean isSideInvisible(BlockState state, BlockState stateFrom, Direction direction) {
        return isSideInvisible(new SideInvisibleArgs(state, stateFrom, direction));
    }

    public boolean isSideInvisible(SideInvisibleArgs args) {
        return super.isSideInvisible(args.state, args.stateFrom, args.direction);
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
        return net.pitan76.mcpitanlib.midohra.block.BlockState.of(super.getPlacementState(args.ctx));
    }

    @Override
    public @Nullable BlockState getPlacementState(ItemPlacementContext ctx) {
        return getPlacementState(new PlacementStateArgs(ctx)).toMinecraft();
    }

    @Deprecated
    @Override
    public @Nullable BlockState getPlacementState(net.pitan76.mcpitanlib.api.event.block.PlacementStateArgs args) {
        return super.getPlacementState(args);
    }

    public net.pitan76.mcpitanlib.midohra.block.BlockState getStateForNeighborUpdate(StateForNeighborUpdateArgs args) {
        return net.pitan76.mcpitanlib.midohra.block.BlockState.of(super.getStateForNeighborUpdate(args.state, args.world, args.tickView, args.pos, args.direction, args.neighborPos, args.neighborState, args.random.getMcRandom()));
    }

    @Override
    protected BlockState getStateForNeighborUpdate(BlockState state, WorldView world, ScheduledTickView tickView, BlockPos pos, Direction direction, BlockPos neighborPos, BlockState neighborState, Random random) {
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

        return super.getOutlineShape(e.state.toMinecraft(), e.world.getRaw(), e.pos.toMinecraft(), e.context);
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
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
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
    public VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return getCollisionShape(new CollisionShapeEvent(state, world, pos, context));
    }

    @Deprecated
    @Override
    public VoxelShape getCollisionShape(net.pitan76.mcpitanlib.api.event.block.CollisionShapeEvent e) {
        return super.getCollisionShape(e);
    }

    @Deprecated
    @Override
    public boolean hasComparatorOutput(BlockState state) {
        return hasComparatorOutput(new HasComparatorOutputArgs(state));
    }

    public boolean hasComparatorOutput(HasComparatorOutputArgs args) {
        return super.hasComparatorOutput(args.state);
    }

    @Deprecated
    @Override
    public int getComparatorOutput(BlockState state, World world, BlockPos pos, Direction direction) {
        return getComparatorOutput(new GetComparatorOutputArgs(state, world, pos, direction));
    }

    public int getComparatorOutput(GetComparatorOutputArgs args) {
        return super.getComparatorOutput(args.state, args.world, args.pos, args.direction);
    }

    @Deprecated
    @Override
    protected boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        return canPlaceAt(new CanPlaceAtArgs(state, world, pos));
    }

    public boolean canPlaceAt(CanPlaceAtArgs args) {
        return super.canPlaceAt(args.state, args.world, args.pos);
    }
}
