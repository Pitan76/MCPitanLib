package net.pitan76.mcpitanlib.core.registry.fabric;

import net.minecraft.core.Registry;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
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
import net.pitan76.mcpitanlib.api.registry.result.RegistrySupplier;

import java.util.function.Supplier;

public class RegistryImpl {
    public static RegistrySupplier<Item> registryItem(Identifier id, Supplier<Item> supplier) {
        return new RegistrySupplier<>(Registry.register(BuiltInRegistries.ITEM, ResourceKey.create(Registries.ITEM, id), supplier.get()));
    }

    public static RegistrySupplier<Block> registryBlock(Identifier id, Supplier<Block> supplier) {
        return new RegistrySupplier<>(Registry.register(BuiltInRegistries.BLOCK, ResourceKey.create(Registries.BLOCK, id), supplier.get()));
    }

    public static RegistrySupplier<MenuType<?>> registryScreenHandlerType(Identifier id, Supplier<MenuType<?>> supplier) {
        return new RegistrySupplier<>(Registry.register(BuiltInRegistries.MENU, ResourceKey.create(Registries.MENU, id), supplier.get()));
    }

    public static RegistrySupplier<BlockEntityType<?>> registryBlockEntityType(Identifier id, Supplier<BlockEntityType<?>> supplier) {
        return new RegistrySupplier<>(Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, ResourceKey.create(Registries.BLOCK_ENTITY_TYPE, id), supplier.get()));
    }

    public static RegistrySupplier<EntityType<?>> registryEntityType(Identifier id, Supplier<EntityType<?>> supplier) {
        return new RegistrySupplier<>(Registry.register(BuiltInRegistries.ENTITY_TYPE, ResourceKey.create(Registries.ENTITY_TYPE, id), supplier.get()));
    }

    public static RegistrySupplier<SoundEvent> registrySoundEvent(Identifier id, Supplier<SoundEvent> supplier) {
        return new RegistrySupplier<>(Registry.register(BuiltInRegistries.SOUND_EVENT, ResourceKey.create(Registries.SOUND_EVENT, id), supplier.get()));
    }

    public static RegistrySupplier<Fluid> registryFluid(Identifier id, Supplier<Fluid> supplier) {
        return new RegistrySupplier<>(Registry.register(BuiltInRegistries.FLUID, ResourceKey.create(Registries.FLUID, id), supplier.get()));
    }

    public static RegistrySupplier<ParticleType<?>> registryParticleType(Identifier id, Supplier<ParticleType<?>> supplier) {
        return new RegistrySupplier<>(Registry.register(BuiltInRegistries.PARTICLE_TYPE, ResourceKey.create(Registries.PARTICLE_TYPE, id), supplier.get()));
    }

    public static RegistrySupplier<MobEffect> registryStatusEffect(Identifier id, Supplier<MobEffect> supplier) {
        return new RegistrySupplier<>(Registry.register(BuiltInRegistries.MOB_EFFECT, ResourceKey.create(Registries.MOB_EFFECT, id), supplier.get()));
    }
}
