package net.pitan76.mcpitanlib.midohra.entity.effect;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.registry.Registries;
import net.pitan76.mcpitanlib.api.util.CompatIdentifier;

public class StatusEffectWrapper {

    private final StatusEffect statusEffect;

    protected StatusEffectWrapper() {
        this.statusEffect = null;
    }

    protected StatusEffectWrapper(StatusEffect statusEffect) {
        this.statusEffect = statusEffect;
    }

    public static StatusEffectWrapper of(StatusEffect statusEffect) {
        return new StatusEffectWrapper(statusEffect);
    }

    public static StatusEffectWrapper of() {
        return new StatusEffectWrapper();
    }

    public static StatusEffectWrapper of(CompatIdentifier id) {
        StatusEffect effect = Registries.STATUS_EFFECT.get(id.toMinecraft());
        if (effect == null) return of();

        return of(effect);
    }

    public StatusEffect get() {
        return statusEffect;
    }

    public boolean isEmpty() {
        return get() == null;
    }

    public boolean isPresent() {
        return !isEmpty();
    }

    public StatusEffect getEntry() {
        return get();
    }

    public CompatIdentifier getId() {
        return CompatIdentifier.fromMinecraft(Registries.STATUS_EFFECT.getId(get()));
    }

    public boolean isInstant() {
        return get().isInstant();
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
