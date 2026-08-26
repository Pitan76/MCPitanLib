package net.pitan76.mcpitanlib.api.entity.effect;

import net.minecraft.entity.effect.StatusEffectCategory;

public enum CompatStatusEffectCategory {
    BENEFICIAL,
    HARMFUL,
    NEUTRAL;

    public StatusEffectCategory toMinecraft() {
        switch (this) {
            case BENEFICIAL:
                return StatusEffectCategory.BENEFICIAL;
            case HARMFUL:
                return StatusEffectCategory.HARMFUL;
            default:
                return StatusEffectCategory.NEUTRAL;
        }
    }

    public static CompatStatusEffectCategory of(StatusEffectCategory category) {
        if (category == StatusEffectCategory.BENEFICIAL) return BENEFICIAL;
        if (category == StatusEffectCategory.HARMFUL) return HARMFUL;

        return NEUTRAL;
    }
}
