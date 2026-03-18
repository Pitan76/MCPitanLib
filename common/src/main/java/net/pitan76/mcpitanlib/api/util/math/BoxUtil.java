package net.pitan76.mcpitanlib.api.util.math;

import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;

public class BoxUtil {
    public static Box createBox(double x1, double y1, double z1, double x2, double y2, double z2) {
        return new Box(x1, y1, z1, x2, y2, z2);
    }

    public static Box createBox(double x, double y, double z, double size) {
        return new Box(x, y, z, x + size, y + size, z + size);
    }

    public static Box createBox(double size) {
        return new Box(0, 0, 0, size, size, size);
    }

    public static Box createBox(double x1, double y1, double z1, double x2, double y2, double z2, double size) {
        return new Box(x1, y1, z1, x2 + size, y2 + size, z2 + size);
    }

    public static Box createBox(BlockPos pos) {
        return new Box(pos);
    }

    public static Box createBox(BlockPos pos1, BlockPos pos2) {
        return new Box(pos1.toCenterPos(), pos2.toCenterPos());
    }

    public static Box createBox(BlockPos pos, int size) {
        return createBox(pos, pos.add(size, size, size));
    }

    public static Box createBox(BlockPos pos, int sizeX, int sizeY, int sizeZ) {
        return createBox(pos, pos.add(sizeX, sizeY, sizeZ));
    }

    public static Box createBoxCenter(BlockPos pos, int size) {
        return createBox(pos.add(-size, -size, -size), pos.add(size, size, size));
    }

    public static Box expand(Box box, double x, double y, double z) {
        return new Box(box.minX - x, box.minY - y, box.minZ - z, box.maxX + x, box.maxY + y, box.maxZ + z);
    }

    public static Box expand(Box box, double size) {
        return expand(box, size, size, size);
    }

    public static Box union(Box box1, Box box2) {
        return box1.union(box2);
    }
}
