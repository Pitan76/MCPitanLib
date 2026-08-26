package net.pitan76.mcpitanlib.core.mc261.fabric;

import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.pitan76.mcpitanlib.api.util.ItemStackUtil;
import net.pitan76.mcpitanlib.api.util.item.ItemGroupUtil;

import java.util.List;
import java.util.function.Supplier;

public class CreativeModeTabEventRegistryImpl {
    public static void addStack(ResourceKey<CreativeModeTab> key, Supplier<ItemStack> supplier) {
        CreativeModeTabEvents.modifyOutputEvent(key).register(entries -> {
            ItemStack stack = supplier.get();
            if (stack == null || ItemStackUtil.isEmpty(stack)) return;

            entries.accept(stack);
        });
    }

    public static void addStacks(ResourceKey<CreativeModeTab> key, Supplier<List<ItemStack>> supplier) {
        CreativeModeTabEvents.modifyOutputEvent(key).register(entries -> {
            for (ItemStack stack : supplier.get()) {
                if (stack == null || ItemStackUtil.isEmpty(stack)) continue;
                entries.accept(stack);
            }
        });
    }

    public static void addStackLazy(Supplier<ResourceKey<CreativeModeTab>> keySupplier, Supplier<ItemStack> supplier) {
        // 登録時点ではアイテムグループがまだ登録されていないことがあるため、イベント発火時に解決する
        CreativeModeTabEvents.MODIFY_OUTPUT_ALL.register((group, output) -> {
            ResourceKey<CreativeModeTab> key = keySupplier.get();
            if (key == null) return;

            Identifier id = ItemGroupUtil.toID(group);
            if (id == null || !key.identifier().equals(id)) return;

            ItemStack stack = supplier.get();
            if (stack == null || ItemStackUtil.isEmpty(stack)) return;
            output.accept(stack);
        });
    }
}
