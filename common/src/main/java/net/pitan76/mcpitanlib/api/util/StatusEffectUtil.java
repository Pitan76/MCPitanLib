package net.pitan76.mcpitanlib.api.util;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import net.pitan76.mcpitanlib.api.entity.effect.CompatStatusEffect;

public class StatusEffectUtil {
    public static CompatStatusEffect getStatusEffect(Identifier identifier) {
        StatusEffect statusEffect = Registries.STATUS_EFFECT.get(identifier);
        return new CompatStatusEffect(statusEffect);
    }

    public static CompatStatusEffect getStatusEffect(CompatIdentifier identifier) {
        return getStatusEffect(identifier.toMinecraft());
    }

    public static Identifier getId(CompatStatusEffect statusEffect) {
        return statusEffect.getId();
    }

    public static CompatIdentifier getCompatId(CompatStatusEffect statusEffect) {
        return CompatIdentifier.fromMinecraft(statusEffect.getId());
    }

}
