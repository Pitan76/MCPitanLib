package net.pitan76.mcpitanlib.core.registry.forge;

import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.particle.ParticleType;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.registries.IForgeRegistryEntry;
import net.pitan76.mcpitanlib.MCPitanLib;
import net.pitan76.mcpitanlib.api.registry.result.RegistrySupplier;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Supplier;

/**
 * Forgeはバニラのレジストリを凍結するため、mod構築時に直接registerすると
 * "Registry is already frozen" で落ちる。RegistryEvent.Registerまで登録を遅延させる。
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

    private static final Map<RegistryKey<? extends Registry<?>>, Map<Identifier, PendingEntry<?>>> PENDING_REGISTRIES = Collections.synchronizedMap(new LinkedHashMap<RegistryKey<? extends Registry<?>>, Map<Identifier, PendingEntry<?>>>());

    private static <T> RegistrySupplier<T> register(RegistryKey<Registry<T>> registryKey, Identifier id, Supplier<T> supplier) {
        RegistrySupplier<T> registrySupplier = new RegistrySupplier<T>();

        synchronized (PENDING_REGISTRIES) {
            Map<Identifier, PendingEntry<?>> entries = PENDING_REGISTRIES.get(registryKey);
            if (entries == null) {
                entries = new LinkedHashMap<Identifier, PendingEntry<?>>();
                PENDING_REGISTRIES.put(registryKey, entries);
            }

            entries.put(id, new PendingEntry<T>(supplier, registrySupplier));
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

    public static RegistrySupplier<Enchantment> registryEnchantment(Identifier id, Supplier<Enchantment> supplier) {
        return register(Registry.ENCHANTMENT_KEY, id, supplier);
    }

    public static RegistrySupplier<StatusEffect> registryStatusEffect(Identifier id, Supplier<StatusEffect> supplier) {
        return register(Registry.MOB_EFFECT_KEY, id, supplier);
    }

    // 1.16.5にはItemGroupのレジストリが無い (ItemGroupは配列で管理される)
    public static RegistrySupplier<ItemGroup> registryItemGroup(Identifier id, Supplier<ItemGroup> supplier) {
        return new RegistrySupplier<ItemGroup>(supplier.get());
    }

    @SubscribeEvent
    public static void onRegisterItem(RegistryEvent.Register<Item> event) {
        flush(Registry.ITEM_KEY, event);
    }

    @SubscribeEvent
    public static void onRegisterBlock(RegistryEvent.Register<Block> event) {
        flush(Registry.BLOCK_KEY, event);
    }

    @SubscribeEvent
    public static void onRegisterScreenHandlerType(RegistryEvent.Register<ScreenHandlerType<?>> event) {
        flush(Registry.MENU_KEY, event);
    }

    @SubscribeEvent
    public static void onRegisterBlockEntityType(RegistryEvent.Register<BlockEntityType<?>> event) {
        flush(Registry.BLOCK_ENTITY_TYPE_KEY, event);
    }

    @SubscribeEvent
    public static void onRegisterEntityType(RegistryEvent.Register<EntityType<?>> event) {
        flush(Registry.ENTITY_TYPE_KEY, event);
    }

    @SubscribeEvent
    public static void onRegisterSoundEvent(RegistryEvent.Register<SoundEvent> event) {
        flush(Registry.SOUND_EVENT_KEY, event);
    }

    @SubscribeEvent
    public static void onRegisterFluid(RegistryEvent.Register<Fluid> event) {
        flush(Registry.FLUID_KEY, event);
    }

    @SubscribeEvent
    public static void onRegisterParticleType(RegistryEvent.Register<ParticleType<?>> event) {
        flush(Registry.PARTICLE_TYPE_KEY, event);
    }

    @SubscribeEvent
    public static void onRegisterEnchantment(RegistryEvent.Register<Enchantment> event) {
        flush(Registry.ENCHANTMENT_KEY, event);
    }

    @SubscribeEvent
    public static void onRegisterStatusEffect(RegistryEvent.Register<StatusEffect> event) {
        flush(Registry.MOB_EFFECT_KEY, event);
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    private static <T extends IForgeRegistryEntry<T>> void flush(RegistryKey<? extends Registry<?>> key, RegistryEvent.Register<T> event) {
        Map<Identifier, PendingEntry<?>> entries;
        synchronized (PENDING_REGISTRIES) {
            Map<Identifier, PendingEntry<?>> found = PENDING_REGISTRIES.get(key);
            entries = found == null ? null : new LinkedHashMap<Identifier, PendingEntry<?>>(found);
        }
        if (entries == null) return;

        for (Map.Entry<Identifier, PendingEntry<?>> mapEntry : entries.entrySet()) {
            PendingEntry<?> pending = mapEntry.getValue();

            Object instance = pending.factory.get();
            ((IForgeRegistryEntry) instance).setRegistryName(mapEntry.getKey());
            event.getRegistry().register((T) instance);

            ((RegistrySupplier<Object>) pending.wrapper).set(instance);
        }
    }
}
