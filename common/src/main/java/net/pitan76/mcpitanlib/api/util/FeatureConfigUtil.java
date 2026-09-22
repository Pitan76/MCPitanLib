package net.pitan76.mcpitanlib.api.util;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.levelgen.feature.BlockReplacement;
import net.minecraft.world.level.levelgen.feature.OreFeature;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;
import net.minecraft.world.level.levelgen.feature.Feature;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FeatureConfigUtil {

    /**
     * Create a new ConfiguredFeature
     * @param oreFeatureConfig The ore feature config
     * @return The new ConfiguredFeature
     */
    public static Feature createConfiguredFeature(OreFeature oreFeatureConfig) {
        return oreFeatureConfig;
    }

    /**
     * Create a new OreFeatureConfig
     * @param test The rule test
     * @param state The block state
     * @param size The size
     * @return The new OreFeatureConfig
     */
    public static OreFeature createOreFeatureConfig(RuleTest test, BlockState state, int size) {
        return new OreFeature(test, state, size);
    }

    /**
     * Create a new OreFeatureConfig
     * @param test The rule test
     * @param block The block
     * @param size The size
     * @return The new OreFeatureConfig
     */
    public static OreFeature createOreFeatureConfig(RuleTest test, Block block, int size) {
        return createOreFeatureConfig(test, block.defaultBlockState(), size);
    }

    /**
     * Create a new OreFeatureConfig
     * @param targets The targets
     * @param size The size
     * @return The new OreFeatureConfig
     */
    public static OreFeature createOreFeatureConfig(List<BlockReplacement> targets, int size) {
        return new OreFeature(targets, size);
    }

    /**
     * Create a new OreFeatureConfig
     * @param targetMap The target map
     * @param size The size
     * @return The new OreFeatureConfig
     */
    public static OreFeature createOreFeatureConfig(Map<RuleTest, BlockState> targetMap, int size) {
        List<BlockReplacement> targets = new ArrayList<>();
        targetMap.forEach((ruleTest, blockState) -> targets.add(BlockReplacement.replace(ruleTest, blockState)));
        return createOreFeatureConfig(targets, size);
    }

    /**
     * Create a new OreFeatureConfig
     * @param state The block state
     * @param size The size
     * @return The new OreFeatureConfig
     */
    public static OreFeature createStoneOreFeatureConfig(BlockState state, int size) {
        RuleTest ruleTest = new TagMatchTest(BlockTags.BASE_STONE_OVERWORLD);
        return createOreFeatureConfig(ruleTest, state, size);
    }
}
