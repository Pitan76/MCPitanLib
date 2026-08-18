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
import java.util.concurrent.CopyOnWriteArrayList;

@EventBusSubscriber(modid = "mcpitanlib")
public class CreativeModeTabEventRegistryImpl {

    private static final List<Consumer<BuildCreativeModeTabContentsEvent>> tabModifiers = new CopyOnWriteArrayList<>();

    public static void addStack(ResourceKey<CreativeModeTab> key, Supplier<ItemStack> supplier) {
        tabModifiers.add(event -> {
            if (event.getTabKey().equals(key)) {
                acceptStack(event, supplier.get());
            }
        });
    }

    public static void addStacks(ResourceKey<CreativeModeTab> key, Supplier<List<ItemStack>> supplier) {
        tabModifiers.add(event -> {
            if (event.getTabKey().equals(key)) {
                for (ItemStack stack : supplier.get()) {
                    acceptStack(event, stack);
                }
            }
        });
    }

    /**
     * NeoForgeは同じItemStackを二重に追加すると例外を投げるため、既に入っている場合は追加しない。
     * <p>
     * アイテムのsupplierが複数回実行されるなどして同じ登録が二重に積まれることがある。
     */
    private static void acceptStack(BuildCreativeModeTabContentsEvent event, ItemStack stack) {
        if (stack == null || stack.isEmpty()) return;
        if (event.getParentEntries().contains(stack)) return;
        if (event.getSearchEntries().contains(stack)) return;

        event.accept(stack);
    }

    @SubscribeEvent
    public static void onBuildCreativeTabContents(BuildCreativeModeTabContentsEvent event) {
        for (Consumer<BuildCreativeModeTabContentsEvent> modifier : tabModifiers) {
            modifier.accept(event);
        }
    }

    public static void addStackLazy(Supplier<ResourceKey<CreativeModeTab>> keySupplier, Supplier<ItemStack> supplier) {
        // キーはイベント発火時に一度だけ解決する (登録時点ではアイテムグループが未登録の可能性があるため)
        tabModifiers.add(new Consumer<BuildCreativeModeTabContentsEvent>() {
            private ResourceKey<CreativeModeTab> key;
            private boolean resolved = false;

            @Override
            public void accept(BuildCreativeModeTabContentsEvent event) {
                if (!resolved) {
                    key = keySupplier.get();
                    resolved = true;
                }
                if (key == null) return;

                if (event.getTabKey().equals(key))
                    acceptStack(event, supplier.get());
            }
        });
    }
}
