package net.pitan76.mcpitanlib.api.util;

import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.pitan76.mcpitanlib.api.util.particle.effect.ItemStackParticleEffectUtil;

public class ParticleEffectUtil {
    public static ItemStackParticleEffectUtil itemStack = new ItemStackParticleEffectUtil();

    public static ParticleType<?> getType(ParticleOptions effect) {
        return effect.getType();
    }
}
