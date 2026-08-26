package net.pitan76.mcpitanlib.core.registry;

import net.pitan76.mcpitanlib.api.registry.result.RegistrySupplier;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.potion.Potion;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.Item;
import net.minecraft.particle.ParticleType;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

import java.util.function.Supplier;

@Deprecated
public class MCPLRegistry {


    public MCPLRegistry(String MOD_ID) {
    }

    public RegistrySupplier<Item> registryItem(Identifier id, Supplier<Item> supplier) {
        return Registry.registryItem(id, supplier);
    }

    public RegistrySupplier<Block> registryBlock(Identifier id, Supplier<Block> supplier) {
        return Registry.registryBlock(id, supplier);
    }

    public RegistrySupplier<ScreenHandlerType<?>> registryScreenHandlerType(Identifier id, Supplier<ScreenHandlerType<?>> supplier) {
        return Registry.registryScreenHandlerType(id, supplier);
    }

    public RegistrySupplier<BlockEntityType<?>> registryBlockEntityType(Identifier id, Supplier<BlockEntityType<?>> supplier) {
        return Registry.registryBlockEntityType(id, supplier);
    }

    public RegistrySupplier<EntityType<?>> registryEntityType(Identifier id, Supplier<EntityType<?>> supplier) {
        return Registry.registryEntityType(id, supplier);
    }

    public RegistrySupplier<SoundEvent> registrySoundEvent(Identifier id, Supplier<SoundEvent> supplier) {
        return Registry.registrySoundEvent(id, supplier);
    }

    public RegistrySupplier<Fluid> registryFluid(Identifier id, Supplier<Fluid> supplier) {
        return Registry.registryFluid(id, supplier);
    }

    public RegistrySupplier<ParticleType<?>> registryParticleType(Identifier id, Supplier<ParticleType<?>> supplier) {
        return Registry.registryParticleType(id, supplier);
    }

    public RegistrySupplier<Enchantment> registryEnchantment(Identifier id, Supplier<Enchantment> supplier) {
        return Registry.registryEnchantment(id, supplier);
    }

    public RegistrySupplier<StatusEffect> registryStatusEffect(Identifier id, Supplier<StatusEffect> supplier) {
        return Registry.registryStatusEffect(id, supplier);
    }

    public RegistrySupplier<Potion> registryPotion(Identifier id, Supplier<Potion> supplier) {
        return Registry.registryPotion(id, supplier);
    }

    public void allRegister1_16() {

    }
}
