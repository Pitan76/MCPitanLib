package net.pitan76.mcpitanlib.core.registry.fabric;

import net.fabricmc.fabric.api.registry.FuelValueEvents;
import net.minecraft.world.level.ItemLike;

public class FuelRegistryImpl {
    public static void register(int time, ItemLike... item) {
        FuelValueEvents.BUILD.register(((builder, context) -> {
            for (ItemLike i : item) {
                builder.add(i.asItem(), context.baseSmeltTime() * time);
            }
        }));
    }
}
