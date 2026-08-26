package net.pitan76.mcpitanlib.api.registry;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.resources.Identifier;
import net.pitan76.mcpitanlib.MCPitanLib;
import net.pitan76.mcpitanlib.api.block.CompatibleBlockSettings;
import net.pitan76.mcpitanlib.api.block.CompatibleMaterial;
import net.pitan76.mcpitanlib.api.gui.ExtendedScreenHandlerTypeBuilder;
import net.pitan76.mcpitanlib.api.item.CreativeTabBuilder;
import net.pitan76.mcpitanlib.api.item.CreativeTabManager;
import net.pitan76.mcpitanlib.api.item.v2.CompatibleItemSettings;
import net.pitan76.mcpitanlib.api.registry.result.RegistryResult;
import net.pitan76.mcpitanlib.api.registry.result.RegistrySupplier;
import net.pitan76.mcpitanlib.api.util.CompatIdentifier;
import net.pitan76.mcpitanlib.api.util.block.BlockUtil;
import net.pitan76.mcpitanlib.api.util.item.ItemUtil;
import net.pitan76.mcpitanlib.core.registry.FuelRegistry;
import net.pitan76.mcpitanlib.core.registry.MCPLRegistry;
import net.pitan76.mcpitanlib.core.registry.MCPLRegistry1_20;
import net.pitan76.mcpitanlib.core.registry.MCPLRegistry1_21;

import java.util.function.Supplier;

public class CompatRegistry {

    protected final MCPLRegistry mcplr;
    protected final MCPLRegistry1_20 mcplr1_20;
    protected final MCPLRegistry1_21 mcplr1_21;
    protected final WorldGenRegistry worldGenRegistry;

    protected String MOD_ID;

    /**
     * @deprecated Use {@link #createRegistry(String)} instead
     */
    @Deprecated
    public CompatRegistry(String MOD_ID) {
        mcplr = new MCPLRegistry(MOD_ID);
        mcplr1_20 = new MCPLRegistry1_20(mcplr, MOD_ID);
        mcplr1_21 = new MCPLRegistry1_21(mcplr, MOD_ID);
        worldGenRegistry = new WorldGenRegistry(MOD_ID);
        this.MOD_ID = MOD_ID;
    }

    /**
     * create(new CompatRegistry)'s alias
     * @see #create(String)
     */
    public static CompatRegistry createRegistry(String MOD_ID) {
        return new CompatRegistry(MOD_ID);
    }

    /**
     * Create a new CompatRegistry
     * @param MOD_ID The mod id
     * @return The new CompatRegistry
     */
    public static CompatRegistry create(String MOD_ID) {
        return createRegistry(MOD_ID);
    }

    /**
     * Register an item
     * @param id The item id
     * @param supplier The item supplier
     * @return The registry result
     */
    public RegistryResult<Item> registerItem(Identifier id, Supplier<Item> supplier) {
        if (MCPitanLib.isItemBlackListed(id)) supplier = () -> ItemUtil.create(CompatibleItemSettings.of(CompatIdentifier.fromMinecraft(id)));
        RegistrySupplier<Item> registrySupplier = mcplr.registryItem(id, supplier);
        CreativeTabManager.register(id);
        return new RegistryResult<>(registrySupplier);
    }

    public RegistryResult<Block> registerBlock(Identifier id, Supplier<Block> supplier) {
        if (MCPitanLib.isBlockBlackListed(id)) supplier = () -> BlockUtil.create(CompatibleBlockSettings.of(CompatibleMaterial.STONE));
        return new RegistryResult<>(mcplr.registryBlock(id, supplier));
    }

    public RegistryResult<MenuType<?>> registerScreenHandlerType(Identifier id, Supplier<MenuType<?>> supplier) {
        return new RegistryResult<>(mcplr.registryScreenHandlerType(id, supplier));
    }

    @Deprecated
    public RegistryResult<MenuType<?>> registerExtendedScreenHandlerType(Identifier id, Supplier<ExtendedScreenHandlerTypeBuilder<?>> supplier) {
        return registerScreenHandlerType(id, () -> supplier.get().build());
    }

    public RegistryResult<MenuType<?>> registerMenu(Identifier id, Supplier<MenuType<?>> supplier) {
        return registerScreenHandlerType(id, supplier);
    }

    public RegistryResult<BlockEntityType<?>> registerBlockEntityType(Identifier id, Supplier<BlockEntityType<?>> supplier) {
        return new RegistryResult<>(mcplr.registryBlockEntityType(id, supplier));
    }

    public RegistryResult<EntityType<?>> registerEntity(Identifier id, Supplier<EntityType<?>> supplier) {
        return new RegistryResult<>(mcplr.registryEntityType(id, supplier));
    }

    @Deprecated
    public RegistryResult<SoundEvent> registerSoundEvent(Identifier id, Supplier<SoundEvent> supplier) {
        return new RegistryResult<>(mcplr.registrySoundEvent(id, supplier));
    }

    public RegistryResult<SoundEvent> registerSoundEvent(Identifier id) {
        return registerSoundEvent(id, () -> SoundEvent.createVariableRangeEvent(id));
    }

    public RegistryResult<SoundEvent> registerSoundEvent(Identifier id, float distanceToTravel) {
        return registerSoundEvent(id, () -> SoundEvent.createFixedRangeEvent(id, distanceToTravel));
    }

    public RegistryResult<Fluid> registerFluid(Identifier id, Supplier<Fluid> supplier) {
        return new RegistryResult<>(mcplr.registryFluid(id, supplier));
    }

    public RegistryResult<ParticleType<?>> registerParticleType(Identifier id, Supplier<ParticleType<?>> supplier) {
        return new RegistryResult<>(mcplr.registryParticleType(id, supplier));
    }

    public RegistryResult<Enchantment> registerEnchantment(Identifier id, Supplier<Enchantment> supplier) {
        return new RegistryResult<>(mcplr.registryEnchantment(id, supplier));
    }

    public RegistryResult<MobEffect> registerStatusEffect(Identifier id, Supplier<MobEffect> supplier) {
        return new RegistryResult<>(mcplr.registryStatusEffect(id, supplier));
    }

    public RegistryResult<Potion> registerPotion(Identifier id, Supplier<Potion> supplier) {
        return new RegistryResult<>(mcplr.registryPotion(id, supplier));
    }

    public RegistryResult<CreativeModeTab> registerItemGroup(Identifier id, Supplier<CreativeModeTab> supplier) {
        return new RegistryResult<>(mcplr1_20.registryItemGroup(id, supplier));
    }

    public RegistryResult<CreativeModeTab> registerItemGroup(Identifier id, CreativeTabBuilder builder) {
        return new RegistryResult<>(mcplr1_20.registryItemGroup(id, builder));
    }

    public RegistryResult<CreativeModeTab> registerItemGroup(CreativeTabBuilder builder) {
        return registerItemGroup(builder.getIdentifier(), builder);
    }

    public RegistryResult<DataComponentType<?>> registerDataComponentType(Identifier id, Supplier<DataComponentType<?>> supplier) {
        return new RegistryResult<>(mcplr1_21.registryDataComponentType(id, supplier));
    }

    public static void registerFuel(int time, ItemLike... item) {
        FuelRegistry.register(time, item);
    }

    public void allRegister() {
        // 1.16 Register
        mcplr.allRegister1_16();

        mcplr1_20.register();
        mcplr1_21.register();

        // ItemGroup
        CreativeTabManager.allRegister();
    }

    @Deprecated
    public MCPLRegistry getMcplr() {
        return mcplr;
    }

    @Deprecated
    public MCPLRegistry1_20 getMcplr1_20() {
        return mcplr1_20;
    }

    @Deprecated
    public MCPLRegistry1_21 getMcplr1_21() {
        return mcplr1_21;
    }

    public String getNamespace() {
        return MOD_ID;
    }
}
