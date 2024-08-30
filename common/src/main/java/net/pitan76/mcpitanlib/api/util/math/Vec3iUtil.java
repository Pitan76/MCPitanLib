package net.pitan76.mcpitanlib.api.util.math;

import net.minecraft.util.math.Vec3i;

public class Vec3iUtil {
    public static Vec3i create(int x, int y, int z) {
        return new Vec3i(x, y, z);
    }

    public static Vec3i add(Vec3i a, Vec3i b) {
        return new Vec3i(a.getX() + b.getX(), a.getY() + b.getY(), a.getZ() + b.getZ());
    }

    public static Vec3i subtract(Vec3i a, Vec3i b) {
        return new Vec3i(a.getX() - b.getX(), a.getY() - b.getY(), a.getZ() - b.getZ());
    }

    public static Vec3i multiply(Vec3i a, int b) {
        return new Vec3i(a.getX() * b, a.getY() * b, a.getZ() * b);
    }

    public static Vec3i cross(Vec3i a, Vec3i b) {
        return a.crossProduct(b);
    }

    public static Vec3i add(Vec3i a, int x, int y, int z) {
        return new Vec3i(a.getX() + x, a.getY() + y, a.getZ() + z);
    }

    public static Vec3i subtract(Vec3i a, int x, int y, int z) {
        return new Vec3i(a.getX() - x, a.getY() - y, a.getZ() - z);
    }
}
