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
import net.minecraft.world.WorldView;
import net.pitan76.mcpitanlib.api.block.CompatBlockRenderType;
import net.pitan76.mcpitanlib.api.block.ExtendBlock;
import net.pitan76.mcpitanlib.api.block.args.RenderTypeArgs;
import net.pitan76.mcpitanlib.api.block.args.RotateArgs;
import net.pitan76.mcpitanlib.api.block.args.SideInvisibleArgs;
import net.pitan76.mcpitanlib.api.block.args.v2.OutlineShapeEvent;
import net.pitan76.mcpitanlib.api.block.args.v2.PlacementStateArgs;
import net.pitan76.mcpitanlib.api.block.args.v2.StateForNeighborUpdateArgs;
import net.pitan76.mcpitanlib.api.event.block.CollisionShapeEvent;
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
        return rotate(new RotateArgs(state, rotation));
    }

    public BlockState rotate(RotateArgs args) {
        return super.rotate(args.state, args.rotation);
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
    public BlockState getStateForNeighborUpdate(BlockState state, WorldView world, ScheduledTickView tickView, BlockPos pos, Direction direction, BlockPos neighborPos, BlockState neighborState, Random random) {
        return getStateForNeighborUpdate(new StateForNeighborUpdateArgs(state, direction, neighborState, world, pos, neighborPos, tickView, new CompatRandom(random))).toMinecraft();
    }

    @Deprecated
    @Override
    public BlockState getStateForNeighborUpdate(net.pitan76.mcpitanlib.api.event.block.StateForNeighborUpdateArgs args) {
        return super.getStateForNeighborUpdate((args));
    }

    public VoxelShape getOutlineShape(OutlineShapeEvent e) {
        return super.getOutlineShape(e.state.toMinecraft(), e.world.getRaw(), e.pos.toMinecraft(), e.context);
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

    @Override
    public VoxelShape getCollisionShape(CollisionShapeEvent e) {
        return super.getCollisionShape(e);
    }
}
