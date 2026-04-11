package net.pitan76.mcpitanlib.api.util.item;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.pitan76.mcpitanlib.core.registry.FuelRegistry;

public class FuelUtil {

    public static int getTime(Level world, ItemStack stack) {
        return FuelRegistry.get(world, stack);
    }

    public static boolean isFuel(Level world, ItemStack stack) {
        return FuelRegistry.isFuel(world, stack);
    }
}
