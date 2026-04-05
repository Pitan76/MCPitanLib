package net.pitan76.mcpitanlib.api.util.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.pitan76.mcpitanlib.api.entity.effect.CompatStatusEffect;
import net.pitan76.mcpitanlib.api.entity.effect.CompatStatusEffectInstance;
import net.pitan76.mcpitanlib.api.item.ArmorEquipmentType;
import net.pitan76.mcpitanlib.api.util.EntityUtil;
import net.pitan76.mcpitanlib.midohra.entity.EntityWrapper;

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

    public static ItemStack getEquippedStack(LivingEntity entity, EquipmentSlot slot) {
        return entity.getEquippedStack(slot);
    }

    public static void setEquippedStack(LivingEntity entity, EquipmentSlot slot, ItemStack stack) {
        entity.equipStack(slot, stack);
    }

    public static ItemStack getEquippedStack(LivingEntity entity, ArmorEquipmentType type) {
        return getEquippedStack(entity, type.getSlot());
    }

    public static void setEquippedStack(LivingEntity entity, ArmorEquipmentType type, ItemStack stack) {
        entity.equipStack(type.getSlot(), stack);
    }

    public static net.pitan76.mcpitanlib.midohra.item.ItemStack getEquippedStack(EntityWrapper entity, ArmorEquipmentType slot) {
        Entity e = entity.get();
        if (e instanceof LivingEntity) {
            return net.pitan76.mcpitanlib.midohra.item.ItemStack.of(getEquippedStack((LivingEntity) e, slot));
        }

        return net.pitan76.mcpitanlib.midohra.item.ItemStack.empty();
    }

    public static void setEquippedStack(EntityWrapper entity, ArmorEquipmentType slot, net.pitan76.mcpitanlib.midohra.item.ItemStack stack) {
        Entity e = entity.get();
        if (e instanceof LivingEntity) {
            setEquippedStack((LivingEntity) e, slot, stack.toMinecraft());
        }
    }
}
