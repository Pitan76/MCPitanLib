package net.pitan76.mcpitanlib.api.util.particle.effect;

import net.minecraft.particle.ParticleEffect;
import net.pitan76.mcpitanlib.api.util.particle.CompatParticleType;

public class CompatParticleEffect {
    private final ParticleEffect effect;

    @Deprecated
    public CompatParticleEffect(ParticleEffect effect) {
        this.effect = effect;
    }

    @Deprecated
    public static CompatParticleEffect of(ParticleEffect effect) {
        return new CompatParticleEffect(effect);
    }

    public static CompatParticleEffect of(CompatParticleType type) {
        ParticleEffect options = type::getRaw;
        return new CompatParticleEffect(options);
    }

    @Deprecated
    public ParticleEffect getRaw() {
        return effect;
    }

    public CompatParticleType getType() {
        return CompatParticleType.of(getRaw().getType());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        CompatParticleEffect other = (CompatParticleEffect) obj;
        return getRaw().equals(other.getRaw());
    }

    @Override
    public int hashCode() {
        return getRaw().hashCode();
    }
}
