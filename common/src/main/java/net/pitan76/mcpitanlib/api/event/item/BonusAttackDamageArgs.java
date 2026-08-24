package net.pitan76.mcpitanlib.api.event.item;

import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.damage.DamageSource;

public class BonusAttackDamageArgs {

    public Entity target;
    public float baseAttackDamage;
    public DamageSource damageSource;
    public ItemStack stack;

    public BonusAttackDamageArgs(Entity target, float baseAttackDamage, DamageSource damageSource) {
        this(target, baseAttackDamage, damageSource, ItemStack.EMPTY);
    }

    public BonusAttackDamageArgs(Entity target, float baseAttackDamage, DamageSource damageSource, ItemStack stack) {
        this.target = target;
        this.baseAttackDamage = baseAttackDamage;
        this.damageSource = damageSource;
        this.stack = stack;
    }

    /**
     * used item stack on attack
     */
    public ItemStack getStack() {
        return stack;
    }

    public net.pitan76.mcpitanlib.midohra.item.ItemStack getStackM() {
        return net.pitan76.mcpitanlib.midohra.item.ItemStack.of(stack);
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
