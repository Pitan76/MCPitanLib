package net.pitan76.mcpitanlib.midohra.world;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.util.math.BlockPos;

public class BlockView implements IWorldView {
    private net.minecraft.world.BlockView blockView;

    public BlockView(net.minecraft.world.BlockView blockView) {
        this.blockView = blockView;
    }

    public static BlockView of(net.minecraft.world.BlockView blockView) {
        return new BlockView(blockView);
    }

    public net.minecraft.world.BlockView getRaw() {
        return blockView;
    }

    public net.minecraft.world.BlockView toMinecraft() {
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
