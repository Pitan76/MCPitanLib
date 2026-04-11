package net.pitan76.mcpitanlib.core.registry;

import net.fabricmc.fabric.api.registry.FuelValueEvents;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class FuelRegistry {
    private FuelRegistry() {

    }

    public static void register(int time, ItemLike... item) {
        FuelValueEvents.BUILD.register(((builder, context) -> {
            for (ItemLike i : item) {
                builder.add(i.asItem(), context.baseSmeltTime() * time);
            }
        }));
    }

    @Deprecated
    public static int get(ItemStack stack) {
        return 0;
    }

    public static int get(Level world, ItemStack stack) {
        return world.fuelValues().burnDuration(stack);
    }

    public static boolean isFuel(Level world, ItemStack stack) {
        return world.fuelValues().isFuel(stack);
    }
}
