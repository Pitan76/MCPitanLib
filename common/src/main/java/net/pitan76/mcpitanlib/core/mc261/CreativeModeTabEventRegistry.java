package net.pitan76.mcpitanlib.core.mc261;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

import java.util.List;
import java.util.function.Supplier;

public class CreativeModeTabEventRegistry {
    @ExpectPlatform
    public static void addStack(ResourceKey<CreativeModeTab> key, Supplier<ItemStack> supplier) {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static void addStacks(ResourceKey<CreativeModeTab> key, Supplier<List<ItemStack>> supplier) {
        throw new AssertionError();
    }
}
