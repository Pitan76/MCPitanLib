package net.pitan76.mcpitanlib.api.event.v0.event;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.pitan76.mcpitanlib.api.entity.Player;

public class LivingHurtEvent {
    public LivingEntity entity;
    public DamageSource damageSource;
    public float damageAmount;

    public LivingHurtEvent(LivingEntity entity, DamageSource damageSource, float damageAmount) {
        this.entity = entity;
        this.damageSource = damageSource;
        this.damageAmount = damageAmount;
    }

    public LivingEntity getEntity() {
        return entity;
    }

    public DamageSource getDamageSource() {
        return damageSource;
    }

    public float getDamageAmount() {
        return damageAmount;
    }

    public Entity getAttacker() {
        return damageSource.getEntity();
    }

    public Entity getSource() {
        return damageSource.getDirectEntity();
    }

    public boolean isDirect() {
        return damageSource.isDirect();
    }

    public boolean isPlayerAttacker() {
        return getAttacker() instanceof Player;
    }

    public Player getPlayerEntityAttacker() {
        return (Player) getAttacker();
    }

    public Player getPlayerAttacker() {
        return new Player(getPlayerEntityAttacker());
    }

    public Level getWorld() {
        return entity.level();
    }

    public boolean isClient() {
        return getWorld().isClientSide();
    }

    public ItemStack getWeaponStack() {
        return getAttacker().getWeaponItem();
    }

    public Item getWeaponItem() {
        return getWeaponStack().getItem();
    }

    public boolean isWeaponEmpty() {
        return getWeaponStack().isEmpty();
    }

    public boolean isWeaponItemEqual(Item item) {
        if (isWeaponEmpty()) return false;
        return getWeaponItem() == item;
    }
}
