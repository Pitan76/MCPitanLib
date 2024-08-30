package net.pitan76.mcpitanlib.api.util;

import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;

public class VoxelShapeUtil {
    public static VoxelShape union(VoxelShape shape,VoxelShape... shapes) {
        return VoxelShapes.union(shape, shapes);
    }

    public static VoxelShape cuboid(double x1, double y1, double z1, double x2, double y2, double z2) {
        return VoxelShapes.cuboid(x1, y1, z1, x2, y2, z2);
    }

    public static VoxelShape cuboid(double x, double y, double z, double size) {
        return VoxelShapes.cuboid(x, y, z, x + size, y + size, z + size);
    }

    public static VoxelShape cuboid(double size) {
        return VoxelShapes.cuboid(0, 0, 0, size, size, size);
    }

    public static VoxelShape centeredCuboid(double x, double y, double z, double size) {
        return VoxelShapes.cuboid(x - size / 2, y - size / 2, z - size / 2, x + size / 2, y + size / 2, z + size / 2);
    }

    public static VoxelShape empty() {
        return VoxelShapes.empty();
    }

    public static VoxelShape fullCube() {
        return VoxelShapes.fullCube();
    }

}
