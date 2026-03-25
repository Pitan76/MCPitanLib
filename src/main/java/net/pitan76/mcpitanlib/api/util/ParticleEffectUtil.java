package net.pitan76.mcpitanlib.api.util;

import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleType;
import net.pitan76.mcpitanlib.api.util.particle.effect.ItemStackParticleEffectUtil;

public class ParticleEffectUtil {
    public static ItemStackParticleEffectUtil itemStack = new ItemStackParticleEffectUtil();

    public static ParticleType<?> getType(ParticleEffect effect) {
        return effect.getType();
    }
}
