package net.pitan76.mcpitanlib.core.registry.fabric;

import net.fabricmc.fabric.api.registry.FuelValueEvents;
import net.minecraft.world.level.ItemLike;

import java.util.function.Supplier;

public class FuelRegistryImpl {
    public static void register(int time, Supplier<ItemLike> item) {
        FuelValueEvents.BUILD.register((builder, context) -> builder.add(item.get(), time));
    }
}
