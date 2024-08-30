package net.pitan76.mcpitanlib.api.util.math;

import net.minecraft.util.math.Vec3d;

public class Vec3dUtil {
    public static Vec3d create(double x, double y, double z) {
        return new Vec3d(x, y, z);
    }

    public static Vec3d add(Vec3d a, Vec3d b) {
        return a.add(b);
    }

    public static Vec3d subtract(Vec3d a, Vec3d b) {
        return a.subtract(b);
    }

    public static Vec3d multiply(Vec3d a, double b) {
        return a.multiply(b);
    }

    public static Vec3d divide(Vec3d a, double b) {
        return a.multiply(1.0D / b);
    }

    public static double dot(Vec3d a, Vec3d b) {
        return a.dotProduct(b);
    }

    public static Vec3d cross(Vec3d a, Vec3d b) {
        return a.crossProduct(b);
    }

    public static Vec3d normalize(Vec3d a) {
        return a.normalize();
    }

    public static Vec3d rotateX(Vec3d a, float angle) {
        return a.rotateX(angle);
    }

    public static Vec3d rotateY(Vec3d a, float angle) {
        return a.rotateY(angle);
    }

    public static Vec3d rotateZ(Vec3d a, float angle) {
        return a.rotateZ(angle);
    }

    public static Vec3d add(Vec3d a, double x, double y, double z) {
        return a.add(x, y, z);
    }

    public static Vec3d subtract(Vec3d a, double x, double y, double z) {
        return a.subtract(x, y, z);
    }

    public static Vec3d multiply(Vec3d a, double x, double y, double z) {
        return a.multiply(x, y, z);
    }

    public static double distanceTo(Vec3d a, Vec3d b) {
        return a.distanceTo(b);
    }




}
