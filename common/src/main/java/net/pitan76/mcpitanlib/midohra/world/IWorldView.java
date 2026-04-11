package net.pitan76.mcpitanlib.midohra.world;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.core.BlockPos;
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
        return FluidWrapper.of(getFluidState(pos.toMinecraft()).getType());
    }

    default net.pitan76.mcpitanlib.midohra.fluid.FluidState getFluidState(net.pitan76.mcpitanlib.midohra.util.math.BlockPos pos) {
        return net.pitan76.mcpitanlib.midohra.fluid.FluidState.of(getFluidState(pos.toMinecraft()));
    }

    boolean isNull();
}
