package net.pitan76.mcpitanlib.core.registry.fabric;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;
import net.pitan76.mcpitanlib.api.util.item.ItemGroupUtil;

import java.util.List;
import java.util.function.Supplier;

public class CreativeTabEventRegistryImpl {
    private static final com.google.common.collect.Multimap<Identifier, Supplier<ItemStack>> APPENDS = com.google.common.collect.MultimapBuilder.hashKeys().arrayListValues().build();

    static {
        ItemGroupEvents.MODIFY_ENTRIES_ALL.register((group, entries) -> {
            Identifier id = ItemGroupUtil.toID(group);
            if (id != null && APPENDS.containsKey(id)) {
                for (Supplier<ItemStack> supplier : APPENDS.get(id)) {
                    ItemStack stack = supplier.get();
                    if (stack != null && !stack.isEmpty()) {
                        entries.add(stack);
                    }
                }
            }
        });
    }

    public static void addStack(RegistryKey<ItemGroup> key, Supplier<ItemStack> supplier) {
        APPENDS.put(key.getValue(), supplier);
        ItemGroupEvents.modifyEntriesEvent(key).register(entries -> {
            ItemStack stack = supplier.get();
            if (stack != null && !stack.isEmpty()) {
                entries.add(stack);
            }
        });
    }

    public static void addStacks(RegistryKey<ItemGroup> key, Supplier<List<ItemStack>> supplier) {
        APPENDS.put(key.getValue(), () -> {
            List<ItemStack> list = supplier.get();
            return (list != null && !list.isEmpty()) ? list.get(0) : ItemStack.EMPTY;
        });
        ItemGroupEvents.modifyEntriesEvent(key).register(entries -> {
            for (ItemStack stack : supplier.get()) {
                if (stack != null && !stack.isEmpty()) {
                    entries.add(stack);
                }
            }
        });
    }

    public static void addStackLazy(Supplier<RegistryKey<ItemGroup>> keySupplier, Supplier<ItemStack> supplier) {
        ItemGroupEvents.MODIFY_ENTRIES_ALL.register((group, entries) -> {
            RegistryKey<ItemGroup> key = keySupplier.get();
            if (key == null) return;

            Identifier id = ItemGroupUtil.toID(group);
            if (id == null || !key.getValue().equals(id)) return;

            ItemStack stack = supplier.get();
            if (stack != null && !stack.isEmpty()) {
                entries.add(stack);
            }
        });
    }
}
