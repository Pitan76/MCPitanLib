package net.pitan76.mcpitanlib.core.registry.forge;

import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.furnace.FurnaceFuelBurnTimeEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.pitan76.mcpitanlib.MCPitanLib;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Supplier;

@EventBusSubscriber(modid = MCPitanLib.MOD_ID)
public class FuelRegistryImpl {
    private static final Map<Item, Integer> BURN_TIMES = new ConcurrentHashMap<Item, Integer>();

    public static void register(int time, Supplier<ItemConvertible> item) {
        BURN_TIMES.put(item.get().asItem(), time);
    }

    public static int getBurnTime(ItemStack stack) {
        if (stack.isEmpty()) return 0;

        Integer time = BURN_TIMES.get(stack.getItem());
        return time == null ? 0 : time;
    }

    @SubscribeEvent
    public static void onFurnaceFuelBurnTime(FurnaceFuelBurnTimeEvent event) {
        int time = getBurnTime(event.getItemStack());
        if (time > 0) event.setBurnTime(time);
    }
}
