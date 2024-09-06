package net.pitan76.mcpitanlib.api.util;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.pitan76.mcpitanlib.api.entity.effect.CompatStatusEffect;

public class StatusEffectUtil {
    public static CompatStatusEffect getStatusEffect(Identifier identifier) {
        RegistryKey<StatusEffect> registryKey = RegistryKey.of(RegistryKeys.STATUS_EFFECT, identifier);
        return new CompatStatusEffect(registryKey);
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
