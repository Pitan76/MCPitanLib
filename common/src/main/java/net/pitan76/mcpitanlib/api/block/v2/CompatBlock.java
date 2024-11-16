package net.pitan76.mcpitanlib.api.block.v2;

import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.pitan76.mcpitanlib.api.block.CompatBlockRenderType;
import net.pitan76.mcpitanlib.api.block.ExtendBlock;
import net.pitan76.mcpitanlib.api.block.args.RenderTypeArgs;
import net.pitan76.mcpitanlib.api.block.args.RotateArgs;
import net.pitan76.mcpitanlib.api.block.args.SideInvisibleArgs;
import net.pitan76.mcpitanlib.api.block.args.v2.OutlineShapeEvent;
import net.pitan76.mcpitanlib.api.block.args.v2.PlacementStateArgs;
import net.pitan76.mcpitanlib.api.block.args.v2.StateForNeighborUpdateArgs;
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

    @Deprecated
    @Override
    public @Nullable BlockState getPlacementState(net.pitan76.mcpitanlib.api.event.block.PlacementStateArgs args) {
        return getPlacementState(new PlacementStateArgs(args.ctx)).toMinecraft();
    }

    public BlockState getStateForNeighborUpdate(StateForNeighborUpdateArgs args) {
        return super.getStateForNeighborUpdate(args.state, args.direction, args.neighborState, args.world, args.pos, args.neighborPos);
    }

    @Deprecated
    @Override
    public BlockState getStateForNeighborUpdate(net.pitan76.mcpitanlib.api.event.block.StateForNeighborUpdateArgs args) {
        return getStateForNeighborUpdate(new StateForNeighborUpdateArgs(args.state, args.direction, args.neighborState, args.world, args.pos, args.neighborPos));
    }

    public VoxelShape getOutlineShape(OutlineShapeEvent e) {
        return super.getOutlineShape(e.state.toMinecraft(), e.world.getRaw(), e.pos.toMinecraft(), e.context);
    }

    @Deprecated
    @Override
    public VoxelShape getOutlineShape(net.pitan76.mcpitanlib.api.event.block.OutlineShapeEvent e) {
        return getOutlineShape(new OutlineShapeEvent(e.state, e.world, e.pos, e.context));
    }
}
