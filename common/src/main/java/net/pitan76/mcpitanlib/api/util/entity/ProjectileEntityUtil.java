package net.pitan76.mcpitanlib.api.util.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

public class ProjectileEntityUtil {
    public static void setVelocity(ProjectileEntity projectileEntity, Entity shooter, float pitch, float yaw, float roll, float speed, float divergence) {
        float f = -MathHelper.sin(yaw * ((float)Math.PI / 180F)) * MathHelper.cos(pitch * ((float)Math.PI / 180F));
        float g = -MathHelper.sin((pitch + roll) * ((float)Math.PI / 180F));
        float h = MathHelper.cos(yaw * ((float)Math.PI / 180F)) * MathHelper.cos(pitch * ((float)Math.PI / 180F));
        projectileEntity.setVelocity(f, g, h, speed, divergence);
        Vec3d vec3d = shooter.getVelocity();
        projectileEntity.setVelocity(projectileEntity.getVelocity().add(vec3d.x, shooter.isOnGround() ? (double)0.0F : vec3d.y, vec3d.z));
    }

    public static void setVelocity(ProjectileEntity projectileEntity, double x, double y, double z, float power, float uncertainty) {
        projectileEntity.setVelocity(x, y, z, power, uncertainty);
    }
}
