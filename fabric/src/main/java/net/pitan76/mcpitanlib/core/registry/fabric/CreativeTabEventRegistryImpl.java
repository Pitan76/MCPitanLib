package net.pitan76.mcpitanlib.core.registry.fabric;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;
import net.pitan76.mcpitanlib.api.util.ItemStackUtil;
import net.pitan76.mcpitanlib.api.util.item.ItemGroupUtil;

import java.util.List;
import java.util.function.Supplier;

public class CreativeTabEventRegistryImpl {
    public static void addStack(RegistryKey<ItemGroup> key, Supplier<ItemStack> supplier) {
        ItemGroupEvents.modifyEntriesEvent(key).register(entries -> {
            ItemStack stack = supplier.get();
            if (stack == null || ItemStackUtil.isEmpty(stack)) return;

            entries.add(stack);
        });
    }

    public static void addStacks(RegistryKey<ItemGroup> key, Supplier<List<ItemStack>> supplier) {
        ItemGroupEvents.modifyEntriesEvent(key).register(entries -> {
            for (ItemStack stack : supplier.get()) {
                if (stack == null || ItemStackUtil.isEmpty(stack)) continue;
                entries.add(stack);
            }
        });
    }

    public static void addStackLazy(Supplier<RegistryKey<ItemGroup>> keySupplier, Supplier<ItemStack> supplier) {
        // 登録時点ではアイテムグループがまだ登録されていないことがあるため、イベント発火時に解決する
        ItemGroupEvents.MODIFY_ENTRIES_ALL.register((group, entries) -> {
            RegistryKey<ItemGroup> key = keySupplier.get();
            if (key == null) return;

            Identifier id = ItemGroupUtil.toID(group);
            if (id == null || !key.getValue().equals(id)) return;

            ItemStack stack = supplier.get();
            if (stack == null || ItemStackUtil.isEmpty(stack)) return;

            entries.add(supplier.get());
        });
    }
}
