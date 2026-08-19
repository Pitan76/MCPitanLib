package net.pitan76.mcpitanlib.core.registry;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.RegistryKey;

import java.util.List;
import java.util.function.Supplier;

public class CreativeTabEventRegistry {
    @ExpectPlatform
    public static void addStack(RegistryKey<ItemGroup> key, Supplier<ItemStack> supplier) {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static void addStacks(RegistryKey<ItemGroup> key, Supplier<List<ItemStack>> supplier) {
        throw new AssertionError();
    }

    /**
     * アイテムグループのキーを遅延解決して登録する。
     * NeoForgeではアイテムグループの登録が遅延されるため、登録時点ではキーを解決できないことがある。
     */
    @ExpectPlatform
    public static void addStackLazy(Supplier<RegistryKey<ItemGroup>> keySupplier, Supplier<ItemStack> supplier) {
        throw new AssertionError();
    }
}
