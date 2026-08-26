package net.pitan76.mcpitanlib.api.event.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import net.pitan76.mcpitanlib.api.event.BaseEvent;
import net.pitan76.mcpitanlib.midohra.entity.EntityWrapper;

/**
 * 効果が持続中、毎tick呼ばれる。
 */
public class StatusEffectUpdateEvent extends BaseEvent {

    public ServerWorld world;
    public LivingEntity entity;
    public int amplifier;

    public StatusEffectUpdateEvent(ServerWorld world, LivingEntity entity, int amplifier) {
        this.world = world;
        this.entity = entity;
        this.amplifier = amplifier;
    }

    public World getWorld() {
        return world;
    }

    public ServerWorld getServerWorld() {
        return world;
    }

    public net.pitan76.mcpitanlib.midohra.world.World getMidohraWorld() {
        return net.pitan76.mcpitanlib.midohra.world.World.of(world);
    }

    public LivingEntity getEntity() {
        return entity;
    }

    public EntityWrapper getEntityWrapper() {
        return EntityWrapper.of(entity);
    }

    /**
     * 効果レベル。レベルIは0、レベルIIは1。
     */
    public int getAmplifier() {
        return amplifier;
    }

    public int getLevel() {
        return amplifier + 1;
    }
}
