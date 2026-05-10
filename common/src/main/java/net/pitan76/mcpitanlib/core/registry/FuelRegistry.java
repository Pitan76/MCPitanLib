package net.pitan76.mcpitanlib.core.registry;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.pitan76.mcpitanlib.midohra.item.ItemWrapper;

import java.util.function.Supplier;

public class FuelRegistry {
    private FuelRegistry() {

    }

    @ExpectPlatform
    public static void register(int time, Supplier<ItemLike> item) {

    }

    public static void register(int time, ItemLike... item) {
        for (ItemLike i : item) {
            register(time, () -> i);
        }
    }

    @Deprecated
    public static int get(ItemStack stack) {
        return 0;
    }

    public static int get(Level world, ItemStack stack) {
        return world.fuelValues().burnDuration(stack);
    }

    public static boolean isFuel(Level world, ItemStack stack) {
        return world.fuelValues().isFuel(stack);
    }

    public static void register(int time, ItemWrapper item) {
        register(time, item::get);
    }

    public static int get(net.pitan76.mcpitanlib.midohra.world.World world, net.pitan76.mcpitanlib.midohra.item.ItemStack stack) {
        return get(world.toMinecraft(), stack.toMinecraft());
    }

    public static boolean isFuel(net.pitan76.mcpitanlib.midohra.world.World world, net.pitan76.mcpitanlib.midohra.item.ItemStack stack) {
        return isFuel(world.toMinecraft(), stack.toMinecraft());
    }
}
