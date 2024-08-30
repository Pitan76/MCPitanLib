package net.pitan76.mcpitanlib.api.util.entity;

import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.world.World;
import net.pitan76.mcpitanlib.api.util.ItemStackUtil;
import org.jetbrains.annotations.Nullable;

public class ArrowEntityUtil {
    public static ArrowEntity create(World world, double x, double y, double z, ItemStack stack, @Nullable ItemStack shotFrom) {
        return new ArrowEntity(world, x, y, z, stack);
    }

    public static ArrowEntity create(World world, double x, double y, double z, ItemStack stack) {
        return create(world, x, y, z, stack, null);
    }

    public static ArrowEntity create(World world, double x, double y, double z) {
        return create(world, x, y, z, ItemStackUtil.getDefaultStack(Items.ARROW));
    }

    public static void setVelocity(ArrowEntity arrow, double x, double y, double z, float velocity, float divergence) {
        arrow.setVelocity(x, y, z, velocity, divergence);
    }
}
