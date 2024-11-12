package net.pitan76.mcpitanlib.api.block.v2;

import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.math.Direction;
import net.pitan76.mcpitanlib.api.block.CompatBlockRenderType;
import net.pitan76.mcpitanlib.api.block.ExtendBlock;
import net.pitan76.mcpitanlib.api.block.args.RenderTypeArgs;
import net.pitan76.mcpitanlib.api.block.args.RotateArgs;
import net.pitan76.mcpitanlib.api.block.args.SideInvisibleArgs;
import net.pitan76.mcpitanlib.midohra.block.BlockWrapper;

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
    public BlockRenderType getRenderType(BlockState state) {
        return getRenderType(new RenderTypeArgs(state)).renderType;
    }

    public CompatBlockRenderType getRenderType(RenderTypeArgs args) {
        return new CompatBlockRenderType(super.getRenderType(args.state));
    }

    @Override
    @Deprecated
    public BlockState rotate(BlockState state, BlockRotation rotation) {
        return rotate(new RotateArgs(state, rotation));
    }

    public BlockState rotate(RotateArgs args) {
        return super.rotate(args.state, args.rotation);
    }

    @Override
    @Deprecated
    public boolean isSideInvisible(BlockState state, BlockState stateFrom, Direction direction) {
        return isSideInvisible(new SideInvisibleArgs(state, stateFrom, direction));
    }

    public boolean isSideInvisible(SideInvisibleArgs args) {
        return super.isSideInvisible(args.state, args.stateFrom, args.direction);
    }
}
