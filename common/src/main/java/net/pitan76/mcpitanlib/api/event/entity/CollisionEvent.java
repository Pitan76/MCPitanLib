package net.pitan76.mcpitanlib.api.event.entity;

import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Vec3d;

public class CollisionEvent {

    public HitResult hitResult;

    public CollisionEvent(HitResult hitResult) {
        this.hitResult = hitResult;
    }

    public HitResult getHitResult() {
        return hitResult;
    }

    public HitResult.Type getType() {
        return hitResult.getType();
    }

    public Vec3d getPos() {
        return hitResult.getPos();
    }
}
