package net.pitan76.mcpitanlib.api.item;

import com.mojang.datafixers.util.Pair;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.FoodComponent;
import net.pitan76.mcpitanlib.api.entity.effect.CompatStatusEffectInstance;

import java.util.ArrayList;
import java.util.List;

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
        builder.hunger(hunger).saturationModifier(saturation);
        if (alwaysEdible) builder.alwaysEdible();
        if (snack) builder.snack();
        if (meat) builder.meat();
        for (Pair<StatusEffectInstance, Float> pair : statusEffects) {
            builder.statusEffect(pair.getFirst(), pair.getSecond());
        }
        return builder;
    }

    public FoodComponent build() {
        return vanillaBuild();
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
