package net.pitan76.mcpitanlib.core.registry.fabric;

import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.component.ComponentType;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.potion.Potion;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.particle.ParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.server.world.ChunkTicketType;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.pitan76.mcpitanlib.api.registry.result.RegistrySupplier;

import java.util.function.Supplier;

public class RegistryImpl {
    public static RegistrySupplier<Item> registryItem(Identifier id, Supplier<Item> supplier) {
        return new RegistrySupplier<>(Registry.register(Registries.ITEM, id, supplier.get()));
    }

    public static RegistrySupplier<Block> registryBlock(Identifier id, Supplier<Block> supplier) {
        return new RegistrySupplier<>(Registry.register(Registries.BLOCK, id, supplier.get()));
    }

    public static RegistrySupplier<ScreenHandlerType<?>> registryScreenHandlerType(Identifier id, Supplier<ScreenHandlerType<?>> supplier) {
        return new RegistrySupplier<>(Registry.register(Registries.SCREEN_HANDLER, id, supplier.get()));
    }

    public static RegistrySupplier<BlockEntityType<?>> registryBlockEntityType(Identifier id, Supplier<BlockEntityType<?>> supplier) {
        return new RegistrySupplier<>(Registry.register(Registries.BLOCK_ENTITY_TYPE, id, supplier.get()));
    }

    public static RegistrySupplier<EntityType<?>> registryEntityType(Identifier id, Supplier<EntityType<?>> supplier) {
        return new RegistrySupplier<>(Registry.register(Registries.ENTITY_TYPE, id, supplier.get()));
    }

    public static RegistrySupplier<SoundEvent> registrySoundEvent(Identifier id, Supplier<SoundEvent> supplier) {
        return new RegistrySupplier<>(Registry.register(Registries.SOUND_EVENT, id, supplier.get()));
    }

    public static RegistrySupplier<Fluid> registryFluid(Identifier id, Supplier<Fluid> supplier) {
        return new RegistrySupplier<>(Registry.register(Registries.FLUID, id, supplier.get()));
    }

    public static RegistrySupplier<ParticleType<?>> registryParticleType(Identifier id, Supplier<ParticleType<?>> supplier) {
        return new RegistrySupplier<>(Registry.register(Registries.PARTICLE_TYPE, id, supplier.get()));
    }

    public static RegistrySupplier<StatusEffect> registryStatusEffect(Identifier id, Supplier<StatusEffect> supplier) {
        return new RegistrySupplier<>(Registry.register(Registries.STATUS_EFFECT, id, supplier.get()));
    }

    public static RegistrySupplier<Potion> registryPotion(Identifier id, Supplier<Potion> supplier) {
        return new RegistrySupplier<>(Registry.register(Registries.POTION, id, supplier.get()));
    }

    public static RegistrySupplier<com.mojang.serialization.MapCodec<? extends net.minecraft.enchantment.effect.EnchantmentEntityEffect>> registryEnchantmentEntityEffectType(
            Identifier id, Supplier<com.mojang.serialization.MapCodec<? extends net.minecraft.enchantment.effect.EnchantmentEntityEffect>> supplier) {
        return new RegistrySupplier<>(Registry.register(Registries.ENCHANTMENT_ENTITY_EFFECT_TYPE, id, supplier.get()));
    }

    public static RegistrySupplier<ItemGroup> registryItemGroup(Identifier id, Supplier<ItemGroup> supplier) {
        return new RegistrySupplier<>(Registry.register(Registries.ITEM_GROUP, id, supplier.get()));
    }

    public static RegistrySupplier<ComponentType<?>> registryDataComponentType(Identifier id, Supplier<ComponentType<?>> supplier) {
        return new RegistrySupplier<>(Registry.register(Registries.DATA_COMPONENT_TYPE, id, supplier.get()));
    }

    public static RegistrySupplier<ChunkTicketType> registryChunkTicketType(Identifier id, Supplier<ChunkTicketType> supplier) {
        return new RegistrySupplier<>(Registry.register(Registries.TICKET_TYPE, id, supplier.get()));
    }
}
