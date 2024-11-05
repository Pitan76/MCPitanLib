package net.pitan76.mcpitanlib.core.registry;

import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class FuelRegistry {
    private FuelRegistry() {

    }

    public static void register(int time, ItemConvertible... item) {
        dev.architectury.registry.fuel.FuelRegistry.register(time, item);
    }

    @Deprecated
    public static int get(ItemStack stack) {
        return 0;
    }

    public static int get(World world, ItemStack stack) {
        return world.getFuelRegistry().getFuelTicks(stack);
    }
}
