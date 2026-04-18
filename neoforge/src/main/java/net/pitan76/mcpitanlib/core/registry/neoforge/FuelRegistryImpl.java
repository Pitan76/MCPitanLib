package net.pitan76.mcpitanlib.core.registry.neoforge;

import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.furnace.FurnaceFuelBurnTimeEvent;

import java.util.HashMap;
import java.util.Map;

@EventBusSubscriber(modid = "mcpitanlib")
public class FuelRegistryImpl {
    private static final Map<Item, Integer> FUEL_TIMES = new HashMap<>();

    public static void register(int time, ItemLike... items) {
        int burnTimeTicks = 200 * time;
        for (ItemLike itemLike : items) {
            FUEL_TIMES.put(itemLike.asItem(), burnTimeTicks);
        }
    }

    @SubscribeEvent
    public static void onFurnaceFuelBurnTime(FurnaceFuelBurnTimeEvent event) {
        Item item = event.getItemStack().getItem();
        if (FUEL_TIMES.containsKey(item)) event.setBurnTime(FUEL_TIMES.get(item));
    }
}