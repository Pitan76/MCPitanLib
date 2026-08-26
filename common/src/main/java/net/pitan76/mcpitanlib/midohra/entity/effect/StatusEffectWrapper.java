package net.pitan76.mcpitanlib.midohra.entity.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.Holder;
import net.pitan76.mcpitanlib.api.util.CompatIdentifier;

public class StatusEffectWrapper {

    private final MobEffect statusEffect;

    protected StatusEffectWrapper() {
        this.statusEffect = null;
    }

    protected StatusEffectWrapper(MobEffect statusEffect) {
        this.statusEffect = statusEffect;
    }

    public static StatusEffectWrapper of(MobEffect statusEffect) {
        return new StatusEffectWrapper(statusEffect);
    }

    public static StatusEffectWrapper of() {
        return new StatusEffectWrapper();
    }

    public static StatusEffectWrapper of(CompatIdentifier id) {
        MobEffect effect = BuiltInRegistries.MOB_EFFECT.getValue(id.toMinecraft());
        if (effect == null) return of();

        return of(effect);
    }

    public MobEffect get() {
        return statusEffect;
    }

    public boolean isEmpty() {
        return get() == null;
    }

    public boolean isPresent() {
        return !isEmpty();
    }

    public Holder<MobEffect> getEntry() {
        return BuiltInRegistries.MOB_EFFECT.wrapAsHolder(get());
    }

    public CompatIdentifier getId() {
        return CompatIdentifier.fromMinecraft(BuiltInRegistries.MOB_EFFECT.getKey(get()));
    }

    public boolean isInstantenous() {
        return get().isInstantenous();
    }

    @Override
    public String toString() {
        return isEmpty() ? "empty" : getId().toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof StatusEffectWrapper)) return false;

        return ((StatusEffectWrapper) obj).get() == get();
    }

    @Override
    public int hashCode() {
        return isEmpty() ? 0 : get().hashCode();
    }
}
