package net.pitan76.mcpitanlib.api.registry;

import me.shedaniel.architectury.registry.BiomeModifications;
import me.shedaniel.architectury.registry.DeferredRegister;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.pitan76.mcpitanlib.api.registry.result.RegistryResult;

import java.util.function.Supplier;

public class WorldGenRegistry {
    protected String MOD_ID;

    protected DeferredRegister<ConfiguredFeature<?, ?>> CONFIGURED_FEATURE;

    @Deprecated
    public WorldGenRegistry(String MOD_ID) {
        this.MOD_ID = MOD_ID;
        //CONFIGURED_FEATURE = DeferredRegister.create(MOD_ID, BuiltinRegistries.CONFIGURED_FEATURE);
        //PLACED_FEATURE = DeferredRegister.create(MOD_ID, BuiltinRegistries.PLACED_FEATURE);
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
    public ConfiguredFeature<?, ?> registerFeature(Identifier id, Supplier<ConfiguredFeature<?, ?>> supplier) {
        ConfiguredFeature<?, ?> feature = Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, id, supplier.get());
        //CONFIGURED_FEATURE.register(id, supplier);
        return feature;
    }

    /**
     * Register a placed feature
     * @param id The id of the placed feature
     * @param supplier The supplier of the placed feature
     * @return The result of the registration
     */
    public RegistryResult<?> registerPlacedFeature(Identifier id, Supplier<?> supplier) {
        //RegistrySupplier<PlacedFeature> feature = PLACED_FEATURE.register(id, supplier);
        return new RegistryResult<>(null);
    }

    /**
     * Replace the properties of a biome
     * @param decoration The decoration step
     * @param feature The feature to replace
     */
    public static void replaceProperties(GenerationStep.Feature decoration, ConfiguredFeature<?, ?>  feature) {
        BiomeModifications.replaceProperties((ctx, mutable) -> mutable.getGenerationProperties().addFeature(decoration, feature));
    }

    /**
     * Add the properties of a biome
     * @param decoration The decoration step
     * @param feature The feature to add
     */
    public static void addProperties(GenerationStep.Feature decoration, ConfiguredFeature<?, ?>  feature) {
        BiomeModifications.addProperties((ctx, mutable) -> mutable.getGenerationProperties().addFeature(decoration, feature));
    }
}
