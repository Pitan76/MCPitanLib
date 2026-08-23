package net.pitan76.mcpitanlib.midohra.util.shape;

import net.minecraft.util.shape.VoxelShapes;
import net.pitan76.mcpitanlib.api.util.VoxelShapeUtil;
import net.pitan76.mcpitanlib.midohra.util.math.Box;
import net.pitan76.mcpitanlib.midohra.util.math.Direction;

public class VoxelShape {
    private final net.minecraft.util.shape.VoxelShape voxelShape;

    public static final VoxelShape EMPTY = of(net.minecraft.util.shape.VoxelShapes.empty());
    public static final VoxelShape FULL_CUBE = of(net.minecraft.util.shape.VoxelShapes.fullCube());

    public VoxelShape(net.minecraft.util.shape.VoxelShape voxelShape) {
        this.voxelShape = voxelShape;
    }

    public static VoxelShape of(net.minecraft.util.shape.VoxelShape shape) {
        return new VoxelShape(shape);
    }

    @Deprecated
    public net.minecraft.util.shape.VoxelShape raw() {
        return voxelShape;
    }

    public net.minecraft.util.shape.VoxelShape toMinecraft() {
        return voxelShape;
    }

    public boolean isEmpty() {
        return raw().isEmpty();
    }

    public VoxelShape union(VoxelShape other) {
        return of(net.minecraft.util.shape.VoxelShapes.union(this.raw(), other.raw()));
    }

    public VoxelShape asCuboid() {
        return of(VoxelShapes.cuboid(raw().getBoundingBox()));
    }

    public VoxelShape offset(double x, double y, double z) {
        return of(raw().offset(x, y, z));
    }

    public VoxelShape getFace(Direction direction) {
        return of(raw().getFace(direction.toMinecraft()));
    }

    public VoxelShape simplify() {
        return of(raw().simplify());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof VoxelShape)) return false;
        VoxelShape other = (VoxelShape) obj;
        return this.raw().equals(other.raw());
    }

    @Override
    public int hashCode() {
        return raw().hashCode();
    }

    public static VoxelShape empty() {
        return EMPTY;
    }

    public static VoxelShape fullCube() {
        return FULL_CUBE;
    }

    public static VoxelShape union(VoxelShape... shapes) {
        net.minecraft.util.shape.VoxelShape result = net.minecraft.util.shape.VoxelShapes.empty();
        for (VoxelShape shape : shapes) {
            result = net.minecraft.util.shape.VoxelShapes.union(result, shape.raw());
        }
        return of(result);
    }

    public static VoxelShape cuboid(double x1, double y1, double z1, double x2, double y2, double z2) {
        return of(VoxelShapeUtil.cuboid(x1, y1, z1, x2, y2, z2));
    }

    public static VoxelShape cuboid(double size) {
        return of(VoxelShapeUtil.cuboid(size));
    }

    public static VoxelShape cuboid(double x1, double y1, double z1, double size) {
        return of(VoxelShapeUtil.cuboid(x1, y1, z1, size));
    }

    public static VoxelShape centeredCuboid(double x, double y, double z, double size) {
        return of(VoxelShapeUtil.centeredCuboid(x, y, z, size));
    }

    public static VoxelShape blockCuboid(double x1, double y1, double z1, double x2, double y2, double z2) {
        return of(VoxelShapeUtil.blockCuboid(x1, y1, z1, x2, y2, z2));
    }

    public Box getBoundingBox() {
        return new Box(VoxelShapeUtil.getBoundingBox(raw()));
    }
}
