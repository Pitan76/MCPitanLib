package net.pitan76.mcpitanlib.midohra.util.math;

import net.minecraft.util.math.Vec3d;
import net.pitan76.mcpitanlib.api.util.math.BoxUtil;

public class Box {
    protected final net.minecraft.util.math.Box box;

    @Deprecated
    public Box(net.minecraft.util.math.Box box) {
        this.box = box;
    }

    @Deprecated
    public net.minecraft.util.math.Box toMinecraft() {
        return box;
    }

    public Box(double x1, double y1, double z1, double x2, double y2, double z2) {
        this(BoxUtil.createBox(x1, y1, z1, x2, y2, z2));
    }

    public Box(double x, double y, double z, double size) {
        this(x, y, z, x + size, y + size, z + size);
    }

    public Box(double size) {
        this(0, 0, 0, size, size, size);
    }

    public Box(BlockPos pos) {
        this(pos.getX(), pos.getY(), pos.getZ(), pos.getX() + 1, pos.getY() + 1, pos.getZ() + 1);
    }

    public Box(net.pitan76.mcpitanlib.midohra.util.math.v0.BlockPos pos) {
        this(pos.getX(), pos.getY(), pos.getZ(), pos.getX() + 1, pos.getY() + 1, pos.getZ() + 1);
    }

    public Box(BlockPos pos1, BlockPos pos2) {
        this(pos1.getX(), pos1.getY(), pos1.getZ(), pos2.getX(), pos2.getY(), pos2.getZ());
    }

    public Box(net.pitan76.mcpitanlib.midohra.util.math.v0.BlockPos pos1, net.pitan76.mcpitanlib.midohra.util.math.v0.BlockPos pos2) {
        this(pos1.getX(), pos1.getY(), pos1.getZ(), pos2.getX(), pos2.getY(), pos2.getZ());
    }

    public Box(Vector3i pos) {
        this(pos.getX(), pos.getY(), pos.getZ(), pos.getX() + 1, pos.getY() + 1, pos.getZ() + 1);
    }

    public Box(Vector3i pos1, Vector3i pos2) {
        this(pos1.getX(), pos1.getY(), pos1.getZ(), pos2.getX(), pos2.getY(), pos2.getZ());
    }

    public Box(Vector3d pos) {
        this(pos.getX(), pos.getY(), pos.getZ(), pos.getX() + 1, pos.getY() + 1, pos.getZ() + 1);
    }

    public Box(Vector3d pos1, Vector3d pos2) {
        this(pos1.getX(), pos1.getY(), pos1.getZ(), pos2.getX(), pos2.getY(), pos2.getZ());
    }

    public Box expand(double x, double y, double z) {
        return new Box(BoxUtil.expand(box, x, y, z));
    }

    public Box expand(double size) {
        return expand(size, size, size);
    }

    public Box union(Box other) {
        return new Box(BoxUtil.union(box, other.box));
    }

    public double getMinX() {
        return box.minX;
    }

    public double getMinY() {
        return box.minY;
    }

    public double getMinZ() {
        return box.minZ;
    }

    public double getMaxX() {
        return box.maxX;
    }

    public double getMaxY() {
        return box.maxY;
    }

    public double getMaxZ() {
        return box.maxZ;
    }

    public Vector3d getCenter() {
        Vec3d center = box.getCenter();
        return new Vector3d(center.x, center.y, center.z);
    }

    public double getLengthX() {
        return box.getLengthX();
    }

    public double getLengthY() {
        return box.getLengthY();
    }

    public double getLengthZ() {
        return box.getLengthZ();
    }

    @Override
    public int hashCode() {
        return box.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Box other = (Box) obj;
        return box.equals(other.box);
    }
}
