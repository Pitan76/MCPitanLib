package net.pitan76.mcpitanlib.api.registry;

import net.minecraft.world.level.ItemLike;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class FuelRegistry {

    private static final Map<String, Map<Supplier<ItemLike>, Integer>> FUEL_MAP = new HashMap<>();

    public static void register(Supplier<ItemLike> itemSupplier, int time, String namespace) {
        Map<Supplier<ItemLike>, Integer> map = new HashMap<>();
        map.put(itemSupplier, time);

        FUEL_MAP.put(namespace, map);
    }

    @Deprecated
    public static void allRegister(String namespace) {
        if (!FUEL_MAP.containsKey(namespace)) return;

        Map<Supplier<ItemLike>, Integer> map = FUEL_MAP.get(namespace);
        for (Map.Entry<Supplier<ItemLike>, Integer> entry : map.entrySet()) {
            net.pitan76.mcpitanlib.core.registry.FuelRegistry.register(entry.getValue(), entry.getKey().get());
        }

        FUEL_MAP.remove(namespace);
    }
}
