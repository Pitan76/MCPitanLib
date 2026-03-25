package net.pitan76.mcpitanlib.api.item;

import com.mojang.datafixers.util.Pair;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.effect.MobEffectInstance;
import net.pitan76.mcpitanlib.api.entity.effect.CompatStatusEffectInstance;

import java.util.ArrayList;
import java.util.List;

public class CompatFoodComponent {
    private int hunger;
    private float saturation;
    private boolean meat;
    private boolean alwaysEdible;
    private boolean snack;
    private final List<Pair<MobEffectInstance, Float>> statusEffects = new ArrayList<>();

    private FoodProperties latestComponent = null;

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
    public CompatFoodComponent addStatusEffect(MobEffectInstance effect, float chance) {
        statusEffects.add(Pair.of(effect, chance));
        return this;
    }

    public CompatFoodComponent addStatusEffect(CompatStatusEffectInstance instance, float chance) {
        return addStatusEffect(instance.getInstance(), chance);
    }

    public FoodProperties.Builder getVanillaBuilder() {
        FoodProperties.Builder builder = new FoodProperties.Builder();
        builder.nutrition(hunger).saturationModifier(saturation);
        if (alwaysEdible) builder.alwaysEdible();
        return builder;
    }

    public FoodProperties build() {
        if (latestComponent != null) return latestComponent;

        float eatSeconds = 1.6f;
        if (snack) eatSeconds = 0.8f;
        if (meat) eatSeconds = 1.6f;

        latestComponent = new FoodProperties(hunger, saturation, alwaysEdible);

        return latestComponent;
    }

    public FoodProperties vanillaBuild() {
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
    public List<Pair<MobEffectInstance, Float>> getStatusEffects() {
        return statusEffects;
    }

    public List<Pair<CompatStatusEffectInstance, Float>> getCompatStatusEffects() {
        List<Pair<CompatStatusEffectInstance, Float>> compatStatusEffects = new ArrayList<>();
        for (Pair<MobEffectInstance, Float> pair : statusEffects) {
            compatStatusEffects.add(Pair.of(new CompatStatusEffectInstance(pair.getFirst()), pair.getSecond()));
        }
        return compatStatusEffects;
    }
}
