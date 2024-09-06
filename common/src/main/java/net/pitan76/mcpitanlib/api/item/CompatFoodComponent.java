package net.pitan76.mcpitanlib.api.item;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import net.minecraft.component.type.FoodComponent;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.pitan76.mcpitanlib.api.entity.effect.CompatStatusEffectInstance;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CompatFoodComponent {
    private int hunger;
    private float saturation;
    private boolean meat;
    private boolean alwaysEdible;
    private boolean snack;
    private final List<Pair<StatusEffectInstance, Float>> statusEffects = new ArrayList<>();

    private FoodComponent latestComponent = null;

    public static CompatFoodComponent create() {
        return new CompatFoodComponent();
    }

    public CompatFoodComponent setHunger(int hunger) {
        this.hunger = hunger;
        return this;
    }

    public CompatFoodComponent setSaturation(float saturation) {
        this.saturation = saturation;
        return this;
    }

    public CompatFoodComponent setAlwaysEdible() {
        this.alwaysEdible = true;
        return this;
    }

    public CompatFoodComponent setSnack() {
        this.snack = true;
        return this;
    }

    public CompatFoodComponent setMeat() {
        this.meat = true;
        return this;
    }

    @Deprecated
    public CompatFoodComponent addStatusEffect(StatusEffectInstance effect, float chance) {
        statusEffects.add(Pair.of(effect, chance));
        return this;
    }

    public CompatFoodComponent addStatusEffect(CompatStatusEffectInstance instance, float chance) {
        return addStatusEffect(instance.getInstance(), chance);
    }

    public FoodComponent.Builder getVanillaBuilder() {
        FoodComponent.Builder builder = new FoodComponent.Builder();
        builder.nutrition(hunger).saturationModifier(saturation);
        if (alwaysEdible) builder.alwaysEdible();
        if (snack) builder.snack();
        //if (meat) builder.meat();
        for (Pair<StatusEffectInstance, Float> pair : statusEffects) {
            builder.statusEffect(pair.getFirst(), pair.getSecond());
        }
        return builder;
    }

    public FoodComponent build() {
        if (latestComponent != null) return latestComponent;

        float eatSeconds = 1.6f;
        ImmutableList.Builder<FoodComponent.StatusEffectEntry> effects = ImmutableList.builder();
        effects.addAll(statusEffects.stream().map(pair -> new FoodComponent.StatusEffectEntry(pair.getFirst(), pair.getSecond())).iterator());
        if (snack) eatSeconds = 0.8f;
        if (meat) eatSeconds = 1.6f;

        latestComponent = new FoodComponent(hunger, saturation, alwaysEdible, eatSeconds, Optional.empty(), effects.build());

        return latestComponent;
    }

    public FoodComponent vanillaBuild() {
        if (latestComponent != null) return latestComponent;

        latestComponent = getVanillaBuilder().build();
        return latestComponent;
    }

    // ----

    public int getHunger() {
        return hunger;
    }

    public float getSaturation() {
        return saturation;
    }

    public boolean isMeat() {
        return meat;
    }

    public boolean isSnack() {
        return snack;
    }

    public boolean isAlwaysEdible() {
        return alwaysEdible;
    }

    @Deprecated
    public List<Pair<StatusEffectInstance, Float>> getStatusEffects() {
        return statusEffects;
    }

    public List<Pair<CompatStatusEffectInstance, Float>> getCompatStatusEffects() {
        List<Pair<CompatStatusEffectInstance, Float>> compatStatusEffects = new ArrayList<>();
        for (Pair<StatusEffectInstance, Float> pair : statusEffects) {
            compatStatusEffects.add(Pair.of(new CompatStatusEffectInstance(pair.getFirst()), pair.getSecond()));
        }
        return compatStatusEffects;
    }
}
