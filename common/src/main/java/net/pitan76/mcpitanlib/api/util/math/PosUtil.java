package net.pitan76.mcpitanlib.api.util.math;

import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Position;
import net.minecraft.util.math.Vec3d;

public class PosUtil {
    public static BlockPos flooredBlockPos(double x, double y, double z) {
        return new BlockPos(x, y, z);
    }

    public static BlockPos flooredBlockPos(Position pos) {
        return new BlockPos(pos);
    }

    public static BlockPos flooredBlockPos(Vec3d pos) {
        return new BlockPos(pos);
    }

    public static double getSquaredDistance(BlockPos pos1, BlockPos pos2) {
        return pos1.getSquaredDistance(pos2);
    }

    public static double getSquaredDistance(BlockPos pos1, double x, double y, double z) {
        return getSquaredDistance(pos1, new BlockPos(x, y, z));
    }

    public static Iterable<BlockPos> iterate(BlockPos start, BlockPos end) {
        return BlockPos.iterate(start, end);
    }

    public static BlockPos[] getNeighborPoses(BlockPos pos) {
        return new BlockPos[]{pos.north(), pos.south(), pos.east(), pos.west(), pos.up(), pos.down()};
    }
}
