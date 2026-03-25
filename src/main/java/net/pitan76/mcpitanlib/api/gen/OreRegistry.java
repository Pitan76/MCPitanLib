package net.pitan76.mcpitanlib.api.gen;

import net.minecraft.world.level.block.Block;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
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
        Identifier identifier = BlockUtil.toID(block).withSuffix("_ore_feature");
        RegistryResult<ConfiguredFeature<?, ?>> configuredFuture = registry.registerFeature(identifier,
                () -> FeatureConfigUtil.createConfiguredFeature(
                        FeatureConfigUtil.createStoneOreFeatureConfig(block.defaultBlockState(), size)
                )
        );

        RegistryResult<PlacedFeature> placedFeature = registry.registerPlacedFeature(identifier,
                () -> PlacedFutureUtil.create(configuredFuture.getOrNull(),
                        PlacedFutureUtil.createPlacementModifiers(count, height)
                )
        );

        WorldGenRegistry.addProperties(GenerationStep.Decoration.UNDERGROUND_DECORATION, placedFeature);
        return identifier;
    }
}
