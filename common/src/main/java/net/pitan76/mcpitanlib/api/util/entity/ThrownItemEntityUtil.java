package net.pitan76.mcpitanlib.api.util.entity;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.projectile.throwableitemprojectile.ThrowableItemProjectile;
import net.minecraft.world.item.ItemStack;
import net.pitan76.mcpitanlib.api.entity.CompatThrownItemEntity;

public class ThrownItemEntityUtil {
    public static ItemStack getItem(ThrowableItemProjectile entity) {
        return entity.getItem();
    }

    public static void setItem(ThrowableItemProjectile entity, ItemStack stack) {
        entity.setItem(stack);
    }

    public static ItemStack getStack(ThrowableItemProjectile entity) {
        return entity.getItem();
    }

    public static void setVelocity(ThrowableItemProjectile entity, Entity shooter, float pitch, float yaw, float roll, float speed, float divergence) {
        ProjectileEntityUtil.setVelocity(entity, shooter, pitch, yaw, roll, speed, divergence);
    }

    public static void setVelocity(CompatThrownItemEntity entity, Entity shooter, float pitch, float yaw, float roll, float speed, float divergence) {
        ProjectileEntityUtil.setVelocity(entity, shooter, pitch, yaw, roll, speed, divergence);
    }

    public static void setVelocity(ThrowableItemProjectile entity, double x, double y, double z, float power, float uncertainty) {
        ProjectileEntityUtil.setVelocity(entity, x, y, z, power, uncertainty);
    }

    public static void setVelocity(CompatThrownItemEntity entity, double x, double y, double z, float power, float uncertainty) {
        ProjectileEntityUtil.setVelocity(entity, x, y, z, power, uncertainty);
    }
}
