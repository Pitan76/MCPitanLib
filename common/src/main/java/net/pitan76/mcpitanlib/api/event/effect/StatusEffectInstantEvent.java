package net.pitan76.mcpitanlib.api.event.effect;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import net.pitan76.mcpitanlib.api.event.BaseEvent;
import net.pitan76.mcpitanlib.midohra.entity.EntityWrapper;
import org.jetbrains.annotations.Nullable;

/**
 * 即時効果 (インスタントヒール等) が適用されたときに呼ばれる。
 * 飲んだ瞬間にテレポートさせるような処理はここに書く。
 */
public class StatusEffectInstantEvent extends BaseEvent {

    public ServerWorld world;

    /**
     * 効果を発生させたエンティティ (投擲されたポーション等)。自分で飲んだ場合はnull。
     */
    @Nullable
    public Entity source;

    /**
     * 効果を与えた側のエンティティ。自分で飲んだ場合はnull。
     */
    @Nullable
    public Entity attacker;

    public LivingEntity target;
    public int amplifier;

    /**
     * 効果の中心からの距離による倍率。スプラッシュポーションで外側ほど小さくなる。
     */
    public double proximity;

    public StatusEffectInstantEvent(ServerWorld world, @Nullable Entity source, @Nullable Entity attacker, LivingEntity target, int amplifier, double proximity) {
        this.world = world;
        this.source = source;
        this.attacker = attacker;
        this.target = target;
        this.amplifier = amplifier;
        this.proximity = proximity;
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

    @Nullable
    public Entity getSource() {
        return source;
    }

    @Nullable
    public Entity getAttacker() {
        return attacker;
    }

    public LivingEntity getTarget() {
        return target;
    }

    public int getAmplifier() {
        return amplifier;
    }

    public int getLevel() {
        return amplifier + 1;
    }

    public double getProximity() {
        return proximity;
    }

    public EntityWrapper getTargetWrapper() {
        return EntityWrapper.of(target);
    }

    public EntityWrapper getSourceWrapper() {
        return source == null ? EntityWrapper.of() : EntityWrapper.of(source);
    }

    public EntityWrapper getAttackerWrapper() {
        return attacker == null ? EntityWrapper.of() : EntityWrapper.of(attacker);
    }
}
