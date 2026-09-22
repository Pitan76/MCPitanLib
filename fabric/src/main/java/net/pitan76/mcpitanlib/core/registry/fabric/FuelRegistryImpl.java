package net.pitan76.mcpitanlib.core.registry.fabric;

import net.fabricmc.fabric.api.item.v1.DefaultItemComponentEvents;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.component.CookingFuel;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.storage.loot.providers.number.floats.ResolvableFloat;
import net.minecraft.world.level.storage.loot.providers.number.ints.ResolvableInt;

import java.util.function.Supplier;

/**
 * 26.3 から燃料は minecraft:cooking_fuel コンポーネントになったため、
 * アイテムのデフォルトコンポーネントを書き換えて登録する。
 */
public class FuelRegistryImpl {
    public static void register(int time, Supplier<ItemLike> item) {
        DefaultItemComponentEvents.MODIFY.register(context ->
                context.modify(item.get().asItem(), builder ->
                        builder.set(DataComponents.COOKING_FUEL, new CookingFuel(new ResolvableInt.Constant(time), new ResolvableFloat.Constant(1.0F)))));
    }
}
