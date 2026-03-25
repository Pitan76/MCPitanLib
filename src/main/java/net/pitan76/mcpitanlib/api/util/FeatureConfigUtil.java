package net.pitan76.mcpitanlib.api.util;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.structure.rule.RuleTest;
import net.minecraft.structure.rule.TagMatchRuleTest;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
     * @param targets The targets
     * @param size The size
     * @return The new OreFeatureConfig
     */
    public static OreFeatureConfig createOreFeatureConfig(List<OreFeatureConfig.Target> targets, int size) {
        return new OreFeatureConfig(targets, size);
    }

    /**
     * Create a new OreFeatureConfig
     * @param targetMap The target map
     * @param size The size
     * @return The new OreFeatureConfig
     */
    public static OreFeatureConfig createOreFeatureConfig(Map<RuleTest, BlockState> targetMap, int size) {
        List<OreFeatureConfig.Target> targets = new ArrayList<>();
        targetMap.forEach((ruleTest, blockState) -> targets.add(OreFeatureConfig.createTarget(ruleTest, blockState)));
        return createOreFeatureConfig(targets, size);
    }

    /**
     * Create a new OreFeatureConfig
     * @param state The block state
     * @param size The size
     * @return The new OreFeatureConfig
     */
    public static OreFeatureConfig createStoneOreFeatureConfig(BlockState state, int size) {
        RuleTest ruleTest = new TagMatchRuleTest(BlockTags.BASE_STONE_OVERWORLD);
        return createOreFeatureConfig(ruleTest, state, size);
    }
}
