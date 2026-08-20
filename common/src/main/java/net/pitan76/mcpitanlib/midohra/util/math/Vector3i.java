package net.pitan76.mcpitanlib.midohra.util.math;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;

public class Vector3i {
    public final int x;
    public final int y;
    public final int z;

    public Vector3i(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public static Vector3i of(int x, int y, int z) {
        return new Vector3i(x, y, z);
    }

    public static Vector3i of(Vector3d vec) {
        return new Vector3i((int) vec.x, (int) vec.y, (int) vec.z);
    }

    public static Vector3i of(Vector3f vec) {
        return new Vector3i((int) vec.x, (int) vec.y, (int) vec.z);
    }

    public static Vector3i of(org.joml.Vector3i vec) {
        return new Vector3i(vec.x, vec.y, vec.z);
    }

    public static Vector3i of(Vec3i vec) {
        return new Vector3i(vec.getX(), vec.getY(), vec.getZ());
    }

    public static Vector3i of(BlockPos vec) {
        return new Vector3i(vec.getX(), vec.getY(), vec.getZ());
    }

    public static Vector3i of(net.pitan76.mcpitanlib.midohra.util.math.BlockPos vec) {
        return new Vector3i(vec.getX(), vec.getY(), vec.getZ());
    }

    public static Vector3i of(net.pitan76.mcpitanlib.midohra.util.math.v0.BlockPos vec) {
        return new Vector3i(vec.getX(), vec.getY(), vec.getZ());
    }

    public Vector3d toDouble() {
        return new Vector3d(x, y, z);
    }

    public Vector3f toFloat() {
        return new Vector3f((float) x, (float) y, (float) z);
    }

    public org.joml.Vector3i toJoml() {
        return new org.joml.Vector3i(x, y, z);
    }

    public Vector3i add(Vector3i other) {
        return new Vector3i(this.x + other.x, this.y + other.y, this.z + other.z);
    }

    public Vector3i sub(Vector3i other) {
        return new Vector3i(this.x - other.x, this.y - other.y, this.z - other.z);
    }

    public Vector3i mul(int scalar) {
        return new Vector3i(this.x * scalar, this.y * scalar, this.z * scalar);
    }

    public Vector3i div(int scalar) {
        return new Vector3i(this.x / scalar, this.y / scalar, this.z / scalar);
    }

    public int dot(Vector3i other) {
        return this.x * other.x + this.y * other.y + this.z * other.z;
    }

    public Vector3i cross(Vector3i other) {
        return new Vector3i(
            this.y * other.z - this.z * other.y,
            this.z * other.x - this.x * other.z,
            this.x * other.y - this.y * other.x
        );
    }

    public int length() {
        return (int) Math.sqrt(x * x + y * y + z * z);
    }

    public Vector3i normalize() {
        int len = length();
        if (len == 0) return new Vector3i(0, 0, 0);
        return div(len);
    }

    public Vector3i add(int x, int y, int z) {
        return new Vector3i(this.x + x, this.y + y, this.z + z);
    }

    public Vector3i sub(int x, int y, int z) {
        return new Vector3i(this.x - x, this.y - y, this.z - z);
    }

    public Vector3i lerp(Vector3i to, int delta) {
        return this.mul(1 - delta).add(to.mul(delta));
    }

    public Vector3i rotateX(double angleDegrees) {
        double angleRadians = Math.toRadians(angleDegrees);
        int cos = (int) Math.cos(angleRadians);
        int sin = (int) Math.sin(angleRadians);
        int newY = this.y * cos - this.z * sin;
        int newZ = this.y * sin + this.z * cos;
        return new Vector3i(this.x, newY, newZ);
    }

    public Vector3i rotateY(double angleDegrees) {
        double angleRadians = Math.toRadians(angleDegrees);
        int cos = (int) Math.cos(angleRadians);
        int sin = (int) Math.sin(angleRadians);
        int newX = this.z * sin + this.x * cos;
        int newZ = this.z * cos - this.x * sin;
        return new Vector3i(newX, this.y, newZ);
    }

    public Vector3i rotateZ(double angleDegrees) {
        double angleRadians = Math.toRadians(angleDegrees);
        int cos = (int) Math.cos(angleRadians);
        int sin = (int) Math.sin(angleRadians);
        int newX = this.x * cos - this.y * sin;
        int newY = this.x * sin + this.y * cos;
        return new Vector3i(newX, newY, this.z);
    }

    public Vector3i negate() {
        return new Vector3i(-this.x, -this.y, -this.z);
    }

    public Vector3i abs() {
        return new Vector3i(Math.abs(this.x), Math.abs(this.y), Math.abs(this.z));
    }

    public Vector3i distanceTo(Vector3i other) {
        return this.sub(other);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getZ() {
        return z;
    }

    public static Vector3i zero() {
        return new Vector3i(0, 0, 0);
    }

    public static Vector3i distance(Vector3i a, Vector3i b) {
        return a.sub(b);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Vector3i vec = (Vector3i) obj;
        return x == vec.x && y == vec.y && z == vec.z;
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ", " + z + ")";
    }

    @Override
    public int hashCode() {
        int h;
        h = Integer.hashCode(x);
        h = 31 * h + Integer.hashCode(y);
        h = 31 * h + Integer.hashCode(z);
        return h;
    }

    public Vec3i toMinecraft() {
        return new Vec3i(x, y, z);
    }

    public Vector3i mul(Vector3i other) {
        return new Vector3i(this.x * other.x, this.y * other.y, this.z * other.z);
    }

    public Vector3i mul(int x, int y, int z) {
        return new Vector3i(this.x * x, this.y * y, this.z * z);
    }

    public Vector3i div(Vector3i other) {
        return new Vector3i(this.x / other.x, this.y / other.y, this.z / other.z);
    }

    public Vector3i div(int x, int y, int z) {
        return new Vector3i(this.x / x, this.y / y, this.z / z);
    }

    public Vector3i mod(Vector3i other) {
        return new Vector3i(this.x % other.x, this.y % other.y, this.z % other.z);
    }

    public Vector3i mod(int x, int y, int z) {
        return new Vector3i(this.x % x, this.y % y, this.z % z);
    }

    public long asLong() {
        return net.minecraft.core.BlockPos.asLong(x, y, z);
    }

    public net.pitan76.mcpitanlib.midohra.util.math.BlockPos toPos() {
        return net.pitan76.mcpitanlib.midohra.util.math.BlockPos.of(x, y, z);
    }

    public Vector3d toCenter() {
        return new Vector3d(x + 0.5, y + 0.5, z + 0.5);
    }
}
