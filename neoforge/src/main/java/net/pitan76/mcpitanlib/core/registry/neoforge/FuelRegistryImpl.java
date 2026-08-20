package net.pitan76.mcpitanlib.core.registry.neoforge;

import com.google.common.base.Supplier;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.furnace.FurnaceFuelBurnTimeEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@EventBusSubscriber(modid = "mcpitanlib")
public class FuelRegistryImpl {
    private static final Map<Supplier<ItemLike>, Integer> FUEL_TIMES = new ConcurrentHashMap<>();

    public static void register(int time, Supplier<ItemLike> item) {
        FUEL_TIMES.put(item, time);
    }

    @SubscribeEvent
    public static void onFurnaceFuelBurnTime(FurnaceFuelBurnTimeEvent event) {
        Item item = event.getItemStack().getItem();
        for (Map.Entry<Supplier<ItemLike>, Integer> entry : FUEL_TIMES.entrySet()) {
            ItemLike itemLike = entry.getKey().get();
            if (itemLike != null && itemLike.asItem().equals(item)) {
                event.setBurnTime(entry.getValue());
                break;
            }
        }
    }
}