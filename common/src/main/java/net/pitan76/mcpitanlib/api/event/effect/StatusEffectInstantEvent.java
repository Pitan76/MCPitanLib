package net.pitan76.mcpitanlib.api.event.effect;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.pitan76.mcpitanlib.api.event.BaseEvent;
import org.jetbrains.annotations.Nullable;

/**
 * 即時効果 (インスタントヒール等) が適用されたときに呼ばれる。
 * 飲んだ瞬間にテレポートさせるような処理はここに書く。
 */
public class StatusEffectInstantEvent extends BaseEvent {

    public ServerLevel world;

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

    public StatusEffectInstantEvent(ServerLevel world, @Nullable Entity source, @Nullable Entity attacker, LivingEntity target, int amplifier, double proximity) {
        this.world = world;
        this.source = source;
        this.attacker = attacker;
        this.target = target;
        this.amplifier = amplifier;
        this.proximity = proximity;
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
}
