package net.pitan76.mcpitanlib.core.registry;

import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.resource.featuretoggle.FeatureSet;
import net.minecraft.world.World;

public class FuelRegistry {
    private FuelRegistry() {

    }

    // TODO: 1.21.2
    public static void register(int time, ItemConvertible... item) {
        //dev.architectury.registry.fuel.FuelRegistry.register(time, item);
    }

    // TODO: 1.21.2
    public static int get(ItemStack stack) {
        return 0;
        //return dev.architectury.registry.fuel.FuelRegistry.get(stack);
    }

    public static int get(World world, ItemStack stack) {
        return world.getFuelRegistry().getFuelTicks(stack);
    }
}
