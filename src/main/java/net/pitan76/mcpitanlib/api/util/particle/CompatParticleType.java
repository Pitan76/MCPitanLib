package net.pitan76.mcpitanlib.api.util.particle;

import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
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
        return particleType.getOverrideLimiter();
    }

    public Identifier getId() {
        return BuiltInRegistries.PARTICLE_TYPE.getKey(particleType);
    }

    public CompatIdentifier getCompatId() {
        return CompatIdentifier.fromMinecraft(getId());
    }
}
