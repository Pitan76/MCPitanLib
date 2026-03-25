package net.pitan76.mcpitanlib.api.util.math;

import net.minecraft.util.math.Vec3i;

public class Vec3iUtil {
    public static Vec3i create(int x, int y, int z) {
        return new Vec3i(x, y, z);
    }

    public static Vec3i add(Vec3i a, Vec3i b) {
        return a.add(b);
    }

    public static Vec3i subtract(Vec3i a, Vec3i b) {
        return a.subtract(b);
    }

    public static Vec3i multiply(Vec3i a, int b) {
        return a.multiply(b);
    }

    public static Vec3i cross(Vec3i a, Vec3i b) {
        return a.crossProduct(b);
    }

    public static Vec3i add(Vec3i a, int x, int y, int z) {
        return a.add(x, y, z);
    }

    public static Vec3i subtract(Vec3i a, int x, int y, int z) {
        return a.add(-x, -y, -z);
    }
}
