package net.pitan76.mcpitanlib.api.registry.v2;

import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.particle.ParticleType;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.sound.SoundEvent;
import net.pitan76.mcpitanlib.api.block.ExtendBlock;
import net.pitan76.mcpitanlib.api.enchantment.CompatEnchantment;
import net.pitan76.mcpitanlib.api.entity.effect.CompatStatusEffect;
import net.pitan76.mcpitanlib.api.item.CreativeTabBuilder;
import net.pitan76.mcpitanlib.api.item.ExtendItem;
import net.pitan76.mcpitanlib.api.registry.CompatRegistry;
import net.pitan76.mcpitanlib.api.registry.FuelRegistry;
import net.pitan76.mcpitanlib.api.registry.result.RegistryResult;
import net.pitan76.mcpitanlib.api.sound.CompatSoundEvent;
import net.pitan76.mcpitanlib.api.sound.RegistryResultCompatSoundEvent;
import net.pitan76.mcpitanlib.api.util.CompatIdentifier;

import java.util.function.Supplier;

public class CompatRegistryV2 {

    public final CompatRegistry cr1;

    @Deprecated
    public CompatRegistryV2(CompatRegistry compatRegistry) {
        this.cr1 = compatRegistry;
    }

    /**
     * Create a new CompatRegistryV2
     * @param MOD_ID The mod id
     * @return The new CompatRegistryV2
     */
    public static CompatRegistryV2 create(String MOD_ID) {
        CompatRegistry cr1 = CompatRegistry.create(MOD_ID);
        return new CompatRegistryV2(cr1);
    }

    public static CompatRegistryV2 create(CompatRegistry compatRegistry) {
        return new CompatRegistryV2(compatRegistry);
    }

    /**
     * Register an item
     * @param id The item id
     * @param supplier The item supplier
     * @return The registry result
     */
    public RegistryResult<Item> registerItem(CompatIdentifier id, Supplier<Item> supplier) {
        return cr1.registerItem(id.toMinecraft(), supplier);
    }

    public Supplier<ExtendItem> registerExtendItem(CompatIdentifier id, Supplier<ExtendItem> supplier) {
        RegistryResult<Item> result = registerItem(id, supplier::get);
        return () -> (ExtendItem) result.get();
    }

    /**
     * Register a block
     * @param id The block id
     * @param supplier The block supplier
     * @return The registry result
     */
    public RegistryResult<Block> registerBlock(CompatIdentifier id, Supplier<Block> supplier) {
        return cr1.registerBlock(id.toMinecraft(), supplier);
    }

    public Supplier<ExtendBlock> registerExtendBlock(CompatIdentifier id, Supplier<ExtendBlock> supplier) {
        RegistryResult<Block> result = registerBlock(id, supplier::get);
        return () -> (ExtendBlock) result.get();
    }

    public RegistryResult<ScreenHandlerType<?>> registerScreenHandlerType(CompatIdentifier id, Supplier<ScreenHandlerType<?>> supplier) {
        return cr1.registerScreenHandlerType(id.toMinecraft(), supplier);
    }

    public RegistryResult<BlockEntityType<?>> registerBlockEntityType(CompatIdentifier id, Supplier<BlockEntityType<?>> supplier) {
        return cr1.registerBlockEntityType(id.toMinecraft(), supplier);
    }

    public RegistryResult<EntityType<?>> registerEntity(CompatIdentifier id, Supplier<EntityType<?>> supplier) {
        return cr1.registerEntity(id.toMinecraft(), supplier);
    }

    public RegistryResult<SoundEvent> registerSoundEvent(CompatIdentifier id) {
        return cr1.registerSoundEvent(id.toMinecraft());
    }

    public RegistryResult<SoundEvent> registerSoundEvent(CompatIdentifier id, float distanceToTravel) {
        return cr1.registerSoundEvent(id.toMinecraft(), distanceToTravel);
    }

    public CompatSoundEvent registerCompatSoundEvent(CompatIdentifier id) {
        return RegistryResultCompatSoundEvent.of(registerSoundEvent(id));
    }

    public CompatSoundEvent registerCompatSoundEvent(CompatIdentifier id, float distanceToTravel) {
        return RegistryResultCompatSoundEvent.of(registerSoundEvent(id, distanceToTravel));
    }

    public RegistryResult<Fluid> registerFluid(CompatIdentifier id, Supplier<Fluid> supplier) {
        return cr1.registerFluid(id.toMinecraft(), supplier);
    }

    public RegistryResult<ParticleType<?>> registerParticleType(CompatIdentifier id, Supplier<ParticleType<?>> supplier) {
        return cr1.registerParticleType(id.toMinecraft(), supplier);
    }

    public RegistryResult<Enchantment> registerEnchantment(CompatIdentifier id, Supplier<CompatEnchantment> supplier) {
        return cr1.registerEnchantment(id.toMinecraft(), () -> supplier.get().getEnchantment(null));
    }

    public RegistryResult<StatusEffect> registryStatusEffect(CompatIdentifier id, Supplier<CompatStatusEffect> supplier) {
        return cr1.registerStatusEffect(id.toMinecraft(), () -> supplier.get().getStatusEffect(null));
    }

    public RegistryResult<ItemGroup> registerItemGroup(CompatIdentifier id, Supplier<ItemGroup> supplier) {
        return cr1.registerItemGroup(id.toMinecraft(), supplier);
    }

    public RegistryResult<ItemGroup> registerItemGroup(CompatIdentifier id, CreativeTabBuilder builder) {
        return cr1.registerItemGroup(id.toMinecraft(), builder);
    }

    public RegistryResult<ItemGroup> registerItemGroup(CreativeTabBuilder builder) {
        return cr1.registerItemGroup(builder.getIdentifier(), builder);
    }

    public void registerFuel(Supplier<Item> itemSupplier, int time) {
        FuelRegistry.register(itemSupplier::get, time, cr1.getNamespace());
    }

    public void allRegister() {
        cr1.allRegister();
    }
}
