package net.pitan76.mcpitanlib.midohra.world;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.util.math.BlockPos;
import net.pitan76.mcpitanlib.midohra.block.entity.BlockEntityWrapper;
import net.pitan76.mcpitanlib.midohra.fluid.FluidWrapper;

public interface IWorldView {
    BlockEntity getBlockEntity(BlockPos pos);

    BlockState getBlockState(BlockPos pos);

    FluidState getFluidState(BlockPos pos);

    default BlockEntityWrapper getBlockEntity(net.pitan76.mcpitanlib.midohra.util.math.BlockPos pos) {
        return BlockEntityWrapper.of(getBlockEntity(pos.toMinecraft()));
    }

    default net.pitan76.mcpitanlib.midohra.block.BlockState getBlockState(net.pitan76.mcpitanlib.midohra.util.math.BlockPos pos) {
        return net.pitan76.mcpitanlib.midohra.block.BlockState.of(getBlockState(pos.toMinecraft()));
    }

    default FluidWrapper getFluid(net.pitan76.mcpitanlib.midohra.util.math.BlockPos pos) {
        return FluidWrapper.of(getFluidState(pos.toMinecraft()).getFluid());
    }
}
