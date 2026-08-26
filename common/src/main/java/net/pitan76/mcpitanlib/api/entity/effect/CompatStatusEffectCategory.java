package net.pitan76.mcpitanlib.api.entity.effect;

import net.minecraft.world.effect.MobEffectCategory;

public enum CompatStatusEffectCategory {
    BENEFICIAL,
    HARMFUL,
    NEUTRAL;

    public MobEffectCategory toMinecraft() {
        switch (this) {
            case BENEFICIAL:
                return MobEffectCategory.BENEFICIAL;
            case HARMFUL:
                return MobEffectCategory.HARMFUL;
            default:
                return MobEffectCategory.NEUTRAL;
        }
    }

    public static CompatStatusEffectCategory of(MobEffectCategory category) {
        if (category == MobEffectCategory.BENEFICIAL) return BENEFICIAL;
        if (category == MobEffectCategory.HARMFUL) return HARMFUL;

        return NEUTRAL;
    }
}
