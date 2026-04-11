package net.pitan76.mcpitanlib.api.util;

import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.phys.shapes.Shapes;

public class VoxelShapeUtil {
    public static VoxelShape union(VoxelShape shape, VoxelShape... shapes) {
        return Shapes.or(shape, shapes);
    }

    public static VoxelShape cuboid(double x1, double y1, double z1, double x2, double y2, double z2) {
        return Shapes.box(x1, y1, z1, x2, y2, z2);
    }

    public static VoxelShape cuboid(double x, double y, double z, double size) {
        return Shapes.box(x, y, z, x + size, y + size, z + size);
    }

    public static VoxelShape cuboid(double size) {
        return Shapes.box(0, 0, 0, size, size, size);
    }

    public static VoxelShape centeredCuboid(double x, double y, double z, double size) {
        return Shapes.box(x - size / 2, y - size / 2, z - size / 2, x + size / 2, y + size / 2, z + size / 2);
    }

    public static VoxelShape empty() {
        return Shapes.empty();
    }

    public static VoxelShape fullCube() {
        return Shapes.block();
    }

    public static VoxelShape blockCuboid(double minX, double minY, double minZ, double maxX, double maxY, double maxZ) {
        return Shapes.box(minX / 16.0, minY / 16.0, minZ / 16.0, maxX / 16.0, maxY / 16.0, maxZ / 16.0);
    }

    public static AABB getBoundingBox(VoxelShape shape) {
        return shape.bounds();
    }
}
