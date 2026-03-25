package net.pitan76.mcpitanlib.api.util.entity;

import net.minecraft.entity.projectile.thrown.SnowballEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.pitan76.mcpitanlib.api.util.ItemStackUtil;

public class SnowballEntityUtil {
    public static SnowballEntity create(World world, double x, double y, double z) {
        return new SnowballEntity(world, x, y, z, ItemStackUtil.empty());
    }

    public static SnowballEntity create(World world, double x, double y, double z, ItemStack stack) {
        return new SnowballEntity(world, x, y, z, stack);
    }

    public static void setVelocity(SnowballEntity entity, double x, double y, double z, float velocity, float divergence) {
        entity.setVelocity(x, y, z, velocity, divergence);
    }

    public static void setItem(SnowballEntity entity, ItemStack stack) {
        entity.setItem(stack);
    }

    public static ItemStack getItem(SnowballEntity entity) {
        return entity.getStack();
    }
}
