package net.pitan76.mcpitanlib.api.util.math;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Position;
import net.minecraft.core.Vec3i;
import net.minecraft.util.math.*;
import net.minecraft.world.phys.Vec3;

public class PosUtil {
    public static BlockPos flooredBlockPos(double x, double y, double z) {
        return BlockPos.containing(x, y, z);
    }

    public static BlockPos flooredBlockPos(Position pos) {
        return BlockPos.containing(pos);
    }

    public static BlockPos flooredBlockPos(Vec3 pos) {
        return BlockPos.containing(pos);
    }

    public static net.pitan76.mcpitanlib.midohra.util.math.BlockPos midohraBlockPos(int x, int y, int z) {
        return net.pitan76.mcpitanlib.midohra.util.math.BlockPos.of(x, y, z);
    }

    public static net.pitan76.mcpitanlib.midohra.util.math.BlockPos flooredMidohraBlockPos(double x, double y, double z) {
        int x1 = (int) Math.floor(x);
        int y1 = (int) Math.floor(y);
        int z1 = (int) Math.floor(z);
        return midohraBlockPos(x1, y1, z1);
    }

    public static double getSquaredDistance(BlockPos pos1, BlockPos pos2) {
        return pos1.distSqr(pos2);
    }

    public static double getSquaredDistance(BlockPos pos1, double x, double y, double z) {
        return pos1.distToLowCornerSqr(x, y, z);
    }

    public static Iterable<BlockPos> iterate(BlockPos start, BlockPos end) {
        return BlockPos.betweenClosed(start, end);
    }

    public static BlockPos[] getNeighborPoses(BlockPos pos) {
        return new BlockPos[]{pos.north(), pos.south(), pos.east(), pos.west(), pos.above(), pos.below()};
    }

    public static BlockPos up(BlockPos pos) {
        return pos.above();
    }

    public static BlockPos down(BlockPos pos) {
        return pos.below();
    }

    public static BlockPos north(BlockPos pos) {
        return pos.north();
    }

    public static BlockPos south(BlockPos pos) {
        return pos.south();
    }

    public static BlockPos east(BlockPos pos) {
        return pos.east();
    }

    public static BlockPos west(BlockPos pos) {
        return pos.west();
    }

    public static BlockPos add(BlockPos pos, int x, int y, int z) {
        return pos.offset(x, y, z);
    }

    public static BlockPos sub(BlockPos pos, int x, int y, int z) {
        return pos.subtract(new Vec3i(x, y, z));
    }

    public static BlockPos mul(BlockPos pos, int n) {
        return pos.multiply(n);
    }

    public static int x(BlockPos pos) {
        return pos.getX();
    }

    public static int y(BlockPos pos) {
        return pos.getY();
    }

    public static int z(BlockPos pos) {
        return pos.getZ();
    }

    public static BlockPos offset(BlockPos pos, Direction direction) {
        return pos.relative(direction);
    }

    public static BlockPos offset(BlockPos pos, Direction direction, int n) {
        return pos.relative(direction, n);
    }

    public static BlockPos offset(BlockPos pos, net.pitan76.mcpitanlib.midohra.util.math.Direction direction) {
        return pos.relative(direction.toMinecraft());
    }

    public static BlockPos offset(BlockPos pos, net.pitan76.mcpitanlib.midohra.util.math.Direction direction, int n) {
        return pos.relative(direction.toMinecraft(), n);
    }

    public static BlockPos toImmutable(BlockPos pos) {
        return pos.immutable();
    }

    public static long asLong(BlockPos pos) {
        return pos.asLong();
    }
}
