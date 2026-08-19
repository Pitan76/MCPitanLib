package net.pitan76.mcpitanlib.api.registry;

import net.pitan76.mcpitanlib.api.registry.result.RegistrySupplier;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.pitan76.mcpitanlib.api.registry.result.RegistryResult;

import java.util.function.Supplier;

// TODO: Add support for biome modifiers and other world gen related things
public class WorldGenRegistry {
    protected String MOD_ID;

    @Deprecated
    public WorldGenRegistry(String MOD_ID) {
        this.MOD_ID = MOD_ID;
    }

    /**
     * Create a new CompatRegistry
     * @param MOD_ID The mod id
     * @return The new CompatRegistry
     */
    public static WorldGenRegistry createRegistry(String MOD_ID) {
        return new WorldGenRegistry(MOD_ID);
    }

    public static WorldGenRegistry createRegistry(CompatRegistry registry) {
        return registry.worldGenRegistry;
    }

    /**
     * Register a configured feature
     * @param id The id of the configured feature
     * @param supplier The supplier of the configured feature
     * @return The result of the registration
     */
    public RegistryResult<ConfiguredFeature<?, ?>> registerFeature(Identifier id, Supplier<ConfiguredFeature<?, ?>> supplier) {
        ConfiguredFeature<?, ?> feature = Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, id, supplier.get());
        //CONFIGURED_FEATURE.register(id, supplier);
        return new RegistryResult<>(null);
    }

    /**
     * Register a placed feature
     * @param id The id of the placed feature
     * @param supplier The supplier of the placed feature
     * @return The result of the registration
     */
    public RegistryResult<PlacedFeature> registerPlacedFeature(Identifier id, Supplier<PlacedFeature> supplier) {
        PlacedFeature feature = Registry.register(BuiltinRegistries.PLACED_FEATURE, id, supplier.get());
        //RegistrySupplier<PlacedFeature> feature = PLACED_FEATURE.register(id, supplier);
        return new RegistryResult<>(null);
    }

    /**
     * Replace the properties of a biome
     * @param decoration The decoration step
     * @param feature The feature to replace
     */
    public static void replaceProperties(GenerationStep.Feature decoration, RegistrySupplier<PlacedFeature> feature) {
        // TODO: Support biome modification without Architectury API
    }

    public static void replaceProperties(GenerationStep.Feature decoration, RegistryResult<PlacedFeature> feature) {
        replaceProperties(decoration, feature.supplier);
    }

    /**
     * Replace the properties of a biome
     * @param decoration The decoration step
     * @param feature The feature to replace
     */
    public static void replaceProperties(GenerationStep.Feature decoration, PlacedFeature feature) {
        // TODO: Support biome modification without Architectury API
    }

    /**
     * Add a feature to the biome properties
     * @param decoration The decoration step
     * @param feature The feature to add
     */
    public static void addProperties(GenerationStep.Feature decoration, RegistrySupplier<PlacedFeature> feature) {
        // TODO: Support biome modification without Architectury API
    }

    public static void addProperties(GenerationStep.Feature decoration, RegistryResult<PlacedFeature> feature) {
        addProperties(decoration, feature.supplier);
    }

    /**
     * Add a feature to the biome properties
     * @param decoration The decoration step
     * @param feature The feature to add
     */
    public static void addProperties(GenerationStep.Feature decoration, PlacedFeature feature) {
        // TODO: Support biome modification without Architectury API
    }
}
