package net.pitan76.mcpitanlib.api.registry;

import net.minecraft.resources.Identifier;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.pitan76.mcpitanlib.api.registry.result.RegistryResult;
import net.pitan76.mcpitanlib.api.registry.result.RegistrySupplier;

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
//        RegistrySupplier<ConfiguredFeature<?, ?>> feature = CONFIGURED_FEATURE.register(id, supplier);
//        ResourceKey<ConfiguredFeature<?, ?>> key = ResourceKey.create(Registries.CONFIGURED_FEATURE, id);
//        return new RegistryResult<>(Registry.register(BuiltInRegistries.FEATURE, key, supplier.get()));
        return null;
    }

    /**
     * Register a placed feature
     * @param id The id of the placed feature
     * @param supplier The supplier of the placed feature
     * @return The result of the registration
     */
    public RegistryResult<PlacedFeature> registerPlacedFeature(Identifier id, Supplier<PlacedFeature> supplier) {
//        RegistrySupplier<PlacedFeature> feature = PLACED_FEATURE.register(id, supplier);
//        return new RegistryResult<>(feature);
//        ResourceKey PlacedFeatureKey = ResourceKey.create(Registries.PLACED_FEATURE, id);
//        return new RegistryResult<>(Registry.register(BuiltInRegistries.FEATURE, PlacedFeatureKey, supplier.get()));
        return null;
    }

    /**
     * Replace the properties of a biome
     * @param decoration The decoration step
     * @param feature The feature to replace
     */
    public static void replaceProperties(GenerationStep.Decoration decoration, RegistrySupplier<PlacedFeature> feature) {
//        BiomeModifications.replaceProperties((ctx, mutable) -> mutable.getGenerationProperties().addFeature(decoration, feature));
    }

    public static void replaceProperties(GenerationStep.Decoration decoration, RegistryResult<PlacedFeature> feature) {
        replaceProperties(decoration, feature.supplier);
    }

    /**
     * Replace the properties of a biome
     * @param decoration The decoration step
     * @param feature The feature to replace
     */
    public static void replaceProperties(GenerationStep.Decoration decoration, PlacedFeature feature) {
//        BiomeModifications.replaceProperties((ctx, mutable) -> mutable.getGenerationProperties().addFeature(decoration, Holder.direct(feature)));
    }

    /**
     * Add a feature to the biome properties
     * @param decoration The decoration step
     * @param feature The feature to add
     */
    public static void addProperties(GenerationStep.Decoration decoration, RegistrySupplier<PlacedFeature> feature) {
//        BiomeModifications.addProperties((ctx, mutable) -> mutable.getGenerationProperties().addFeature(decoration, feature));
    }

    public static void addProperties(GenerationStep.Decoration decoration, RegistryResult<PlacedFeature> feature) {
        addProperties(decoration, feature.supplier);
    }

    /**
     * Add a feature to the biome properties
     * @param decoration The decoration step
     * @param feature The feature to add
     */
    public static void addProperties(GenerationStep.Decoration decoration, PlacedFeature feature) {
//        BiomeModifications.addProperties((ctx, mutable) -> mutable.getGenerationProperties().addFeature(decoration, Holder.direct(feature)));
    }
}
