package net.pitan76.mcpitanlib.api.util.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.ItemStack;
import net.pitan76.mcpitanlib.api.entity.CompatThrownItemEntity;

public class ThrownItemEntityUtil {
    public static ItemStack getItem(ThrownItemEntity entity) {
        return entity.getStack();
    }

    public static void setItem(ThrownItemEntity entity, ItemStack stack) {
        entity.setItem(stack);
    }

    public static ItemStack getStack(ThrownItemEntity entity) {
        return entity.getStack();
    }

    public static void setVelocity(ThrownItemEntity entity, Entity shooter, float pitch, float yaw, float roll, float speed, float divergence) {
        ProjectileEntityUtil.setVelocity(entity, shooter, pitch, yaw, roll, speed, divergence);
    }

    public static void setVelocity(CompatThrownItemEntity entity, Entity shooter, float pitch, float yaw, float roll, float speed, float divergence) {
        ProjectileEntityUtil.setVelocity(entity, shooter, pitch, yaw, roll, speed, divergence);
    }

    public static void setVelocity(ThrownItemEntity entity, double x, double y, double z, float power, float uncertainty) {
        ProjectileEntityUtil.setVelocity(entity, x, y, z, power, uncertainty);
    }

    public static void setVelocity(CompatThrownItemEntity entity, double x, double y, double z, float power, float uncertainty) {
        ProjectileEntityUtil.setVelocity(entity, x, y, z, power, uncertainty);
    }
}
