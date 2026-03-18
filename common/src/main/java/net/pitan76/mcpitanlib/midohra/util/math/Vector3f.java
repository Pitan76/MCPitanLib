package net.pitan76.mcpitanlib.midohra.util.math;

import net.minecraft.util.math.Vec3d;

public class Vector3f {
    public final float x;
    public final float y;
    public final float z;

    public Vector3f(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public static Vector3f of(float x, float y, float z) {
        return new Vector3f(x, y, z);
    }

    public static Vector3f of(Vector3i vec) {
        return new Vector3f(vec.x, vec.y, vec.z);
    }

    public static Vector3f of(Vector3d vec) {
        return new Vector3f((float) vec.x, (float) vec.y, (float) vec.z);
    }

    public Vector3i toInt() {
        return new Vector3i((int) x, (int) y, (int) z);
    }

    public Vector3d toDouble() {
        return new Vector3d((double) x, (double) y, (double) z);
    }

    public Vector3f add(Vector3f other) {
        return new Vector3f(this.x + other.x, this.y + other.y, this.z + other.z);
    }

    public Vector3f sub(Vector3f other) {
        return new Vector3f(this.x - other.x, this.y - other.y, this.z - other.z);
    }

    public Vector3f mul(float scalar) {
        return new Vector3f(this.x * scalar, this.y * scalar, this.z * scalar);
    }

    public Vector3f div(float scalar) {
        return new Vector3f(this.x / scalar, this.y / scalar, this.z / scalar);
    }

    public float dot(Vector3f other) {
        return this.x * other.x + this.y * other.y + this.z * other.z;
    }

    public Vector3f cross(Vector3f other) {
        return new Vector3f(
            this.y * other.z - this.z * other.y,
            this.z * other.x - this.x * other.z,
            this.x * other.y - this.y * other.x
        );
    }

    public float length() {
        return (float) Math.sqrt(x * x + y * y + z * z);
    }

    public Vector3f normalize() {
        float len = length();
        if (len == 0) return new Vector3f(0, 0, 0);
        return div(len);
    }

    public Vector3f add(float x, float y, float z) {
        return new Vector3f(this.x + x, this.y + y, this.z + z);
    }

    public Vector3f sub(float x, float y, float z) {
        return new Vector3f(this.x - x, this.y - y, this.z - z);
    }

    public Vector3f lerp(Vector3f to, float delta) {
        return this.mul(1 - delta).add(to.mul(delta));
    }

    public Vector3f rotateX(float angleDegrees) {
        double angleRadians = Math.toRadians(angleDegrees);
        float cos = (float) Math.cos(angleRadians);
        float sin = (float) Math.sin(angleRadians);
        float newY = y * cos - z * sin;
        float newZ = y * sin + z * cos;
        return new Vector3f(x, newY, newZ);
    }

    public Vector3f rotateY(float angleDegrees) {
        double angleRadians = Math.toRadians(angleDegrees);
        float cos = (float) Math.cos(angleRadians);
        float sin = (float) Math.sin(angleRadians);
        float newX = z * sin + x * cos;
        float newZ = z * cos - x * sin;
        return new Vector3f(newX, y, newZ);
    }

    public Vector3f rotateZ(float angleDegrees) {
        double angleRadians = Math.toRadians(angleDegrees);
        float cos = (float) Math.cos(angleRadians);
        float sin = (float) Math.sin(angleRadians);
        float newX = x * cos - y * sin;
        float newY = x * sin + y * cos;
        return new Vector3f(newX, newY, z);
    }

    public Vector3f negate() {
        return new Vector3f(-x, -y, -z);
    }

    public Vector3f abs() {
        return new Vector3f(Math.abs(x), Math.abs(y), Math.abs(z));
    }

    public Vector3f distanceTo(Vector3f other) {
        return this.sub(other);
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getZ() {
        return z;
    }

    public static Vector3f zero() {
        return new Vector3f(0, 0, 0);
    }

    public static Vector3f distance(Vector3f a, Vector3f b) {
        return a.sub(b);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Vector3f vec = (Vector3f) obj;
        return x == vec.x && y == vec.y && z == vec.z;
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ", " + z + ")";
    }

    @Override
    public int hashCode() {
        int h;
        h = Float.hashCode(x);
        h = 31 * h + Float.hashCode(y);
        h = 31 * h + Float.hashCode(z);
        return h;
    }

    public Vector3f ofCenter() {
        return this.add(0.5f, 0.5f, 0.5f);
    }

    public org.joml.Vector3f toMinecraft() {
        return new org.joml.Vector3f(x, y, z);
    }
}
