package net.pitan76.mcpitanlib.core.registry.fabric;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.RegistryKey;

import java.util.List;
import java.util.function.Supplier;

public class CreativeTabEventRegistryImpl {
    public static void addStack(RegistryKey<ItemGroup> key, Supplier<ItemStack> supplier) {
        ItemGroupEvents.modifyEntriesEvent(key).register(entries -> entries.add(supplier.get()));
    }

    public static void addStacks(RegistryKey<ItemGroup> key, Supplier<List<ItemStack>> supplier) {
        ItemGroupEvents.modifyEntriesEvent(key).register(entries -> {
            for (ItemStack stack : supplier.get()) {
                entries.add(stack);
            }
        });
    }

    public static void addStackLazy(Supplier<RegistryKey<ItemGroup>> keySupplier, Supplier<ItemStack> supplier) {
        // Fabricではアイテムグループが即時登録されるため、その場で解決できる
        RegistryKey<ItemGroup> key = keySupplier.get();
        if (key == null) return;

        addStack(key, supplier);
    }
}
