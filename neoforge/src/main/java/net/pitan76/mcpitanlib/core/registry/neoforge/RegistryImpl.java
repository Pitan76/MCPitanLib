package net.pitan76.mcpitanlib.core.registry.neoforge;

import net.minecraft.core.Registry;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.registries.RegisterEvent;
import net.pitan76.mcpitanlib.api.registry.result.RegistrySupplier;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

@EventBusSubscriber(modid = "mcpitanlib")
public class RegistryImpl {

    private static final Map<ResourceKey<? extends Registry<?>>, Map<Identifier, Object>> PENDING_REGISTRIES = new HashMap<>();

    private static <T> RegistrySupplier<T> register(ResourceKey<@NotNull Registry<T>> registryKey, Identifier id, Supplier<T> supplier) {
        T instance = supplier.get();

        PENDING_REGISTRIES.computeIfAbsent(registryKey, _ -> new HashMap<>())
                .put(id, instance);

        return new RegistrySupplier<>(instance);
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

    @SubscribeEvent
    @SuppressWarnings({"unchecked", "rawtypes"})
    public static void onRegister(RegisterEvent event) {
        ResourceKey<? extends Registry<?>> key = event.getRegistryKey();

        if (PENDING_REGISTRIES.containsKey(key)) {
            Map<Identifier, Object> entries = PENDING_REGISTRIES.get(key);

            for (Map.Entry<Identifier, Object> entry : entries.entrySet()) {
                event.register((ResourceKey) key, entry.getKey(), () -> entry.getValue());
            }
        }
    }
}