package net.pitan76.mcpitanlib.core.registry;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.item.Item;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.resources.Identifier;
import net.pitan76.mcpitanlib.api.registry.result.RegistrySupplier;

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

    public RegistrySupplier<MenuType<?>> registryScreenHandlerType(Identifier id, Supplier<MenuType<?>> supplier) {
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
        return null;
        //return ENCHANTMENT.register(id, supplier);
    }

    public RegistrySupplier<MobEffect> registryStatusEffect(Identifier id, Supplier<MobEffect> supplier) {
        return Registry.registryStatusEffect(id, supplier);
    }

    public RegistrySupplier<Potion> registryPotion(Identifier id, Supplier<Potion> supplier) {
        return Registry.registryPotion(id, supplier);
    }

    public void allRegister1_16() {

    }
}
