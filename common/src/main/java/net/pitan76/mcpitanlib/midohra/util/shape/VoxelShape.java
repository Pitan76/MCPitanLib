package net.pitan76.mcpitanlib.midohra.util.shape;

import net.pitan76.mcpitanlib.midohra.util.math.Direction;

public class VoxelShape {
    private final net.minecraft.world.phys.shapes.VoxelShape voxelShape;

    public static final VoxelShape EMPTY = of(net.minecraft.world.phys.shapes.Shapes.empty());
    public static final VoxelShape FULL_CUBE = of(net.minecraft.world.phys.shapes.Shapes.block());

    public VoxelShape(net.minecraft.world.phys.shapes.VoxelShape voxelShape) {
        this.voxelShape = voxelShape;
    }

    public static VoxelShape of(net.minecraft.world.phys.shapes.VoxelShape shape) {
        return new VoxelShape(shape);
    }

    @Deprecated
    public net.minecraft.world.phys.shapes.VoxelShape raw() {
        return voxelShape;
    }

    public boolean isEmpty() {
        return raw().isEmpty();
    }

    public VoxelShape union(VoxelShape other) {
        return of(net.minecraft.world.phys.shapes.Shapes.or(this.raw(), other.raw()));
    }

    public VoxelShape asCuboid() {
        return of(raw().singleEncompassing());
    }

    public VoxelShape offset(double x, double y, double z) {
        return of(raw().move(x, y, z));
    }

    public VoxelShape getFace(Direction direction) {
        return of(raw().getFaceShape(direction.toMinecraft()));
    }

    public VoxelShape simplify() {
        return of(raw().optimize());
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
        net.minecraft.world.phys.shapes.VoxelShape result = net.minecraft.world.phys.shapes.Shapes.empty();
        for (VoxelShape shape : shapes) {
            result = net.minecraft.world.phys.shapes.Shapes.or(result, shape.raw());
        }
        return of(result);
    }
}
