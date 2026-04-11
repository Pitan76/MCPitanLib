package net.pitan76.mcpitanlib.api.util.math;

import net.minecraft.world.phys.Vec3;

public class Vec3dUtil {
    public static Vec3 create(double x, double y, double z) {
        return new Vec3(x, y, z);
    }

    public static Vec3 add(Vec3 a, Vec3 b) {
        return a.add(b);
    }

    public static Vec3 subtract(Vec3 a, Vec3 b) {
        return a.subtract(b);
    }

    public static Vec3 multiply(Vec3 a, double b) {
        return a.scale(b);
    }

    public static Vec3 divide(Vec3 a, double b) {
        return a.scale(1.0D / b);
    }

    public static double dot(Vec3 a, Vec3 b) {
        return a.dot(b);
    }

    public static Vec3 cross(Vec3 a, Vec3 b) {
        return a.cross(b);
    }

    public static Vec3 normalize(Vec3 a) {
        return a.normalize();
    }

    public static Vec3 rotateX(Vec3 a, float angle) {
        return a.xRot(angle);
    }

    public static Vec3 rotateY(Vec3 a, float angle) {
        return a.yRot(angle);
    }

    public static Vec3 rotateZ(Vec3 a, float angle) {
        return a.zRot(angle);
    }

    public static Vec3 add(Vec3 a, double x, double y, double z) {
        return a.add(x, y, z);
    }

    public static Vec3 subtract(Vec3 a, double x, double y, double z) {
        return a.subtract(x, y, z);
    }

    public static Vec3 multiply(Vec3 a, double x, double y, double z) {
        return a.multiply(x, y, z);
    }

    public static double distanceTo(Vec3 a, Vec3 b) {
        return a.distanceTo(b);
    }

    public static Vec3 ofCenter(double x, double y, double z) {
        return new Vec3(x + 0.5, y + 0.5, z + 0.5);
    }
}
