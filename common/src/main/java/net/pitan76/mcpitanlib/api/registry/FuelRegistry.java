package net.pitan76.mcpitanlib.api.registry;

import net.minecraft.item.ItemConvertible;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class FuelRegistry {

    private static final Map<String, Map<Supplier<ItemConvertible>, Integer>> FUEL_MAP = new HashMap<>();

    public static void register(Supplier<ItemConvertible> itemSupplier, int time, String namespace) {
        Map<Supplier<ItemConvertible>, Integer> map = new HashMap<>();
        map.put(itemSupplier, time);

        FUEL_MAP.put(namespace, map);
    }

    @Deprecated
    public static void allRegister(String namespace) {
        Map<Supplier<ItemConvertible>, Integer> map = FUEL_MAP.get(namespace);
        for (Map.Entry<Supplier<ItemConvertible>, Integer> entry : map.entrySet()) {
            net.pitan76.mcpitanlib.core.registry.FuelRegistry.register(entry.getValue(), entry.getKey().get());
        }
    }
}
