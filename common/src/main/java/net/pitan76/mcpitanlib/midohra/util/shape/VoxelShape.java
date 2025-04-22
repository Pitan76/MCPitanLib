package net.pitan76.mcpitanlib.midohra.util.shape;

import net.minecraft.util.shape.VoxelShapes;
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
}
