package net.pitan76.mcpitanlib.api.util.particle.effect;

import net.minecraft.core.particles.ParticleOptions;
import net.pitan76.mcpitanlib.api.util.particle.CompatParticleType;

public class CompatParticleEffect {
    private final ParticleOptions effect;

    @Deprecated
    public CompatParticleEffect(ParticleOptions effect) {
        this.effect = effect;
    }

    @Deprecated
    public static CompatParticleEffect of(ParticleOptions effect) {
        return new CompatParticleEffect(effect);
    }

    public static CompatParticleEffect of(CompatParticleType type) {
        ParticleOptions options = type::getRaw;
        return new CompatParticleEffect(options);
    }

    @Deprecated
    public ParticleOptions getRaw() {
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
