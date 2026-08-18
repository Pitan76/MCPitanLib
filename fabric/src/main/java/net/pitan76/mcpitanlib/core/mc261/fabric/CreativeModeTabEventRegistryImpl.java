package net.pitan76.mcpitanlib.core.mc261.fabric;

import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

import java.util.List;
import java.util.function.Supplier;

public class CreativeModeTabEventRegistryImpl {
    public static void addStack(ResourceKey<CreativeModeTab> key, Supplier<ItemStack> supplier) {
        CreativeModeTabEvents.modifyOutputEvent(key).register(entries -> entries.accept(supplier.get()));
    }

    public static void addStacks(ResourceKey<CreativeModeTab> key, Supplier<List<ItemStack>> supplier) {
        CreativeModeTabEvents.modifyOutputEvent(key).register(entries -> entries.acceptAll(supplier.get()));
    }

    public static void addStackLazy(Supplier<ResourceKey<CreativeModeTab>> keySupplier, Supplier<ItemStack> supplier) {
        // Fabricではアイテムグループが即時登録されるため、その場で解決できる
        ResourceKey<CreativeModeTab> key = keySupplier.get();
        if (key == null) return;

        addStack(key, supplier);
    }
}
