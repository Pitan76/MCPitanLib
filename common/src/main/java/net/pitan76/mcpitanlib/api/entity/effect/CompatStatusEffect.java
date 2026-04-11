package net.pitan76.mcpitanlib.api.entity.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.data.registries.VanillaRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.core.Holder;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.Level;
import net.pitan76.mcpitanlib.api.util.StatusEffectUtil;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class CompatStatusEffect {
    private final ResourceKey<MobEffect> registryKey;

    @Deprecated
    public CompatStatusEffect(ResourceKey<MobEffect> registryKey) {
        this.registryKey = registryKey;
    }

    public CompatStatusEffect of(Identifier identifier) {
        return StatusEffectUtil.getStatusEffect(identifier);
    }

    public Identifier getId() {
        return registryKey.registry();
    }

    @Deprecated
    public ResourceKey<MobEffect> getRegistryKey() {
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
    public Holder<MobEffect> getEntry(@Nullable Level world) {
        Optional<Holder.Reference<MobEffect>> optionalEntry;
        if (world == null) {
            optionalEntry = VanillaRegistries.createLookup()
                    .get(registryKey);
        } else {
            optionalEntry = world.registryAccess().get(registryKey);
        }

        return optionalEntry.orElseThrow();
    }

    public MobEffect getStatusEffect(@Nullable Level world) {
        return getEntry(world).value();
    }
}
