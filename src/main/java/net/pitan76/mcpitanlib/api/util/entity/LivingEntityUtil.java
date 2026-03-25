package net.pitan76.mcpitanlib.api.util.entity;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.pitan76.mcpitanlib.api.entity.effect.CompatStatusEffect;
import net.pitan76.mcpitanlib.api.entity.effect.CompatStatusEffectInstance;
import net.pitan76.mcpitanlib.api.util.EntityUtil;

import java.util.ArrayList;
import java.util.List;

public class LivingEntityUtil extends EntityUtil {
    public static void addStatusEffect(LivingEntity entity, CompatStatusEffectInstance effect) {
        entity.addEffect(effect.getInstance());
    }

    public static void removeStatusEffect(LivingEntity entity, CompatStatusEffectInstance effect) {
        entity.removeEffect(effect.getInstance().getEffect());
    }

    public static void removeStatusEffect(LivingEntity entity, CompatStatusEffect effect, Level world) {
        entity.removeEffect(effect.getEntry(world));
    }

    public static List<CompatStatusEffectInstance> getStatusEffects(LivingEntity entity) {
        List<CompatStatusEffectInstance> compatEffects = new ArrayList<>();

        for (MobEffectInstance effect : entity.getActiveEffects()) {
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

    public static ItemStack getEquippedStack(LivingEntity entity, EquipmentSlot slot) {
        return entity.getItemBySlot(slot);
    }

    public static void setEquippedStack(LivingEntity entity, EquipmentSlot slot, ItemStack stack) {
        entity.setItemSlot(slot, stack);
    }
}
