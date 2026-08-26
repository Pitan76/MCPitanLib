package net.pitan76.mcpitanlib.api.entity.effect;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.server.level.ServerLevel;
import net.pitan76.mcpitanlib.api.event.effect.StatusEffectAppliedEvent;
import net.pitan76.mcpitanlib.api.event.effect.StatusEffectInstantEvent;
import net.pitan76.mcpitanlib.api.event.effect.StatusEffectUpdateEvent;

/**
 * バージョン差を吸収したMobEffect。継承してフックをオーバーライドする。
 * <p>
 * バニラのメソッドはバージョンごとに引数が違うため、直接オーバーライドせず
 * {@link #onApplied} / {@link #onUpdate} / {@link #onInstantApplied} を使うこと。
 */
public class ExtendStatusEffect extends MobEffect {

    public ExtendStatusEffect(CompatStatusEffectCategory category, int color) {
        super(category.toMinecraft(), color);
    }

    protected ExtendStatusEffect(MobEffectCategory category, int color) {
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
    public boolean isInstantenous() {
        return false;
    }

    @Override
    public void onEffectAdded(LivingEntity entity, int amplifier) {
        super.onEffectAdded(entity, amplifier);
        onApplied(new StatusEffectAppliedEvent(entity, amplifier));
    }

    @Override
    public boolean applyEffectTick(ServerLevel world, LivingEntity entity, int amplifier) {
        return onUpdate(new StatusEffectUpdateEvent(world, entity, amplifier));
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        return canUpdate(duration, amplifier);
    }

    @Override
    public void applyInstantenousEffect(ServerLevel world, Entity source, Entity attacker, LivingEntity target, int amplifier, double proximity) {
        onInstantApplied(new StatusEffectInstantEvent(world, source, attacker, target, amplifier, proximity));
    }
}
