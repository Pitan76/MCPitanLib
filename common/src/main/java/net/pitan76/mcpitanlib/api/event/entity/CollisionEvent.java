package net.pitan76.mcpitanlib.api.event.entity;

import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.pitan76.mcpitanlib.midohra.util.hit.HitResultType;

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

    public Vec3 getPos() {
        return hitResult.getLocation();
    }

    public net.pitan76.mcpitanlib.midohra.util.hit.HitResult getHitResultM() {
        return net.pitan76.mcpitanlib.midohra.util.hit.HitResult.of(getHitResult());
    }

    public HitResultType getHitResultTypeM() {
        return HitResultType.from(getType());
    }
}
