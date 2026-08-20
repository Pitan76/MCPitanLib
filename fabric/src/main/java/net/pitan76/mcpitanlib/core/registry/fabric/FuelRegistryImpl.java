package net.pitan76.mcpitanlib.core.registry.fabric;

import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;

import java.util.function.Supplier;

public class FuelRegistryImpl {
    public static void register(int time, Supplier<ItemConvertible> item) {
        FuelRegistry.INSTANCE.add(item.get(), time);
    }

    public static int getBurnTime(ItemStack stack) {
        if (stack.isEmpty()) return 0;

        Integer time = FuelRegistry.INSTANCE.get(stack.getItem());
        return time == null ? 0 : time;
    }
}
