package net.pitan76.mcpitanlib.api.entity.effect;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectType;
import net.minecraft.server.world.ServerWorld;
import net.pitan76.mcpitanlib.api.event.effect.StatusEffectAppliedEvent;
import net.pitan76.mcpitanlib.api.event.effect.StatusEffectInstantEvent;
import net.pitan76.mcpitanlib.api.event.effect.StatusEffectUpdateEvent;

/**
 * バージョン差を吸収したStatusEffect。継承してフックをオーバーライドする。
 * <p>
 * バニラのメソッドはバージョンごとに引数が違うため、直接オーバーライドせず
 * {@link #onApplied} / {@link #onUpdate} / {@link #onInstantApplied} を使うこと。
 */
public class ExtendStatusEffect extends StatusEffect {

    public ExtendStatusEffect(CompatStatusEffectCategory category, int color) {
        super(category.toMinecraft(), color);
    }

    protected ExtendStatusEffect(StatusEffectType category, int color) {
        super(category, color);
    }

    /**
     * 効果が付与された瞬間に呼ばれる。
     */
    public void onApplied(StatusEffectAppliedEvent e) {
    }

    /**
     * 持続効果のtick処理。
     * @return 効果を継続する場合はtrue
     */
    public boolean onUpdate(StatusEffectUpdateEvent e) {
        return true;
    }

    /**
     * このtickで {@link #onUpdate} を呼ぶかどうか。
     * 既定では毎tick呼ぶ。
     */
    public boolean canUpdate(int duration, int amplifier) {
        return true;
    }

    /**
     * 即時効果の処理。飲んだ瞬間にテレポートさせる等はここに書く。
     */
    public void onInstantApplied(StatusEffectInstantEvent e) {
    }

    /**
     * 即時効果かどうか。trueを返すと持続せず {@link #onInstantApplied} が呼ばれる。
     */
    @Override
    public boolean isInstant() {
        return false;
    }

    @Override
    public void onApplied(LivingEntity entity, net.minecraft.entity.attribute.AttributeContainer attributes, int amplifier) {
        super.onApplied(entity, attributes, amplifier);
        onApplied(new StatusEffectAppliedEvent(entity, amplifier));
    }

    /**
     * このバージョンのバニラはServerWorldを渡してこないため、エンティティから取り出す。
     */
    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        ServerWorld world = toServerWorld(entity);
        if (world == null) return;

        onUpdate(new StatusEffectUpdateEvent(world, entity, amplifier));
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return canUpdate(duration, amplifier);
    }

    @Override
    public void applyInstantEffect(Entity source, Entity attacker, LivingEntity target, int amplifier, double proximity) {
        ServerWorld world = toServerWorld(target);
        if (world == null) return;

        onInstantApplied(new StatusEffectInstantEvent(world, source, attacker, target, amplifier, proximity));
    }

    private static ServerWorld toServerWorld(Entity entity) {
        if (!(entity.getEntityWorld() instanceof ServerWorld)) return null;

        return (ServerWorld) entity.getEntityWorld();
    }
}
