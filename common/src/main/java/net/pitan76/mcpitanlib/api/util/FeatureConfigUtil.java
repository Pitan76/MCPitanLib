package net.pitan76.mcpitanlib.api.util;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.structure.rule.RuleTest;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;

public class FeatureConfigUtil {

    /**
     * Create a new ConfiguredFeature
     * @param oreFeatureConfig The ore feature config
     * @return The new ConfiguredFeature
     */
    public static ConfiguredFeature<?, ?> createConfiguredFeature(OreFeatureConfig oreFeatureConfig) {
        return new ConfiguredFeature<>(Feature.ORE, oreFeatureConfig);
    }

    /**
     * Create a new OreFeatureConfig
     * @param test The rule test
     * @param state The block state
     * @param size The size
     * @return The new OreFeatureConfig
     */
    public static OreFeatureConfig createOreFeatureConfig(RuleTest test, BlockState state, int size) {
        return new OreFeatureConfig(test, state, size);
    }

    /**
     * Create a new OreFeatureConfig
     * @param test The rule test
     * @param block The block
     * @param size The size
     * @return The new OreFeatureConfig
     */
    public static OreFeatureConfig createOreFeatureConfig(RuleTest test, Block block, int size) {
        return createOreFeatureConfig(test, block.getDefaultState(), size);
    }

    /**
     * Create a new OreFeatureConfig
     * @param state The block state
     * @param size The size
     * @return The new OreFeatureConfig
     */
    public static OreFeatureConfig createStoneOreFeatureConfig(BlockState state, int size) {
        RuleTest ruleTest = OreFeatureConfig.Rules.BASE_STONE_OVERWORLD;
        return createOreFeatureConfig(ruleTest, state, size);
    }
}
