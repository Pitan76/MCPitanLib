package net.pitan76.mcpitanlib.core.registry.neoforge;

import com.google.common.base.Supplier;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.component.CookingFuel;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.storage.loot.providers.number.floats.ResolvableFloat;
import net.minecraft.world.level.storage.loot.providers.number.ints.ResolvableInt;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.ModifyDefaultComponentsEvent;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 26.3 から燃料は minecraft:cooking_fuel コンポーネントになったため、
 * アイテムのデフォルトコンポーネントを書き換えて登録する。
 */
@EventBusSubscriber(modid = "mcpitanlib")
public class FuelRegistryImpl {
    private static final Map<Supplier<ItemLike>, Integer> FUEL_TIMES = new ConcurrentHashMap<>();

    public static void register(int time, Supplier<ItemLike> item) {
        FUEL_TIMES.put(item, time);
    }

    @SubscribeEvent
    public static void onModifyDefaultComponents(ModifyDefaultComponentsEvent event) {
        FUEL_TIMES.forEach((item, time) -> {
            ItemLike itemLike = item.get();
            if (itemLike == null) return;

            event.modify(itemLike.asItem(), (builder, lookup, target) ->
                    builder.set(DataComponents.COOKING_FUEL, new CookingFuel(new ResolvableInt.Constant(time), new ResolvableFloat.Constant(1.0F))));
        });
    }
}
