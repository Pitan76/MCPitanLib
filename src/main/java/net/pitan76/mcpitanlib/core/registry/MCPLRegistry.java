package net.pitan76.mcpitanlib.core.registry;

import com.google.common.base.Suppliers;
import dev.architectury.registry.registries.Registrar;
import dev.architectury.registry.registries.RegistrarManager;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.item.Item;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.resources.Identifier;

import java.util.function.Supplier;

@Deprecated
public class MCPLRegistry {

    public Supplier<RegistrarManager> REGISTRIES;

    public Registrar<Item> ITEMS;
    public Registrar<Block> BLOCKS;
    public Registrar<MenuType<?>> SCREEN_HANDLER_TYPE;
    public Registrar<BlockEntityType<?>> BLOCK_ENTITY_TYPE;
    public Registrar<EntityType<?>> ENTITY_TYPE;
    public Registrar<SoundEvent> SOUND_EVENT;
    public Registrar<Fluid> FLUID;
    public Registrar<ParticleType<?>> PARTICLE_TYPE;
    public Registrar<MobEffect> STATUS_EFFECT;

    public MCPLRegistry(String MOD_ID) {
        REGISTRIES = Suppliers.memoize(() -> RegistrarManager.get(MOD_ID));

        ITEMS = REGISTRIES.get().get(Registries.ITEM);
        BLOCKS = REGISTRIES.get().get(Registries.BLOCK);
        SCREEN_HANDLER_TYPE = REGISTRIES.get().get(Registries.MENU);
        BLOCK_ENTITY_TYPE = REGISTRIES.get().get(Registries.BLOCK_ENTITY_TYPE);
        ENTITY_TYPE = REGISTRIES.get().get(Registries.ENTITY_TYPE);
        SOUND_EVENT = REGISTRIES.get().get(Registries.SOUND_EVENT);
        FLUID = REGISTRIES.get().get(Registries.FLUID);
        PARTICLE_TYPE = REGISTRIES.get().get(Registries.PARTICLE_TYPE);
        STATUS_EFFECT = REGISTRIES.get().get(Registries.MOB_EFFECT);
    }

    public RegistrySupplier<Item> registryItem(Identifier id, Supplier<Item> supplier) {
        return ITEMS.register(id, supplier);
    }

    public RegistrySupplier<Block> registryBlock(Identifier id, Supplier<Block> supplier) {
        return BLOCKS.register(id, supplier);
    }

    public RegistrySupplier<MenuType<?>> registryScreenHandlerType(Identifier id, Supplier<MenuType<?>> supplier) {
        return SCREEN_HANDLER_TYPE.register(id, supplier);
    }

    public RegistrySupplier<BlockEntityType<?>> registryBlockEntityType(Identifier id, Supplier<BlockEntityType<?>> supplier) {
        return BLOCK_ENTITY_TYPE.register(id, supplier);
    }

    public RegistrySupplier<EntityType<?>> registryEntityType(Identifier id, Supplier<EntityType<?>> supplier) {
        return ENTITY_TYPE.register(id, supplier);
    }

    public RegistrySupplier<SoundEvent> registrySoundEvent(Identifier id, Supplier<SoundEvent> supplier) {
        return SOUND_EVENT.register(id, supplier);
    }

    public RegistrySupplier<Fluid> registryFluid(Identifier id, Supplier<Fluid> supplier) {
        return FLUID.register(id, supplier);
    }

    public RegistrySupplier<ParticleType<?>> registryParticleType(Identifier id, Supplier<ParticleType<?>> supplier) {
        return PARTICLE_TYPE.register(id, supplier);
    }

    public RegistrySupplier<Enchantment> registryEnchantment(Identifier id, Supplier<Enchantment> supplier) {
        return null;
        //return ENCHANTMENT.register(id, supplier);
    }

    public RegistrySupplier<MobEffect> registryStatusEffect(Identifier id, Supplier<MobEffect> supplier) {
        return STATUS_EFFECT.register(id, supplier);
    }

    public void allRegister1_16() {

    }
}
