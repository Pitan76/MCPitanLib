package net.pitan76.mcpitanlib.api.event.entity;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;

public class EntityHitEvent {

    public EntityHitResult entityHitResult;

    public EntityHitEvent(EntityHitResult result) {
        this.entityHitResult = result;
    }

    public EntityHitResult getEntityHitResult() {
        return entityHitResult;
    }

    public Entity getEntity() {
        return entityHitResult.getEntity();
    }

    public HitResult.Type getType() {
        return entityHitResult.getType();
    }
}
