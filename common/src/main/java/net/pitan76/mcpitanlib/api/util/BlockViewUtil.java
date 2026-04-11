package net.pitan76.mcpitanlib.api.util;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;

public class BlockViewUtil {
    public static BlockState getBlockState(BlockGetter blockView, BlockPos pos) {
        return blockView.getBlockState(pos);
    }

    public static BlockEntity getBlockEntity(BlockGetter blockView, BlockPos pos) {
        return blockView.getBlockEntity(pos);
    }

    public static boolean hasBlockEntity(BlockGetter blockView, BlockPos pos) {
        return getBlockEntity(blockView, pos) != null;
    }

    public static boolean isAir(BlockGetter blockView, BlockPos pos) {
        return getBlockState(blockView, pos).isAir();
    }
}
