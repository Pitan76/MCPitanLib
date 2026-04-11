package net.pitan76.mcpitanlib.api.util.entity;

import net.minecraft.world.entity.projectile.throwableitemprojectile.Snowball;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.pitan76.mcpitanlib.api.util.ItemStackUtil;

public class SnowballEntityUtil {
    public static Snowball create(Level world, double x, double y, double z) {
        return new Snowball(world, x, y, z, ItemStackUtil.empty());
    }

    public static Snowball create(Level world, double x, double y, double z, ItemStack stack) {
        return new Snowball(world, x, y, z, stack);
    }

    public static void setVelocity(Snowball entity, double x, double y, double z, float velocity, float divergence) {
        entity.shoot(x, y, z, velocity, divergence);
    }

    public static void setItem(Snowball entity, ItemStack stack) {
        entity.setItem(stack);
    }

    public static ItemStack getItem(Snowball entity) {
        return entity.getItem();
    }
}
