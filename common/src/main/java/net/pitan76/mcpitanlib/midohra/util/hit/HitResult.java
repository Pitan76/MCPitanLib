package net.pitan76.mcpitanlib.midohra.util.hit;

import net.minecraft.entity.Entity;
import net.minecraft.util.math.Vec3d;

import java.util.Optional;

public class HitResult {
    protected final net.minecraft.util.hit.HitResult raw;

    public HitResult(net.minecraft.util.hit.HitResult raw) {
        this.raw = raw;
    }

    public static HitResult of(net.minecraft.util.hit.HitResult raw) {
        return new HitResult(raw);
    }

    @Deprecated
    public net.minecraft.util.hit.HitResult getRaw() {
        return raw;
    }

    @Deprecated
    public net.minecraft.util.hit.HitResult.Type getRawType() {
        return getRaw().getType();
    }

    public Vec3d getPos() {
        return getRaw().getPos();
    }

    public double getX() {
        return pos().x;
    }

    public double getY() {
        return pos().y;
    }

    public double getZ() {
        return pos().z;
    }

    public HitResultType getType() {
        return HitResultType.of(this);
    }

    public double squaredDistanceTo(Vec3d point) {
        Vec3d hitPos = getPos();
        double dx = hitPos.x - point.x;
        double dy = hitPos.y - point.y;
        double dz = hitPos.z - point.z;
        return dx * dx + dy * dy + dz * dz;
    }

    public double squaredDistanceTo(Entity entity) {
        return getRaw().squaredDistanceTo(entity);
    }

    public Vec3d pos() {
        return getPos();
    }

    public double x() {
        return getX();
    }

    public double y() {
        return getY();
    }

    public double z() {
        return getZ();
    }

    public Optional<BlockHitResult> asBlockHitResult() {
        if (this instanceof BlockHitResult) {
            return Optional.of((BlockHitResult) this);
        }

        if (raw instanceof net.minecraft.util.hit.BlockHitResult) {
            return Optional.of(BlockHitResult.of((net.minecraft.util.hit.BlockHitResult) raw));
        }

        return Optional.empty();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof HitResult)) return false;
        HitResult other = (HitResult) obj;
        return getRaw().equals(other.getRaw());
    }

    @Override
    public int hashCode() {
        return getRaw().hashCode();
    }

    @Override
    public String toString() {
        return getRaw().toString();
    }
}
