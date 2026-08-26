package net.pitan76.mcpitanlib.core.registry.fabric;

import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.EntityType;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.potion.Potion;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.particle.ParticleType;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.pitan76.mcpitanlib.api.registry.result.RegistrySupplier;

import java.util.function.Supplier;

public class RegistryImpl {
    public static RegistrySupplier<Item> registryItem(Identifier id, Supplier<Item> supplier) {
        return new RegistrySupplier<Item>(Registry.register(Registry.ITEM, id, supplier.get()));
    }

    public static RegistrySupplier<Block> registryBlock(Identifier id, Supplier<Block> supplier) {
        return new RegistrySupplier<Block>(Registry.register(Registry.BLOCK, id, supplier.get()));
    }

    public static RegistrySupplier<ScreenHandlerType<?>> registryScreenHandlerType(Identifier id, Supplier<ScreenHandlerType<?>> supplier) {
        return new RegistrySupplier<ScreenHandlerType<?>>(Registry.register(Registry.SCREEN_HANDLER, id, supplier.get()));
    }

    public static RegistrySupplier<BlockEntityType<?>> registryBlockEntityType(Identifier id, Supplier<BlockEntityType<?>> supplier) {
        return new RegistrySupplier<BlockEntityType<?>>(Registry.register(Registry.BLOCK_ENTITY_TYPE, id, supplier.get()));
    }

    public static RegistrySupplier<EntityType<?>> registryEntityType(Identifier id, Supplier<EntityType<?>> supplier) {
        return new RegistrySupplier<EntityType<?>>(Registry.register(Registry.ENTITY_TYPE, id, supplier.get()));
    }

    public static RegistrySupplier<SoundEvent> registrySoundEvent(Identifier id, Supplier<SoundEvent> supplier) {
        return new RegistrySupplier<SoundEvent>(Registry.register(Registry.SOUND_EVENT, id, supplier.get()));
    }

    public static RegistrySupplier<Fluid> registryFluid(Identifier id, Supplier<Fluid> supplier) {
        return new RegistrySupplier<Fluid>(Registry.register(Registry.FLUID, id, supplier.get()));
    }

    public static RegistrySupplier<ParticleType<?>> registryParticleType(Identifier id, Supplier<ParticleType<?>> supplier) {
        return new RegistrySupplier<ParticleType<?>>(Registry.register(Registry.PARTICLE_TYPE, id, supplier.get()));
    }

    public static RegistrySupplier<Enchantment> registryEnchantment(Identifier id, Supplier<Enchantment> supplier) {
        return new RegistrySupplier<Enchantment>(Registry.register(Registry.ENCHANTMENT, id, supplier.get()));
    }

    public static RegistrySupplier<StatusEffect> registryStatusEffect(Identifier id, Supplier<StatusEffect> supplier) {
        return new RegistrySupplier<StatusEffect>(Registry.register(Registry.STATUS_EFFECT, id, supplier.get()));
    }

    public static RegistrySupplier<Potion> registryPotion(Identifier id, Supplier<Potion> supplier) {
        return new RegistrySupplier<>(Registry.register(Registry.POTION, id, supplier.get()));
    }


    // 1.16.5にはItemGroupのレジストリが無い (ItemGroupは配列で管理される)
    public static RegistrySupplier<ItemGroup> registryItemGroup(Identifier id, Supplier<ItemGroup> supplier) {
        return new RegistrySupplier<ItemGroup>(supplier.get());
    }
}
