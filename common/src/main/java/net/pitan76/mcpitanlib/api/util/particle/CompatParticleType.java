package net.pitan76.mcpitanlib.api.util.particle;

import net.minecraft.particle.ParticleType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.pitan76.mcpitanlib.api.util.CompatIdentifier;

public class CompatParticleType {
    private final ParticleType<?> particleType;

    public CompatParticleType(ParticleType<?> particleType) {
        this.particleType = particleType;
    }

    public static CompatParticleType of(ParticleType<?> particleType) {
        return new CompatParticleType(particleType);
    }

    public ParticleType<?> getRaw() {
        return particleType;
    }

    public boolean shouldAlwaysSpawn() {
        return particleType.shouldAlwaysSpawn();
    }

    public Identifier getId() {
        return Registry.PARTICLE_TYPE.getId(particleType);
    }

    public CompatIdentifier getCompatId() {
        return CompatIdentifier.fromMinecraft(getId());
    }

    @Override
    public int hashCode() {
        return particleType.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        CompatParticleType other = (CompatParticleType) obj;
        return particleType.equals(other.particleType);
    }
}
