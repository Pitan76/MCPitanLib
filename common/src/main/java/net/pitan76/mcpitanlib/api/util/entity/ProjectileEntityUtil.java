package net.pitan76.mcpitanlib.api.util.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.projectile.ProjectileEntity;

public class ProjectileEntityUtil {
    public static void setVelocity(ProjectileEntity projectileEntity, Entity shooter, float pitch, float yaw, float roll, float speed, float divergence) {
        projectileEntity.setVelocity(shooter, pitch, yaw, roll, speed, divergence);
    }

    public static void setVelocity(ProjectileEntity projectileEntity, double x, double y, double z, float power, float uncertainty) {
        projectileEntity.setVelocity(x, y, z, power, uncertainty);
    }
}
