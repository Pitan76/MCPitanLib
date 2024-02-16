package net.pitan76.mcpitanlib.api.registry;

import me.shedaniel.architectury.registry.RegistrySupplier;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.EntityType;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemGroup;
import net.minecraft.particle.ParticleType;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.pitan76.mcpitanlib.MCPitanLib;
import net.pitan76.mcpitanlib.api.block.CompatibleBlockSettings;
import net.pitan76.mcpitanlib.api.block.CompatibleMaterial;
import net.pitan76.mcpitanlib.api.gui.ExtendedScreenHandlerTypeBuilder;
import net.pitan76.mcpitanlib.api.item.CompatibleItemSettings;
import net.pitan76.mcpitanlib.api.item.CreativeTabManager;
import net.pitan76.mcpitanlib.api.registry.result.RegistryResult;
import net.pitan76.mcpitanlib.api.util.BlockUtil;
import net.pitan76.mcpitanlib.api.util.ItemUtil;
import net.pitan76.mcpitanlib.core.registry.FuelRegistry;
import net.pitan76.mcpitanlib.core.registry.MCPLRegistry;
import net.pitan76.mcpitanlib.core.registry.MCPLRegistry1_20;

import java.util.function.Supplier;

public class CompatRegistry {

    private final MCPLRegistry mcplr;
    private final MCPLRegistry1_20 mcplr1_20;

    /**
     * @deprecated Use {@link #createRegistry(String)} instead
     */
    public CompatRegistry(String MOD_ID) {
        mcplr = new MCPLRegistry(MOD_ID);
        mcplr1_20 = new MCPLRegistry1_20(mcplr);
    }

    /**
     * Create a new CompatRegistry
     * @param MOD_ID The mod id
     * @return The new CompatRegistry
     */
    public static CompatRegistry createRegistry(String MOD_ID) {
        return new CompatRegistry(MOD_ID);
    }

    /**
     * Register an item
     * @param id The item id
     * @param supplier The item supplier
     * @return The registry result
     */
    public RegistryResult<Item> registerItem(Identifier id, Supplier<Item> supplier) {
        if (MCPitanLib.isItemBlackListed(id)) supplier = () -> ItemUtil.of(CompatibleItemSettings.of());
        RegistrySupplier<Item> registrySupplier = mcplr.registryItem(id, supplier);
        CreativeTabManager.register(id);
        return new RegistryResult<>(registrySupplier);
    }

    public RegistryResult<Block> registerBlock(Identifier id, Supplier<Block> supplier) {
        if (MCPitanLib.isBlockBlackListed(id)) supplier = () -> BlockUtil.of(CompatibleBlockSettings.of(CompatibleMaterial.STONE));
        return new RegistryResult<>(mcplr.registryBlock(id, supplier));
    }

    public RegistryResult<ScreenHandlerType<?>> registerScreenHandlerType(Identifier id, Supplier<ScreenHandlerType<?>> supplier) {
        return new RegistryResult<>(mcplr.registryScreenHandlerType(id, supplier));
    }

    @Deprecated
    public RegistryResult<ScreenHandlerType<?>> registerExtendedScreenHandlerType(Identifier id, Supplier<ExtendedScreenHandlerTypeBuilder<?>> supplier) {
        return registerScreenHandlerType(id, () -> supplier.get().build());
    }

    public RegistryResult<ScreenHandlerType<?>> registerMenu(Identifier id, Supplier<ScreenHandlerType<?>> supplier) {
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
        return registerSoundEvent(id, () -> new SoundEvent(id));
    }

    public RegistryResult<SoundEvent> registerSoundEvent(Identifier id, float distanceToTravel) {
        return registerSoundEvent(id, () -> new SoundEvent(id));
    }

    public RegistryResult<Fluid> registerFluid(Identifier id, Supplier<Fluid> supplier) {
        return new RegistryResult<>(mcplr.registryFluid(id, supplier));
    }

    public RegistryResult<ParticleType<?>> registerParticleType(Identifier id, Supplier<ParticleType<?>> supplier) {
        return new RegistryResult<>(mcplr.registryParticleType(id, supplier));
    }

    public RegistryResult<ItemGroup> registerItemGroup(Identifier id, Supplier<ItemGroup> supplier) {
        return new RegistryResult<>(null);
    }

    public static void registerFuel(int time, ItemConvertible... item) {
        FuelRegistry.register(time, item);
    }

    public void allRegister() {
        // 1.16 Register
        mcplr.allRegister1_16();

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
}
