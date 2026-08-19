package net.pitan76.mcpitanlib.core.registry.fabric;

import net.fabricmc.fabric.api.registry.FuelRegistryEvents;
import net.minecraft.item.ItemConvertible;

import java.util.function.Supplier;

public class FuelRegistryImpl {
    public static void register(int time, Supplier<ItemConvertible> item) {
        FuelRegistryEvents.BUILD.register((builder, context) -> builder.add(item.get(), time));
    }
}
