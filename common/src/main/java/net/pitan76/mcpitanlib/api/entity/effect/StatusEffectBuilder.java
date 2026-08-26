package net.pitan76.mcpitanlib.api.entity.effect;

import net.pitan76.mcpitanlib.api.CommonModInitializer;
import net.pitan76.mcpitanlib.api.event.effect.StatusEffectAppliedEvent;
import net.pitan76.mcpitanlib.api.event.effect.StatusEffectInstantEvent;
import net.pitan76.mcpitanlib.api.event.effect.StatusEffectUpdateEvent;
import net.pitan76.mcpitanlib.api.registry.result.RegistryResult;
import net.pitan76.mcpitanlib.api.registry.v2.CompatRegistryV2;
import net.pitan76.mcpitanlib.api.util.CompatIdentifier;
import net.pitan76.mcpitanlib.midohra.entity.effect.SupplierStatusEffectWrapper;

import java.util.function.Consumer;
import java.util.function.Function;

public class StatusEffectBuilder {

    public CompatIdentifier id;
    public CompatStatusEffectCategory category = CompatStatusEffectCategory.NEUTRAL;
    public int color = 0xFFFFFF;
    public boolean instant = false;

    public Consumer<StatusEffectAppliedEvent> onApplied;
    public Function<StatusEffectUpdateEvent, Boolean> onUpdate;
    public Consumer<StatusEffectInstantEvent> onInstantApplied;

    public StatusEffectBuilder(CompatIdentifier id) {
        this.id = id;
    }

    public static StatusEffectBuilder of(CompatIdentifier id) {
        return new StatusEffectBuilder(id);
    }

    public static StatusEffectBuilder of(String id) {
        return new StatusEffectBuilder(CompatIdentifier.of(id));
    }

    public StatusEffectBuilder category(CompatStatusEffectCategory category) {
        this.category = category;
        return this;
    }

    public StatusEffectBuilder beneficial() {
        return category(CompatStatusEffectCategory.BENEFICIAL);
    }

    public StatusEffectBuilder harmful() {
        return category(CompatStatusEffectCategory.HARMFUL);
    }

    /**
     * @param color ポーションやパーティクルの色。eg: 0xRRGGBB。
     */
    public StatusEffectBuilder color(int color) {
        this.color = color;
        return this;
    }

    /**
     * 持続せず、付与された瞬間だけ効果が出るようにする。
     * {@link #onInstantApplied(Consumer)} と併用する。
     */
    public StatusEffectBuilder instant() {
        this.instant = true;
        return this;
    }

    /**
     * @param onApplied 効果が付与されたときに実行される処理
     */
    public StatusEffectBuilder onApplied(Consumer<StatusEffectAppliedEvent> onApplied) {
        this.onApplied = onApplied;
        return this;
    }

    /**
     * @param onUpdate 効果を継続する場合はtrueを返す
     */
    public StatusEffectBuilder onUpdate(Function<StatusEffectUpdateEvent, Boolean> onUpdate) {
        this.onUpdate = onUpdate;
        return this;
    }

    /**
     * 飲んだ瞬間に動作させる処理等はここに書く。{@link #instant()} と併用する。
     */
    public StatusEffectBuilder onInstantApplied(Consumer<StatusEffectInstantEvent> onInstantApplied) {
        this.onInstantApplied = onInstantApplied;
        return this;
    }

    public ExtendStatusEffect create() {
        return new BuiltStatusEffect(this);
    }

    public SupplierStatusEffectWrapper build(CompatRegistryV2 registry) {
        return SupplierStatusEffectWrapper.of(rawBuild(registry)::get);
    }

    public SupplierStatusEffectWrapper build(CommonModInitializer initializer) {
        return build(initializer.registry);
    }

    /**
     * {@link RegistryResult} が欲しい場合はこちら。
     */
    public RegistryResult<net.minecraft.world.effect.MobEffect> rawBuild(CompatRegistryV2 registry) {
        return registry.registerStatusEffect(id, this::create);
    }

    public RegistryResult<net.minecraft.world.effect.MobEffect> rawBuild(CommonModInitializer initializer) {
        return rawBuild(initializer.registry);
    }

    public static class BuiltStatusEffect extends ExtendStatusEffect {

        public final StatusEffectBuilder builder;

        public BuiltStatusEffect(StatusEffectBuilder builder) {
            super(builder.category, builder.color);
            this.builder = builder;
        }

        @Override
        public boolean isInstantaneous() {
            return builder.instant;
        }

        @Override
        public void onApplied(StatusEffectAppliedEvent e) {
            if (builder.onApplied != null) builder.onApplied.accept(e);
        }

        @Override
        public boolean onUpdate(StatusEffectUpdateEvent e) {
            if (builder.onUpdate == null) return true;

            return builder.onUpdate.apply(e);
        }

        @Override
        public void onInstantApplied(StatusEffectInstantEvent e) {
            if (builder.onInstantApplied != null) builder.onInstantApplied.accept(e);
        }
    }
}
