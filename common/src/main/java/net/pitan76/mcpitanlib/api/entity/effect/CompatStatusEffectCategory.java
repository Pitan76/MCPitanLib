package net.pitan76.mcpitanlib.api.entity.effect;

import net.minecraft.entity.effect.StatusEffectType;

public enum CompatStatusEffectCategory {
    BENEFICIAL,
    HARMFUL,
    NEUTRAL;

    public StatusEffectType toMinecraft() {
        switch (this) {
            case BENEFICIAL:
                return StatusEffectType.BENEFICIAL;
            case HARMFUL:
                return StatusEffectType.HARMFUL;
            default:
                return StatusEffectType.NEUTRAL;
        }
    }

    public static CompatStatusEffectCategory of(StatusEffectType category) {
        if (category == StatusEffectType.BENEFICIAL) return BENEFICIAL;
        if (category == StatusEffectType.HARMFUL) return HARMFUL;

        return NEUTRAL;
    }
}
