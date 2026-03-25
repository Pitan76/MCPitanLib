package net.pitan76.mcpitanlib.api.util;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;

public class BlockViewUtil {
    public static BlockState getBlockState(BlockView blockView, BlockPos pos) {
        return blockView.getBlockState(pos);
    }

    public static BlockEntity getBlockEntity(BlockView blockView, BlockPos pos) {
        return blockView.getBlockEntity(pos);
    }

    public static boolean hasBlockEntity(BlockView blockView, BlockPos pos) {
        return getBlockEntity(blockView, pos) != null;
    }

    public static boolean isAir(BlockView blockView, BlockPos pos) {
        return getBlockState(blockView, pos).isAir();
    }
}
