package net.pitan76.mcpitanlib.api.util.entity;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.projectile.Projectile;

public class ProjectileEntityUtil {
    public static void setVelocity(Projectile projectileEntity, Entity shooter, float pitch, float yaw, float roll, float speed, float divergence) {
        projectileEntity.shootFromRotation(shooter, pitch, yaw, roll, speed, divergence);
    }

    public static void setVelocity(Projectile projectileEntity, double x, double y, double z, float power, float uncertainty) {
        projectileEntity.shoot(x, y, z, power, uncertainty);
    }
}
