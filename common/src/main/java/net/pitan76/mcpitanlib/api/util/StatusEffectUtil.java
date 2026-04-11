package net.pitan76.mcpitanlib.api.util;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.resources.ResourceKey;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.pitan76.mcpitanlib.api.entity.effect.CompatStatusEffect;

public class StatusEffectUtil {
    public static CompatStatusEffect getStatusEffect(Identifier identifier) {
        ResourceKey<MobEffect> registryKey = ResourceKey.create(Registries.MOB_EFFECT, identifier);
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
