package net.pitan76.mcpitanlib.core.registry.fabric;

import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.minecraft.item.ItemConvertible;

import java.util.function.Supplier;

public class FuelRegistryImpl {
    public static void register(int time, Supplier<ItemConvertible> item) {
        FuelRegistry.INSTANCE.add(item.get(), time);
    }
}
