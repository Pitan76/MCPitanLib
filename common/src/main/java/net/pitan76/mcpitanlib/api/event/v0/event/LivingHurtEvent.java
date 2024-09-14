package net.pitan76.mcpitanlib.api.event.v0.event;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
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
        return damageSource.getAttacker();
    }

    public Entity getSource() {
        return damageSource.getSource();
    }

    public boolean isDirect() {
        return damageSource.isDirect();
    }

    public boolean isPlayerAttacker() {
        return getAttacker() instanceof PlayerEntity;
    }

    public PlayerEntity getPlayerEntityAttacker() {
        return (PlayerEntity) getAttacker();
    }

    public Player getPlayerAttacker() {
        return new Player(getPlayerEntityAttacker());
    }

    public World getWorld() {
        return entity.getWorld();
    }

    public boolean isClient() {
        return getWorld().isClient();
    }

    public ItemStack getWeaponStack() {
        return getAttacker().getWeaponStack();
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
