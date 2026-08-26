package net.pitan76.mcpitanlib.api.event.effect;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.pitan76.mcpitanlib.api.event.BaseEvent;

/**
 * 効果が持続中、毎tick呼ばれる。
 */
public class StatusEffectUpdateEvent extends BaseEvent {

    public ServerLevel world;
    public LivingEntity entity;
    public int amplifier;

    public StatusEffectUpdateEvent(ServerLevel world, LivingEntity entity, int amplifier) {
        this.world = world;
        this.entity = entity;
        this.amplifier = amplifier;
    }

    public Level getWorld() {
        return world;
    }

    public ServerLevel getServerWorld() {
        return world;
    }

    public net.pitan76.mcpitanlib.midohra.world.World getMidohraWorld() {
        return net.pitan76.mcpitanlib.midohra.world.World.of(world);
    }

    public LivingEntity getEntity() {
        return entity;
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
