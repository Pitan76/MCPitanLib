package net.pitan76.mcpitanlib.core.registry;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.component.ComponentType;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.particle.ParticleType;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.server.world.ChunkTicketType;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
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
    public static RegistrySupplier<ScreenHandlerType<?>> registryScreenHandlerType(Identifier id, Supplier<ScreenHandlerType<?>> supplier) {
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
    public static RegistrySupplier<StatusEffect> registryStatusEffect(Identifier id, Supplier<StatusEffect> supplier) {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static RegistrySupplier<ItemGroup> registryItemGroup(Identifier id, Supplier<ItemGroup> supplier) {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static RegistrySupplier<ComponentType<?>> registryDataComponentType(Identifier id, Supplier<ComponentType<?>> supplier) {
        throw new AssertionError();
    }
}
