package net.pitan76.mcpitanlib.api.registry;

import dev.architectury.registry.level.biome.BiomeModifications;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.pitan76.mcpitanlib.api.registry.result.RegistryResult;

import java.util.function.Supplier;

public class WorldGenRegistry {
    protected String MOD_ID;

    protected DeferredRegister<ConfiguredFeature<?, ?>> CONFIGURED_FEATURE;
    protected DeferredRegister<PlacedFeature> PLACED_FEATURE;

    @Deprecated
    public WorldGenRegistry(String MOD_ID) {
        this.MOD_ID = MOD_ID;
        CONFIGURED_FEATURE = DeferredRegister.create(MOD_ID, RegistryKeys.CONFIGURED_FEATURE);
        PLACED_FEATURE = DeferredRegister.create(MOD_ID, RegistryKeys.PLACED_FEATURE);
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
        RegistrySupplier<ConfiguredFeature<?, ?>> feature = CONFIGURED_FEATURE.register(id, supplier);
        return new RegistryResult<>(feature);
    }

    /**
     * Register a placed feature
     * @param id The id of the placed feature
     * @param supplier The supplier of the placed feature
     * @return The result of the registration
     */
    public RegistryResult<PlacedFeature> registerPlacedFeature(Identifier id, Supplier<PlacedFeature> supplier) {
        RegistrySupplier<PlacedFeature> feature = PLACED_FEATURE.register(id, supplier);
        return new RegistryResult<>(feature);
    }

    /**
     * Replace the properties of a biome
     * @param decoration The decoration step
     * @param feature The feature to replace
     */
    public static void replaceProperties(GenerationStep.Feature decoration, RegistrySupplier<PlacedFeature> feature) {
        BiomeModifications.replaceProperties((ctx, mutable) -> mutable.getGenerationProperties().addFeature(decoration, RegistryEntry.of(feature.getOrNull())));
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
        BiomeModifications.replaceProperties((ctx, mutable) -> mutable.getGenerationProperties().addFeature(decoration, RegistryEntry.of(feature)));
    }

    /**
     * Add a feature to the biome properties
     * @param decoration The decoration step
     * @param feature The feature to add
     */
    public static void addProperties(GenerationStep.Feature decoration, RegistrySupplier<PlacedFeature> feature) {
        BiomeModifications.addProperties((ctx, mutable) -> mutable.getGenerationProperties().addFeature(decoration, RegistryEntry.of(feature.getOrNull())));
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
        BiomeModifications.addProperties((ctx, mutable) -> mutable.getGenerationProperties().addFeature(decoration, RegistryEntry.of(feature)));
    }
}
