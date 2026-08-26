package net.pitan76.mcpitanlib.api.potion;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.potion.Potion;
import net.pitan76.mcpitanlib.api.CommonModInitializer;
import net.pitan76.mcpitanlib.api.entity.effect.CompatStatusEffect;
import net.pitan76.mcpitanlib.api.entity.effect.CompatStatusEffectInstance;
import net.pitan76.mcpitanlib.api.registry.result.RegistryResult;
import net.pitan76.mcpitanlib.api.registry.v2.CompatRegistryV2;
import net.pitan76.mcpitanlib.api.util.CompatIdentifier;
import net.pitan76.mcpitanlib.midohra.potion.SupplierPotionWrapper;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

/**
 * カスタムポーションを組み立てる。
 * <p>
 * {@code baseName} は翻訳キーに使われる。指定しない場合はidのパス部分を使う。
 * 翻訳キーは {@code item.minecraft.potion.effect.<baseName>} のような形になるため、
 * 言語ファイルへの追加が必要。
 */
public class PotionBuilder {

    public CompatIdentifier id;
    public String baseName;
    public final List<Supplier<StatusEffectInstance>> effectSuppliers = new ArrayList<>();

    public PotionBuilder(CompatIdentifier id) {
        this.id = id;
        this.baseName = id.getPath();
    }

    public static PotionBuilder of(CompatIdentifier id) {
        return new PotionBuilder(id);
    }

    public static PotionBuilder of(String id) {
        return new PotionBuilder(CompatIdentifier.of(id));
    }

    public PotionBuilder baseName(String baseName) {
        this.baseName = baseName;
        return this;
    }

    public PotionBuilder effect(StatusEffectInstance effect) {
        effectSuppliers.add(() -> effect);
        return this;
    }

    public PotionBuilder effect(CompatStatusEffectInstance effect) {
        return effect(effect.getInstance());
    }

    /**
     * @param duration 持続時間 (tick)
     * @param amplifier 効果レベル。レベルIは0、レベルIIは1
     */
    public PotionBuilder effect(CompatStatusEffect effect, int duration, int amplifier) {
        return effect(new CompatStatusEffectInstance(effect, duration, amplifier));
    }

    public PotionBuilder effect(CompatStatusEffect effect, int duration) {
        return effect(effect, duration, 0);
    }

    public PotionBuilder effect(net.pitan76.mcpitanlib.midohra.entity.effect.StatusEffectWrapper effect, int duration, int amplifier) {
        effectSuppliers.add(() -> new StatusEffectInstance(effect.getEntry(), duration, amplifier));
        return this;
    }

    public PotionBuilder effect(net.pitan76.mcpitanlib.midohra.entity.effect.StatusEffectWrapper effect, int duration) {
        return effect(effect, duration, 0);
    }

    public PotionBuilder effect(RegistryResult<net.minecraft.entity.effect.StatusEffect> effect, int duration, int amplifier) {
        effectSuppliers.add(() -> new StatusEffectInstance(PotionUtil.toEffectEntry(effect), duration, amplifier));
        return this;
    }

    public PotionBuilder effect(RegistryResult<net.minecraft.entity.effect.StatusEffect> effect, int duration) {
        return effect(effect, duration, 0);
    }

    private PotionBuilder effect(net.minecraft.entity.effect.StatusEffect entry, int duration, int amplifier) {
        effectSuppliers.add(() -> new StatusEffectInstance(entry, duration, amplifier));
        return this;
    }

    public Potion create() {
        StatusEffectInstance[] effects = effectSuppliers.stream()
                .map(Supplier::get)
                .toArray(StatusEffectInstance[]::new);
        return new Potion(baseName, effects);
    }

    public SupplierPotionWrapper build(CompatRegistryV2 registry) {
        return SupplierPotionWrapper.of(rawBuild(registry)::get);
    }

    public SupplierPotionWrapper build(CommonModInitializer initializer) {
        return build(initializer.registry);
    }

    public RegistryResult<Potion> rawBuild(CompatRegistryV2 registry) {
        return registry.registerPotion(id, this::create);
    }

    public RegistryResult<Potion> rawBuild(CommonModInitializer initializer) {
        return rawBuild(initializer.registry);
    }
}
