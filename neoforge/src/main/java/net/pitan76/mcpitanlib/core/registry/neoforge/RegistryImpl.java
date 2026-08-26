package net.pitan76.mcpitanlib.core.registry.neoforge;

import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.TicketType;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.registries.RegisterEvent;
import net.pitan76.mcpitanlib.api.registry.result.RegistrySupplier;
import net.pitan76.mcpitanlib.midohra.world.chunk.ChunkTicketType;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;
import java.util.Collections;
import java.util.LinkedHashMap;

@EventBusSubscriber(modid = "mcpitanlib")
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
    private static final Map<ResourceKey<? extends Registry<?>>, Map<Identifier, PendingEntry<?>>> PENDING_REGISTRIES = Collections.synchronizedMap(new LinkedHashMap<>());

    private static <T> RegistrySupplier<T> register(ResourceKey<@NotNull Registry<T>> registryKey, Identifier id, Supplier<T> supplier) {
        RegistrySupplier<T> registrySupplier = new RegistrySupplier<>();

        synchronized (PENDING_REGISTRIES) {
            PENDING_REGISTRIES.computeIfAbsent(registryKey, k -> new LinkedHashMap<>())
                    .put(id, new PendingEntry<>(supplier, registrySupplier));
        }

        return registrySupplier;
    }

    public static RegistrySupplier<Item> registryItem(Identifier id, Supplier<Item> supplier) {
        return register(Registries.ITEM, id, supplier);
    }

    public static RegistrySupplier<Block> registryBlock(Identifier id, Supplier<Block> supplier) {
        return register(Registries.BLOCK, id, supplier);
    }

    public static RegistrySupplier<MenuType<?>> registryScreenHandlerType(Identifier id, Supplier<MenuType<?>> supplier) {
        return register(Registries.MENU, id, supplier);
    }

    public static RegistrySupplier<BlockEntityType<?>> registryBlockEntityType(Identifier id, Supplier<BlockEntityType<?>> supplier) {
        return register(Registries.BLOCK_ENTITY_TYPE, id, supplier);
    }

    public static RegistrySupplier<EntityType<?>> registryEntityType(Identifier id, Supplier<EntityType<?>> supplier) {
        return register(Registries.ENTITY_TYPE, id, supplier);
    }

    public static RegistrySupplier<SoundEvent> registrySoundEvent(Identifier id, Supplier<SoundEvent> supplier) {
        return register(Registries.SOUND_EVENT, id, supplier);
    }

    public static RegistrySupplier<Fluid> registryFluid(Identifier id, Supplier<Fluid> supplier) {
        return register(Registries.FLUID, id, supplier);
    }

    public static RegistrySupplier<ParticleType<?>> registryParticleType(Identifier id, Supplier<ParticleType<?>> supplier) {
        return register(Registries.PARTICLE_TYPE, id, supplier);
    }

    public static RegistrySupplier<MobEffect> registryStatusEffect(Identifier id, Supplier<MobEffect> supplier) {
        return register(Registries.MOB_EFFECT, id, supplier);
    }

    public static RegistrySupplier<Potion> registryPotion(Identifier id, Supplier<Potion> supplier) {
        return register(Registries.POTION, id, supplier);
    }

    public static RegistrySupplier<CreativeModeTab> registryItemGroup(Identifier id, Supplier<CreativeModeTab> supplier) {
        return register(Registries.CREATIVE_MODE_TAB, id, supplier);
    }

    public static RegistrySupplier<DataComponentType<?>> registryDataComponentType(Identifier id, Supplier<DataComponentType<?>> supplier) {
        return register(Registries.DATA_COMPONENT_TYPE, id, supplier);
    }

    public static Supplier<ChunkTicketType<?>> registryChunkTicketType(Identifier id, Supplier<TicketType> supplier) {
        RegistrySupplier<TicketType> supplier1 = register(Registries.TICKET_TYPE, id, supplier);
        return () -> ChunkTicketType.of(supplier1.get());
    }

    @SubscribeEvent
    @SuppressWarnings({"unchecked", "rawtypes"})
    public static void onRegister(RegisterEvent event) {
        ResourceKey<? extends Registry<?>> key = event.getRegistryKey();

        Map<Identifier, PendingEntry<?>> entries;
        synchronized (PENDING_REGISTRIES) {
            entries = PENDING_REGISTRIES.get(key);
        }

        if (entries != null) {
            for (Map.Entry<Identifier, PendingEntry<?>> mapEntry : new LinkedHashMap<>(entries).entrySet()) {
                PendingEntry<?> pending = mapEntry.getValue();
                event.register((ResourceKey) key, mapEntry.getKey(), () -> {
                    Object instance = pending.factory.get();
                    ((RegistrySupplier<Object>) pending.wrapper).set(instance);

                    return instance;
                });
            }
        }
    }
}