package net.pitan76.mcpitanlib.api.util.entity;

import net.minecraft.entity.projectile.SmallFireballEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.pitan76.mcpitanlib.api.util.math.Vec3dUtil;

public class SmallFireballEntityUtil {
    public static SmallFireballEntity create(World world, double x, double y, double z, double velocityX, double velocityY, double velocityZ) {
        return new SmallFireballEntity(world, x, y, z, Vec3dUtil.create(velocityX, velocityY, velocityZ));
    }

    public static SmallFireballEntity create(World world, double x, double y, double z, Vec3d velocity) {
        return new SmallFireballEntity(world, x, y, z, velocity);
    }

    public static void setVelocity(SmallFireballEntity entity, double x, double y, double z, float velocity, float divergence) {
        entity.setVelocity(x, y, z, velocity, divergence);
    }

    public static void setItem(SmallFireballEntity entity, ItemStack stack) {
        entity.setItem(stack);
    }

    public static ItemStack getItem(SmallFireballEntity entity) {
        return entity.getStack();
    }
}
