package net.pitan76.mcpitanlib.core.registry;

import net.minecraft.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.pitan76.mcpitanlib.midohra.item.ItemWrapper;

import java.util.function.Supplier;

public class FuelRegistry {
    private FuelRegistry() {

    }

    public static void register(int time, Supplier<ItemConvertible> item) {
        dev.architectury.registry.fuel.FuelRegistry.register(time, item.get());
    }

    public static void register(int time, ItemConvertible... item) {
        for (ItemConvertible i : item) {
            register(time, () -> i);
        }
    }

    @Deprecated
    public static int get(ItemStack stack) {
        return dev.architectury.registry.fuel.FuelRegistry.get(stack);
    }

    public static int get(World world, ItemStack stack) {
        return dev.architectury.registry.fuel.FuelRegistry.get(stack);
    }

    public static boolean isFuel(World world, ItemStack stack) {
        return AbstractFurnaceBlockEntity.canUseAsFuel(stack);
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
