package net.pitan76.mcpitanlib.api.util.block;

import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.phys.Vec3;

public class BlockHitResultUtil {

    public static BlockHitResult create(Vec3 pos, Direction direction, BlockPos blockPos, boolean insideBlock) {
        return new BlockHitResult(pos, direction, blockPos, insideBlock);
    }

    public static BlockHitResult create(Vec3 pos, Direction direction, BlockPos blockPos) {
        return new BlockHitResult(pos, direction, blockPos, false);
    }

    public static Vec3 getPos(BlockHitResult blockHitResult) {
        return blockHitResult.getLocation();
    }

    public static Direction getSide(BlockHitResult blockHitResult) {
        return blockHitResult.getDirection();
    }

    public static BlockPos getBlockPos(BlockHitResult blockHitResult) {
        return blockHitResult.getBlockPos();
    }

    public static boolean isInsideBlock(BlockHitResult blockHitResult) {
        return blockHitResult.isInside();
    }
}
