package net.pitan76.mcpitanlib.api.util.entity;

import net.minecraft.entity.projectile.SmallFireballEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class SmallFireballEntityUtil {
    public static SmallFireballEntity create(World world, double x, double y, double z, double velocityX, double velocityY, double velocityZ) {
        return new SmallFireballEntity(world, x, y, z, velocityX, velocityY, velocityZ);
    }

    public static SmallFireballEntity create(World world, double x, double y, double z, Vec3d velocity) {
        return create(world, x, y, z, velocity.x, velocity.y, velocity.z);
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
