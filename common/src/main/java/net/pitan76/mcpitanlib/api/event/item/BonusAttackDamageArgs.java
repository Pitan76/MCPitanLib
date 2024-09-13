package net.pitan76.mcpitanlib.api.event.item;

import net.minecraft.entity.Entity;
import net.minecraft.entity.damage.DamageSource;

public class BonusAttackDamageArgs {

    public Entity target;
    public float baseAttackDamage;
    public DamageSource damageSource;

    public BonusAttackDamageArgs(Entity target, float baseAttackDamage, DamageSource damageSource) {
        this.target = target;
        this.baseAttackDamage = baseAttackDamage;
        this.damageSource = damageSource;
    }

    public Entity getTarget() {
        return target;
    }

    public float getBaseAttackDamage() {
        return baseAttackDamage;
    }

    public DamageSource getDamageSource() {
        return damageSource;
    }
}
