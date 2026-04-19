package net.pitan76.mcpitanlib.core.registry;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.resources.Identifier;
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

public class Registry {

    @ExpectPlatform
    public static RegistrySupplier<Item> registryItem(Identifier id, Supplier<Item> supplier) {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static RegistrySupplier<Block> registryBlock(Identifier id, Supplier<Block> supplier) {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static RegistrySupplier<MenuType<?>> registryScreenHandlerType(Identifier id, Supplier<MenuType<?>> supplier) {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static RegistrySupplier<BlockEntityType<?>> registryBlockEntityType(Identifier id, Supplier<BlockEntityType<?>> supplier) {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static RegistrySupplier<EntityType<?>> registryEntityType(Identifier id, Supplier<EntityType<?>> supplier) {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static RegistrySupplier<SoundEvent> registrySoundEvent(Identifier id, Supplier<SoundEvent> supplier) {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static RegistrySupplier<Fluid> registryFluid(Identifier id, Supplier<Fluid> supplier) {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static RegistrySupplier<ParticleType<?>> registryParticleType(Identifier id, Supplier<ParticleType<?>> supplier) {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static RegistrySupplier<MobEffect> registryStatusEffect(Identifier id, Supplier<MobEffect> supplier) {
        throw new AssertionError();
    }
}
