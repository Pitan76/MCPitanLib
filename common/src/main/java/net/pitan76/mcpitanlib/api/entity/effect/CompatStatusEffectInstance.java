package net.pitan76.mcpitanlib.api.entity.effect;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.registry.RegistryKey;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class CompatStatusEffectInstance {
    private final StatusEffectInstance instance;
    
    @Deprecated
    public CompatStatusEffectInstance(StatusEffectInstance instance) {
        this.instance = instance;
    }

    public StatusEffectInstance getInstance() {
        return instance;
    }

    public Optional<CompatStatusEffect> getCompatStatusEffect() {
        Optional<RegistryKey<StatusEffect>> optional = instance.getEffectType().getKey();
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
    
    public CompatStatusEffectInstance(CompatStatusEffect effect, int duration, int amplifier, boolean ambient, boolean showParticles, boolean showIcon, @Nullable StatusEffectInstance hiddenEffect) {
        this.instance = new StatusEffectInstance(effect.getEntry(null), duration, amplifier, ambient, showParticles, showIcon, hiddenEffect);
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
        return instance.shouldShowParticles();
    }

    public boolean showIcon() {
        return instance.shouldShowIcon();
    }

    public boolean isInfinite() {
        return instance.isInfinite();
    }
}
