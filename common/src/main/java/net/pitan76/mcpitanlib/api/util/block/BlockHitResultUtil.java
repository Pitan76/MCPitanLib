package net.pitan76.mcpitanlib.api.util.block;

import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;

public class BlockHitResultUtil {

    public static BlockHitResult create(Vec3d pos, Direction direction, BlockPos blockPos, boolean insideBlock) {
        return new BlockHitResult(pos, direction, blockPos, insideBlock);
    }

    public static BlockHitResult create(Vec3d pos, Direction direction, BlockPos blockPos) {
        return new BlockHitResult(pos, direction, blockPos, false);
    }

    public static Vec3d getPos(BlockHitResult blockHitResult) {
        return blockHitResult.getPos();
    }

    public static Direction getSide(BlockHitResult blockHitResult) {
        return blockHitResult.getSide();
    }

    public static BlockPos getBlockPos(BlockHitResult blockHitResult) {
        return blockHitResult.getBlockPos();
    }

    public static boolean isInsideBlock(BlockHitResult blockHitResult) {
        return blockHitResult.isInsideBlock();
    }
}
