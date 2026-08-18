package net.pitan76.mcpitanlib.core.registry;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.pitan76.mcpitanlib.midohra.item.ItemWrapper;

import java.util.function.Supplier;

public class FuelRegistry {
    private FuelRegistry() {

    }

    @ExpectPlatform
    public static void register(int time, Supplier<ItemConvertible> item) {

    }

    public static void register(int time, ItemConvertible... item) {
        for (ItemConvertible i : item) {
            register(time, () -> i);
        }
    }

    @ExpectPlatform
    public static int get(ItemStack stack) {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static boolean isFuel(ItemStack stack) {
        throw new AssertionError();
    }

    public static int get(World world, ItemStack stack) {
        return get(stack);
    }

    public static boolean isFuel(World world, ItemStack stack) {
        return isFuel(stack);
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
