package net.pitan76.mcpitanlib.core.registry.forge;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.RegistryKey;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.pitan76.mcpitanlib.MCPitanLib;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.concurrent.CopyOnWriteArrayList;

@EventBusSubscriber(modid = MCPitanLib.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class CreativeTabEventRegistryImpl {

    private static final List<Consumer<BuildCreativeModeTabContentsEvent>> tabModifiers = new CopyOnWriteArrayList<>();

    public static void addStack(RegistryKey<ItemGroup> key, Supplier<ItemStack> supplier) {
        tabModifiers.add(event -> {
            if (event.getTabKey().equals(key))
                add(event, supplier.get());
        });
    }

    public static void addStacks(RegistryKey<ItemGroup> key, Supplier<List<ItemStack>> supplier) {
        tabModifiers.add(event -> {
            if (event.getTabKey().equals(key)) {
                for (ItemStack stack : supplier.get()) {
                    add(event, stack);
                }
            }
        });
    }

    public static void addStackLazy(Supplier<RegistryKey<ItemGroup>> keySupplier, Supplier<ItemStack> supplier) {
        // 繧ｭ繝ｼ縺ｯ繧､繝吶Φ繝育匱轣ｫ譎ゅ↓荳蠎ｦ縺縺題ｧ｣豎ｺ縺吶ｋ (逋ｻ骭ｲ譎らせ縺ｧ縺ｯ繧｢繧､繝・Β繧ｰ繝ｫ繝ｼ繝励′譛ｪ逋ｻ骭ｲ縺ｮ蜿ｯ閭ｽ諤ｧ縺後≠繧九◆繧・
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
                    add(event, supplier.get());
            }
        });
    }

    /**
     * NeoForge縺ｯ蜷後§ItemStack繧剃ｺ碁㍾縺ｫ霑ｽ蜉縺吶ｋ縺ｨ萓句､悶ｒ謚輔￡繧九◆繧√∵里縺ｫ蜈･縺｣縺ｦ縺・ｋ蝣ｴ蜷医・霑ｽ蜉縺励↑縺・・
     * <p>
     * 繧｢繧､繝・Β縺ｮsupplier縺瑚､・焚蝗槫ｮ溯｡後＆繧後ｋ縺ｪ縺ｩ縺励※蜷後§逋ｻ骭ｲ縺御ｺ碁㍾縺ｫ遨阪∪繧後ｋ縺薙→縺後≠繧九・
     */
    private static void add(BuildCreativeModeTabContentsEvent event, ItemStack stack) {
        if (stack == null || stack.isEmpty()) return;

        event.add(stack);
    }

    @SubscribeEvent
    public static void onBuildCreativeTabContents(BuildCreativeModeTabContentsEvent event) {
        for (Consumer<BuildCreativeModeTabContentsEvent> modifier : tabModifiers) {
            modifier.accept(event);
        }
    }
}

