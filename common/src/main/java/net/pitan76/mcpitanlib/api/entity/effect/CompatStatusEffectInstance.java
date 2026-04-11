package net.pitan76.mcpitanlib.api.entity.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.resources.ResourceKey;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class CompatStatusEffectInstance {
    private final MobEffectInstance instance;
    
    @Deprecated
    public CompatStatusEffectInstance(MobEffectInstance instance) {
        this.instance = instance;
    }

    public MobEffectInstance getInstance() {
        return instance;
    }

    public Optional<CompatStatusEffect> getCompatStatusEffect() {
        Optional<ResourceKey<MobEffect>> optional = instance.getEffect().unwrapKey();
        return optional.map(CompatStatusEffect::new);
    }

    public CompatStatusEffectInstance(CompatStatusEffect effect) {
        this(effect, 0, 0);
    }

    public CompatStatusEffectInstance(CompatStatusEffect effect, int duration) {
        this(effect, duration, 0);
    }

    public CompatStatusEffectInstance(CompatStatusEffect effect, int duration, int amplifier) {
        this(effect, duration, amplifier, false, true);
    }

    public CompatStatusEffectInstance(CompatStatusEffect effect, int duration, int amplifier, boolean ambient, boolean visible) {
        this(effect, duration, amplifier, ambient, visible, visible);
    }

    public CompatStatusEffectInstance(CompatStatusEffect effect, int duration, int amplifier, boolean ambient, boolean showParticles, boolean showIcon) {
        this(effect, duration, amplifier, ambient, showParticles, showIcon, null);
    }
    
    public CompatStatusEffectInstance(CompatStatusEffect effect, int duration, int amplifier, boolean ambient, boolean showParticles, boolean showIcon, @Nullable MobEffectInstance hiddenEffect) {
        this.instance = new MobEffectInstance(effect.getEntry(null), duration, amplifier, ambient, showParticles, showIcon, hiddenEffect);
    }

    public int getDuration() {
        return instance.getDuration();
    }

    public int getAmplifier() {
        return instance.getAmplifier();
    }

    public boolean isAmbient() {
        return instance.isAmbient();
    }

    public boolean showParticles() {
        return instance.isVisible();
    }

    public boolean showIcon() {
        return instance.showIcon();
    }

    public boolean isInfinite() {
        return instance.isInfiniteDuration();
    }
}
