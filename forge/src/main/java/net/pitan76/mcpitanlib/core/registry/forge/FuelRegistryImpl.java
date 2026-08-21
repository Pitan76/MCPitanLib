package net.pitan76.mcpitanlib.core.registry.forge;

import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.event.furnace.FurnaceFuelBurnTimeEvent;
import net.pitan76.mcpitanlib.MCPitanLib;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;
import java.util.concurrent.ConcurrentHashMap;

@EventBusSubscriber(modid = MCPitanLib.MOD_ID)
public class FuelRegistryImpl {
    private static final Map<Supplier<ItemConvertible>, Integer> FUEL_TIMES = new ConcurrentHashMap<>();

    public static void register(int time, Supplier<ItemConvertible> item) {
        FUEL_TIMES.put(item, time);
    }

    @SubscribeEvent
    public static void onFurnaceFuelBurnTime(FurnaceFuelBurnTimeEvent event) {
        Item item = event.getItemStack().getItem();
        for (Map.Entry<Supplier<ItemConvertible>, Integer> entry : FUEL_TIMES.entrySet()) {
            ItemConvertible itemConvertible = entry.getKey().get();
            if (itemConvertible != null && itemConvertible.asItem().equals(item)) {
                event.setBurnTime(entry.getValue());
                break;
            }
        }
    }

    public static int get(ItemStack stack) {
        return ForgeHooks.getBurnTime(stack, null);
    }

    public static boolean isFuel(ItemStack stack) {
        return stack.getBurnTime(null) > 0;
    }
}

