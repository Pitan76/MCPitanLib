package net.pitan76.mcpitanlib.core.registry.neoforge;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.RegistryKey;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.pitan76.mcpitanlib.MCPitanLib;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

@EventBusSubscriber(modid = MCPitanLib.MOD_ID)
public class CreativeTabEventRegistryImpl {

    private static final List<Consumer<BuildCreativeModeTabContentsEvent>> tabModifiers = new ArrayList<>();

    public static void addStack(RegistryKey<ItemGroup> key, Supplier<ItemStack> supplier) {
        tabModifiers.add(event -> {
            if (event.getTabKey().equals(key))
                event.accept(supplier.get());
        });
    }

    public static void addStacks(RegistryKey<ItemGroup> key, Supplier<List<ItemStack>> supplier) {
        tabModifiers.add(event -> {
            if (event.getTabKey().equals(key))
                event.acceptAll(supplier.get());
        });
    }

    public static void addStackLazy(Supplier<RegistryKey<ItemGroup>> keySupplier, Supplier<ItemStack> supplier) {
        // キーはイベント発火時に一度だけ解決する (登録時点ではアイテムグループが未登録の可能性があるため)
        tabModifiers.add(new Consumer<BuildCreativeModeTabContentsEvent>() {
            private RegistryKey<ItemGroup> key;
            private boolean resolved = false;

            @Override
            public void accept(BuildCreativeModeTabContentsEvent event) {
                if (!resolved) {
                    key = keySupplier.get();
                    resolved = true;
                }
                if (key == null) return;

                if (event.getTabKey().equals(key))
                    event.accept(supplier.get());
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
