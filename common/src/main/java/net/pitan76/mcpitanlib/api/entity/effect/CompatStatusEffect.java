package net.pitan76.mcpitanlib.api.entity.effect;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.registry.BuiltinRegistries;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import net.pitan76.mcpitanlib.api.util.StatusEffectUtil;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class CompatStatusEffect {
    private final RegistryKey<StatusEffect> registryKey;

    @Deprecated
    public CompatStatusEffect(RegistryKey<StatusEffect> registryKey) {
        this.registryKey = registryKey;
    }

    public CompatStatusEffect of(Identifier identifier) {
        return StatusEffectUtil.getStatusEffect(identifier);
    }

    public Identifier getId() {
        return registryKey.getRegistry();
    }

    @Deprecated
    public RegistryKey<StatusEffect> getRegistryKey() {
        return registryKey;
    }

    public String toString() {
        return getId().toString();
    }

    public boolean equals(Object obj) {
        if (obj instanceof CompatStatusEffect) {
            return ((CompatStatusEffect) obj).getId().equals(getId());
        }
        return false;
    }

    @Deprecated
    public RegistryEntry<StatusEffect> getEntry(@Nullable World world) {
        Optional<RegistryEntry.Reference<StatusEffect>> optionalEntry;
        if (world == null) {
            optionalEntry = BuiltinRegistries.createWrapperLookup().createRegistryLookup()
                    .getOptionalEntry(RegistryKeys.STATUS_EFFECT, registryKey);
        } else {
            optionalEntry = world.getRegistryManager().get(RegistryKeys.STATUS_EFFECT).getEntry(registryKey);
        }

        return optionalEntry.orElseThrow();
    }

    public StatusEffect getStatusEffect(@Nullable World world) {
        return getEntry(world).value();
    }
}
