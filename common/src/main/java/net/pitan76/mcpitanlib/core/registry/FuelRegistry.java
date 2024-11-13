package net.pitan76.mcpitanlib.core.registry;

import net.minecraft.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class FuelRegistry {
    private FuelRegistry() {

    }

    public static void register(int time, ItemConvertible... item) {
        me.shedaniel.architectury.registry.fuel.FuelRegistry.register(time, item);
    }

    @Deprecated
    public static int get(ItemStack stack) {
        return me.shedaniel.architectury.registry.fuel.FuelRegistry.get(stack);
    }

    public static int get(World world, ItemStack stack) {
        return me.shedaniel.architectury.registry.fuel.FuelRegistry.get(stack);
    }

    public static boolean isFuel(World world, ItemStack stack) {
        return AbstractFurnaceBlockEntity.canUseAsFuel(stack);
    }
}
