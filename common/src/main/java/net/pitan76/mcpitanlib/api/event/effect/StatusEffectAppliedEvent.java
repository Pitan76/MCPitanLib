package net.pitan76.mcpitanlib.api.event.effect;

import net.minecraft.entity.LivingEntity;
import net.pitan76.mcpitanlib.api.event.BaseEvent;
import net.pitan76.mcpitanlib.midohra.entity.EntityWrapper;

/**
 * 効果が付与された瞬間に呼ばれる。
 */
public class StatusEffectAppliedEvent extends BaseEvent {

    public LivingEntity entity;
    public int amplifier;

    public StatusEffectAppliedEvent(LivingEntity entity, int amplifier) {
        this.entity = entity;
        this.amplifier = amplifier;
    }

    public LivingEntity getEntity() {
        return entity;
    }

    public EntityWrapper getEntityWrapper() {
        return EntityWrapper.of(entity);
    }

    public int getAmplifier() {
        return amplifier;
    }

    public int getLevel() {
        return amplifier + 1;
    }
}
