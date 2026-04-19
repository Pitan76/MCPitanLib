package net.pitan76.mcpitanlib.core.mc261.neoforge;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

@EventBusSubscriber(modid = "mcpitanlib")
public class CreativeModeTabEventRegistryImpl {

    private static final List<Consumer<BuildCreativeModeTabContentsEvent>> tabModifiers = new ArrayList<>();

    public static void addStack(ResourceKey<CreativeModeTab> key, Supplier<ItemStack> supplier) {
        tabModifiers.add(event -> {
            if (event.getTabKey().equals(key)) {
                event.accept(supplier.get());
            }
        });
    }

    public static void addStacks(ResourceKey<CreativeModeTab> key, Supplier<List<ItemStack>> supplier) {
        tabModifiers.add(event -> {
            if (event.getTabKey().equals(key)) {
                event.acceptAll(supplier.get());
            }
        });
    }

    @SubscribeEvent
    public static void onBuildCreativeTabContents(BuildCreativeModeTabContentsEvent event) {
        for (Consumer<BuildCreativeModeTabContentsEvent> modifier : tabModifiers) {
            modifier.accept(event);
        }
    }
}