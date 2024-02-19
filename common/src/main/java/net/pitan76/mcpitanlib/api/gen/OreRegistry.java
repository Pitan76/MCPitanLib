package net.pitan76.mcpitanlib.api.gen;

import net.minecraft.block.Block;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.pitan76.mcpitanlib.api.registry.WorldGenRegistry;
import net.pitan76.mcpitanlib.api.registry.result.RegistryResult;
import net.pitan76.mcpitanlib.api.util.BlockUtil;
import net.pitan76.mcpitanlib.api.util.FeatureConfigUtil;
import net.pitan76.mcpitanlib.api.util.PlacedFutureUtil;

@Deprecated
public class OreRegistry {
    /**
     * Generating custom ore in stone
     * @param registry WorldGenRegistry
     * @param block Block
     * @param size Ore size
     * @param height generating height
     * @param count generating count per chunk
     * @return Identifier of feature
     */
    public static Identifier registerStoneOre(WorldGenRegistry registry, Block block, int size, int height, int count) {
        Identifier blockId = BlockUtil.toID(block);
        Identifier identifier = new Identifier(blockId.getNamespace(), blockId.getPath() + "_ore_feature");
        RegistryResult<ConfiguredFeature<?, ?>> configuredFuture = registry.registerFeature(identifier,
                () -> FeatureConfigUtil.createConfiguredFeature(
                        FeatureConfigUtil.createStoneOreFeatureConfig(block.getDefaultState(), size)
                )
        );

        RegistryResult<PlacedFeature> placedFeature = registry.registerPlacedFeature(identifier,
                () -> PlacedFutureUtil.create(configuredFuture.getOrNull(),
                        PlacedFutureUtil.createPlacementModifiers(count, height)
                )
        );

        WorldGenRegistry.addProperties(GenerationStep.Feature.UNDERGROUND_ORES, placedFeature);
        return identifier;
    }
}
