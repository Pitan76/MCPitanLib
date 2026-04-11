package net.pitan76.mcpitanlib.api.util.entity;

import net.minecraft.world.entity.projectile.arrow.SpectralArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.pitan76.mcpitanlib.api.util.ItemStackUtil;
import org.jetbrains.annotations.Nullable;

public class SpectralArrowEntityUtil {
    public static SpectralArrow create(Level world, double x, double y, double z, ItemStack stack, @Nullable ItemStack shotFrom) {
        return new SpectralArrow(world, x, y, z, stack, shotFrom);
    }

    public static SpectralArrow create(Level world, double x, double y, double z, ItemStack stack) {
        return create(world, x, y, z, stack, null);
    }

    public static SpectralArrow create(Level world, double x, double y, double z) {
        return create(world, x, y, z, ItemStackUtil.getDefaultStack(Items.ARROW));
    }

    public static void setVelocity(SpectralArrow arrow, double x, double y, double z, float velocity, float divergence) {
        arrow.shoot(x, y, z, velocity, divergence);
    }
}
