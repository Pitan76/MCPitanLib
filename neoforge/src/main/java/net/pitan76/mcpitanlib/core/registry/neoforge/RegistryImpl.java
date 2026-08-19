package net.pitan76.mcpitanlib.core.registry.neoforge;

import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.component.ComponentType;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.particle.ParticleType;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.server.world.ChunkTicketType;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.registries.RegisterEvent;
import net.pitan76.mcpitanlib.MCPitanLib;
import net.pitan76.mcpitanlib.api.registry.result.RegistrySupplier;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Supplier;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Collections;

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

    // NeoForgeはmodを並列に構築するため、スレッドセーフにする必要がある
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
        return register(RegistryKeys.ITEM, id, supplier);
    }

    public static RegistrySupplier<Block> registryBlock(Identifier id, Supplier<Block> supplier) {
        return register(RegistryKeys.BLOCK, id, supplier);
    }

    public static RegistrySupplier<ScreenHandlerType<?>> registryScreenHandlerType(Identifier id, Supplier<ScreenHandlerType<?>> supplier) {
        return register(RegistryKeys.SCREEN_HANDLER, id, supplier);
    }

    public static RegistrySupplier<BlockEntityType<?>> registryBlockEntityType(Identifier id, Supplier<BlockEntityType<?>> supplier) {
        return register(RegistryKeys.BLOCK_ENTITY_TYPE, id, supplier);
    }

    public static RegistrySupplier<EntityType<?>> registryEntityType(Identifier id, Supplier<EntityType<?>> supplier) {
        return register(RegistryKeys.ENTITY_TYPE, id, supplier);
    }

    public static RegistrySupplier<SoundEvent> registrySoundEvent(Identifier id, Supplier<SoundEvent> supplier) {
        return register(RegistryKeys.SOUND_EVENT, id, supplier);
    }

    public static RegistrySupplier<Fluid> registryFluid(Identifier id, Supplier<Fluid> supplier) {
        return register(RegistryKeys.FLUID, id, supplier);
    }

    public static RegistrySupplier<ParticleType<?>> registryParticleType(Identifier id, Supplier<ParticleType<?>> supplier) {
        return register(RegistryKeys.PARTICLE_TYPE, id, supplier);
    }

    public static RegistrySupplier<StatusEffect> registryStatusEffect(Identifier id, Supplier<StatusEffect> supplier) {
        return register(RegistryKeys.STATUS_EFFECT, id, supplier);
    }

    public static RegistrySupplier<ItemGroup> registryItemGroup(Identifier id, Supplier<ItemGroup> supplier) {
        return register(RegistryKeys.ITEM_GROUP, id, supplier);
    }

    public static RegistrySupplier<ComponentType<?>> registryDataComponentType(Identifier id, Supplier<ComponentType<?>> supplier) {
        return register(RegistryKeys.DATA_COMPONENT_TYPE, id, supplier);
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
