package net.pitan76.mcpitanlib.api.event.entity;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.pitan76.mcpitanlib.midohra.entity.EntityWrapper;
import net.pitan76.mcpitanlib.midohra.util.hit.HitResultType;

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

    public EntityWrapper getEntityWrapper() {
        return EntityWrapper.of(getEntity());
    }

    public HitResultType getTypeM() {
        return HitResultType.from(getType());
    }
}
