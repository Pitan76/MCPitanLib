package net.pitan76.mcpitanlib.api.util.entity;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.world.World;
import net.pitan76.mcpitanlib.api.entity.effect.CompatStatusEffect;
import net.pitan76.mcpitanlib.api.entity.effect.CompatStatusEffectInstance;
import net.pitan76.mcpitanlib.api.util.EntityUtil;

import java.util.ArrayList;
import java.util.List;

public class LivingEntityUtil extends EntityUtil {
    public static void addStatusEffect(LivingEntity entity, CompatStatusEffectInstance effect) {
        entity.addStatusEffect(effect.getInstance());
    }

    public static void removeStatusEffect(LivingEntity entity, CompatStatusEffectInstance effect) {
        entity.removeStatusEffect(effect.getInstance().getEffectType());
    }

    public static void removeStatusEffect(LivingEntity entity, CompatStatusEffect effect, World world) {
        entity.removeStatusEffect(effect.getStatusEffect(world));
    }

    public static List<CompatStatusEffectInstance> getStatusEffects(LivingEntity entity) {
        List<CompatStatusEffectInstance> compatEffects = new ArrayList<>();

        for (StatusEffectInstance effect : entity.getStatusEffects()) {
            compatEffects.add(new CompatStatusEffectInstance(effect));
        }

        return compatEffects;
    }

    public static float getHealth(LivingEntity entity) {
        return entity.getHealth();
    }

    public static void setHealth(LivingEntity entity, float health) {
        entity.setHealth(health);
    }

    public static float getMaxHealth(LivingEntity entity) {
        return entity.getMaxHealth();
    }
}
