package net.pitan76.mcpitanlib.api.util.item;

import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.pitan76.mcpitanlib.core.registry.FuelRegistry;

public class FuelUtil {

    public static int getTime(World world, ItemStack stack) {
        return FuelRegistry.get(world, stack);
    }

    public static boolean isFuel(World world, ItemStack stack) {
        return FuelRegistry.isFuel(world, stack);
    }
}
