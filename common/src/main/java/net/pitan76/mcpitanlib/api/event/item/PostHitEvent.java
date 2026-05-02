package net.pitan76.mcpitanlib.api.event.item;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.pitan76.mcpitanlib.api.event.BaseEvent;
import net.pitan76.mcpitanlib.api.item.ArmorEquipmentType;
import net.pitan76.mcpitanlib.midohra.entity.EntityWrapper;

public class PostHitEvent extends BaseEvent {
    public ItemStack stack;
    public LivingEntity target;
    public LivingEntity attacker;

    public PostHitEvent(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        this.stack = stack;
        this.target = target;
        this.attacker = attacker;
    }

    public ItemStack getStack() {
        return stack;
    }

    public LivingEntity getAttacker() {
        return attacker;
    }

    public LivingEntity getTarget() {
        return target;
    }

    /**
     * Damages the stack in the given slot
     * @param amount the amount of damage to deal
     * @param slot the slot to damage
     */
    public void damageStack(int amount, EquipmentSlot slot) {
        stack.damage(amount, attacker, slot);
    }

    public net.pitan76.mcpitanlib.midohra.item.ItemStack getStackM() {
        return net.pitan76.mcpitanlib.midohra.item.ItemStack.of(getStack());
    }

    public EntityWrapper getAttackerM() {
        return EntityWrapper.of(getAttacker());
    }

    public EntityWrapper getTargetM() {
        return EntityWrapper.of(getTarget());
    }

    public void damageStack(int amount, ArmorEquipmentType type) {
        damageStack(amount, type.getSlot());
    }
}
