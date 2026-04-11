package net.pitan76.mcpitanlib.api.util.entity;

import net.minecraft.world.entity.projectile.hurtingprojectile.SmallFireball;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.level.Level;
import net.pitan76.mcpitanlib.api.util.math.Vec3dUtil;

public class SmallFireballEntityUtil {
    public static SmallFireball create(Level world, double x, double y, double z, double velocityX, double velocityY, double velocityZ) {
        return new SmallFireball(world, x, y, z, Vec3dUtil.create(velocityX, velocityY, velocityZ));
    }

    public static SmallFireball create(Level world, double x, double y, double z, Vec3 velocity) {
        return new SmallFireball(world, x, y, z, velocity);
    }

    public static void setVelocity(SmallFireball entity, double x, double y, double z, float velocity, float divergence) {
        entity.shoot(x, y, z, velocity, divergence);
    }

    public static void setItem(SmallFireball entity, ItemStack stack) {
        entity.setItem(stack);
    }

    public static ItemStack getItem(SmallFireball entity) {
        return entity.getItem();
    }
}
