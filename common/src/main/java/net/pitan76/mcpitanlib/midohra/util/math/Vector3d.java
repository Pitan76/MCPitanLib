package net.pitan76.mcpitanlib.midohra.util.math;

import net.minecraft.util.math.Position;
import net.minecraft.util.math.Vec3d;

public class Vector3d {
    public final double x;
    public final double y;
    public final double z;

    public Vector3d(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public static Vector3d of(double x, double y, double z) {
        return new Vector3d(x, y, z);
    }

    public static Vector3d of(Vector3i vec) {
        return new Vector3d(vec.x, vec.y, vec.z);
    }

    public static Vector3d of(Vector3f vec) {
        return new Vector3d(vec.x, vec.y, vec.z);
    }

    public static Vector3d of(org.joml.Vector3d vec) {
        return new Vector3d(vec.x, vec.y, vec.z);
    }

    public static Vector3d of(Vec3d vec) {
        return new Vector3d(vec.x, vec.y, vec.z);
    }

    public static Vector3d of(Position vec) {
        return new Vector3d(vec.getX(), vec.getY(), vec.getZ());
    }

    /**
     * ブロック座標として扱うため切り捨て(floor)で変換する。
     * 負の座標で1ズレるため、(int)キャストによる0方向への切り捨ては行わない。
     */
    public Vector3i toInt() {
        return new Vector3i((int) Math.floor(x), (int) Math.floor(y), (int) Math.floor(z));
    }

    /**
     * 0方向へ切り捨てて変換する (負の座標では{@link #toInt()}と結果が異なる)
     */
    public Vector3i toTruncatedInt() {
        return new Vector3i((int) x, (int) y, (int) z);
    }

    public BlockPos toBlockPos() {
        return toInt().toPos();
    }

    public Vector3f toFloat() {
        return new Vector3f((float) x, (float) y, (float) z);
    }

    public org.joml.Vector3d toJoml() {
        return new org.joml.Vector3d(x, y, z);
    }

    public Vector3d add(Vector3d other) {
        return new Vector3d(this.x + other.x, this.y + other.y, this.z + other.z);
    }

    public Vector3d sub(Vector3d other) {
        return new Vector3d(this.x - other.x, this.y - other.y, this.z - other.z);
    }

    public Vector3d mul(double scalar) {
        return new Vector3d(this.x * scalar, this.y * scalar, this.z * scalar);
    }

    public Vector3d div(double scalar) {
        return new Vector3d(this.x / scalar, this.y / scalar, this.z / scalar);
    }

    public double dot(Vector3d other) {
        return this.x * other.x + this.y * other.y + this.z * other.z;
    }

    public Vector3d cross(Vector3d other) {
        return new Vector3d(
            this.y * other.z - this.z * other.y,
            this.z * other.x - this.x * other.z,
            this.x * other.y - this.y * other.x
        );
    }

    public double length() {
        return Math.sqrt(x * x + y * y + z * z);
    }

    public Vector3d normalize() {
        double len = length();
        if (len == 0) return new Vector3d(0, 0, 0);
        return div(len);
    }

    public Vector3d add(double x, double y, double z) {
        return new Vector3d(this.x + x, this.y + y, this.z + z);
    }

    public Vector3d sub(double x, double y, double z) {
        return new Vector3d(this.x - x, this.y - y, this.z - z);
    }

    public Vector3d lerp(Vector3d to, double delta) {
        return this.mul(1 - delta).add(to.mul(delta));
    }

    public Vector3d rotateX(double angleDegrees) {
        double angleRadians = Math.toRadians(angleDegrees);
        double cos = Math.cos(angleRadians);
        double sin = Math.sin(angleRadians);
        double newY = y * cos - z * sin;
        double newZ = y * sin + z * cos;
        return new Vector3d(x, newY, newZ);
    }

    public Vector3d rotateY(double angleDegrees) {
        double angleRadians = Math.toRadians(angleDegrees);
        double cos = Math.cos(angleRadians);
        double sin = Math.sin(angleRadians);
        double newX = x * cos - z * sin;
        double newZ = x * sin + z * cos;
        return new Vector3d(newX, y, newZ);
    }

    public Vector3d rotateZ(double angleDegrees) {
        double angleRadians = Math.toRadians(angleDegrees);
        double cos = Math.cos(angleRadians);
        double sin = Math.sin(angleRadians);
        double newX = x * cos - y * sin;
        double newY = x * sin + y * cos;
        return new Vector3d(newX, newY, z);
    }

    public Vector3d negate() {
        return new Vector3d(-x, -y, -z);
    }

    public Vector3d abs() {
        return new Vector3d(Math.abs(x), Math.abs(y), Math.abs(z));
    }

    public Vector3d distanceTo(Vector3d other) {
        return this.sub(other);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    public static Vector3d zero() {
        return new Vector3d(0, 0, 0);
    }

    public static Vector3d distance(Vector3d a, Vector3d b) {
        return a.sub(b);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Vector3d vec = (Vector3d) obj;
        return x == vec.x && y == vec.y && z == vec.z;
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ", " + z + ")";
    }

    @Override
    public int hashCode() {
        int h;
        h = Double.hashCode(x);
        h = 31 * h + Double.hashCode(y);
        h = 31 * h + Double.hashCode(z);
        return h;
    }

    public Vector3d ofCenter() {
        return new Vector3d(x + 0.5, y + 0.5, z + 0.5);
    }

    public Vec3d toMinecraft() {
        return new Vec3d(x, y, z);
    }

    public Vector3d mul(Vector3d other) {
        return new Vector3d(this.x * other.x, this.y * other.y, this.z * other.z);
    }

    public Vector3d mul(double x, double y, double z) {
        return new Vector3d(this.x * x, this.y * y, this.z * z);
    }

    public Vector3d div(Vector3d other) {
        return new Vector3d(this.x / other.x, this.y / other.y, this.z / other.z);
    }

    public Vector3d div(double x, double y, double z) {
        return new Vector3d(this.x / x, this.y / y, this.z / z);
    }

    public Vector3d mod(Vector3d other) {
        return new Vector3d(this.x % other.x, this.y % other.y, this.z % other.z);
    }

    public Vector3d mod(double x, double y, double z) {
        return new Vector3d(this.x % x, this.y % y, this.z % z);
    }

    public Vector3d floor() {
        return new Vector3d(Math.floor(x), Math.floor(y), Math.floor(z));
    }

    public long asLong() {
        return toInt().asLong();
    }
}
