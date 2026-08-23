package net.pitan76.mcpitanlib.midohra.util.hit;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.Vec3;

import java.util.Optional;

public class HitResult {
    protected final net.minecraft.world.phys.HitResult raw;

    public HitResult(net.minecraft.world.phys.HitResult raw) {
        this.raw = raw;
    }

    public static HitResult of(net.minecraft.world.phys.HitResult raw) {
        return new HitResult(raw);
    }

    @Deprecated
    public net.minecraft.world.phys.HitResult getRaw() {
        return raw;
    }

    @Deprecated
    public net.minecraft.world.phys.HitResult.Type getRawType() {
        return getRaw().getType();
    }

    public Vec3 getPos() {
        return getRaw().getLocation();
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

    public double squaredDistanceTo(Vec3 point) {
        Vec3 hitPos = getPos();
        double dx = hitPos.x - point.x;
        double dy = hitPos.y - point.y;
        double dz = hitPos.z - point.z;
        return dx * dx + dy * dy + dz * dz;
    }

    public double squaredDistanceTo(Entity entity) {
        return getRaw().distanceTo(entity);
    }

    public Vec3 pos() {
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

        if (raw instanceof net.minecraft.world.phys.BlockHitResult) {
            return Optional.of(BlockHitResult.of((net.minecraft.world.phys.BlockHitResult) raw));
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
