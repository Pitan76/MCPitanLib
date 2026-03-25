package net.pitan76.mcpitanlib.midohra.world;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.core.BlockPos;

public class BlockView implements IWorldView {
    private net.minecraft.world.level.BlockGetter blockView;

    public BlockView(net.minecraft.world.level.BlockGetter blockView) {
        this.blockView = blockView;
    }

    public static BlockView of(net.minecraft.world.level.BlockGetter blockView) {
        return new BlockView(blockView);
    }

    public net.minecraft.world.level.BlockGetter getRaw() {
        return blockView;
    }

    public net.minecraft.world.level.BlockGetter toMinecraft() {
        return getRaw();
    }

    @Override
    public BlockEntity getBlockEntity(BlockPos pos) {
        return getRaw().getBlockEntity(pos);
    }

    @Override
    public BlockState getBlockState(BlockPos pos) {
        return getRaw().getBlockState(pos);
    }

    @Override
    public FluidState getFluidState(BlockPos pos) {
        return getRaw().getFluidState(pos);
    }
}
