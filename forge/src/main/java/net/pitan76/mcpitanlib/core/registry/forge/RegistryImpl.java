package net.pitan76.mcpitanlib.core.registry.forge;

import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.potion.Potion;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.particle.ParticleType;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.registries.RegisterEvent;
import net.pitan76.mcpitanlib.MCPitanLib;
import net.pitan76.mcpitanlib.api.registry.result.RegistrySupplier;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Supplier;

/**
 * Forgeはバニラのレジストリを凍結するため、mod構築時に直接registerすると
 * "Registry is already frozen" で落ちる。RegisterEventまで登録を遅延させる。
 */
@EventBusSubscriber(modid = MCPitanLib.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class RegistryImpl {

    private static class PendingEntry<T> {
        final Supplier<T> factory;
        final RegistrySupplier<T> wrapper;

        PendingEntry(Supplier<T> factory, RegistrySupplier<T> wrapper) {
            this.factory = factory;
            this.wrapper = wrapper;
        }
    }

    // 罠7: Forgeもmodを並列構築するのでスレッドセーフにする
    private static final Map<RegistryKey<? extends Registry<?>>, Map<Identifier, PendingEntry<?>>> PENDING_REGISTRIES = Collections.synchronizedMap(new LinkedHashMap<>());

    private static <T> RegistrySupplier<T> register(RegistryKey<Registry<T>> registryKey, Identifier id, Supplier<T> supplier) {
        RegistrySupplier<T> registrySupplier = new RegistrySupplier<>();

        synchronized (PENDING_REGISTRIES) {
            PENDING_REGISTRIES.computeIfAbsent(registryKey, key -> new LinkedHashMap<>())
                    .put(id, new PendingEntry<>(supplier, registrySupplier));
        }

        return registrySupplier;
    }

    public static RegistrySupplier<Item> registryItem(Identifier id, Supplier<Item> supplier) {
        return register(Registry.ITEM_KEY, id, supplier);
    }

    public static RegistrySupplier<Block> registryBlock(Identifier id, Supplier<Block> supplier) {
        return register(Registry.BLOCK_KEY, id, supplier);
    }

    public static RegistrySupplier<ScreenHandlerType<?>> registryScreenHandlerType(Identifier id, Supplier<ScreenHandlerType<?>> supplier) {
        return register(Registry.MENU_KEY, id, supplier);
    }

    public static RegistrySupplier<BlockEntityType<?>> registryBlockEntityType(Identifier id, Supplier<BlockEntityType<?>> supplier) {
        return register(Registry.BLOCK_ENTITY_TYPE_KEY, id, supplier);
    }

    public static RegistrySupplier<EntityType<?>> registryEntityType(Identifier id, Supplier<EntityType<?>> supplier) {
        return register(Registry.ENTITY_TYPE_KEY, id, supplier);
    }

    public static RegistrySupplier<SoundEvent> registrySoundEvent(Identifier id, Supplier<SoundEvent> supplier) {
        return register(Registry.SOUND_EVENT_KEY, id, supplier);
    }

    public static RegistrySupplier<Fluid> registryFluid(Identifier id, Supplier<Fluid> supplier) {
        return register(Registry.FLUID_KEY, id, supplier);
    }

    public static RegistrySupplier<ParticleType<?>> registryParticleType(Identifier id, Supplier<ParticleType<?>> supplier) {
        return register(Registry.PARTICLE_TYPE_KEY, id, supplier);
    }

    public static RegistrySupplier<StatusEffect> registryStatusEffect(Identifier id, Supplier<StatusEffect> supplier) {
        return register(Registry.MOB_EFFECT_KEY, id, supplier);
    }

    public static RegistrySupplier<Potion> registryPotion(Identifier id, Supplier<Potion> supplier) {
        return register(Registry.POTION_KEY, id, supplier);
    }

    public static RegistrySupplier<Enchantment> registryEnchantment(Identifier id, Supplier<Enchantment> supplier) {
        return register(Registry.ENCHANTMENT_KEY, id, supplier);
    }

    // 1.19.2にはItemGroupのレジストリが無い (ItemGroupは配列で管理される)
    public static RegistrySupplier<ItemGroup> registryItemGroup(Identifier id, Supplier<ItemGroup> supplier) {
        return new RegistrySupplier<>(supplier.get());
    }

    @SubscribeEvent
    @SuppressWarnings({"unchecked", "rawtypes"})
    public static void onRegister(RegisterEvent event) {
        RegistryKey<? extends Registry<?>> key = event.getRegistryKey();

        Map<Identifier, PendingEntry<?>> entries;
        synchronized (PENDING_REGISTRIES) {
            Map<Identifier, PendingEntry<?>> found = PENDING_REGISTRIES.get(key);
            entries = found == null ? null : new LinkedHashMap<>(found);
        }
        if (entries == null) return;

        for (Map.Entry<Identifier, PendingEntry<?>> mapEntry : entries.entrySet()) {
            PendingEntry<?> pending = mapEntry.getValue();
            event.register((RegistryKey) key, mapEntry.getKey(), () -> {
                Object instance = pending.factory.get();
                ((RegistrySupplier<Object>) pending.wrapper).set(instance);

                return instance;
            });
        }
    }
}
