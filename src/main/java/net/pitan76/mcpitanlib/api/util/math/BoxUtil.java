package net.pitan76.mcpitanlib.api.util.math;

import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.AABB;

public class BoxUtil {
    public static AABB createBox(double x1, double y1, double z1, double x2, double y2, double z2) {
        return new AABB(x1, y1, z1, x2, y2, z2);
    }

    public static AABB createBox(double x, double y, double z, double size) {
        return new AABB(x, y, z, x + size, y + size, z + size);
    }

    public static AABB createBox(double size) {
        return new AABB(0, 0, 0, size, size, size);
    }

    public static AABB createBox(double x1, double y1, double z1, double x2, double y2, double z2, double size) {
        return new AABB(x1, y1, z1, x2 + size, y2 + size, z2 + size);
    }

    public static AABB createBox(BlockPos pos) {
        return new AABB(pos);
    }

    public static AABB createBox(BlockPos pos1, BlockPos pos2) {
        return new AABB(pos1.getCenter(), pos2.getCenter());
    }

    public static AABB createBox(BlockPos pos, int size) {
        return createBox(pos, pos.offset(size, size, size));
    }

    public static AABB createBox(BlockPos pos, int sizeX, int sizeY, int sizeZ) {
        return createBox(pos, pos.offset(sizeX, sizeY, sizeZ));
    }

    public static AABB createBoxCenter(BlockPos pos, int size) {
        return createBox(pos.offset(-size, -size, -size), pos.offset(size, size, size));
    }

    public static AABB expand(AABB box, double x, double y, double z) {
        return new AABB(box.minX - x, box.minY - y, box.minZ - z, box.maxX + x, box.maxY + y, box.maxZ + z);
    }

    public static AABB expand(AABB box, double size) {
        return expand(box, size, size, size);
    }

    public static AABB union(AABB box1, AABB box2) {
        return box1.minmax(box2);
    }
}
