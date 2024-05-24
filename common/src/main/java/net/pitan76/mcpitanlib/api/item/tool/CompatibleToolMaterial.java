package net.pitan76.mcpitanlib.api.item.tool;

import net.minecraft.block.Block;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.ToolMaterials;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.TagKey;

public interface CompatibleToolMaterial extends ToolMaterial {

    int getCompatMiningLevel();

    float getCompatAttackDamage();

    float getCompatMiningSpeedMultiplier();

    Ingredient getCompatRepairIngredient();

    int getCompatDurability();

    int getCompatEnchantability();

    /*
    @Deprecated
    @Override
    default int getMiningLevel() {
        return getCompatMiningLevel();
    }
     */


    @Override
    default TagKey<Block> getInverseTag() {

        return level2inverseTag(getCompatMiningLevel());
    }

    public static TagKey<Block> level2inverseTag(int level) {
        switch (level) {
            case 1:
                return BlockTags.INCORRECT_FOR_WOODEN_TOOL;
            case 2:
                return BlockTags.INCORRECT_FOR_STONE_TOOL;
            case 3:
                return BlockTags.INCORRECT_FOR_IRON_TOOL;
            case 4:
                return BlockTags.INCORRECT_FOR_GOLD_TOOL;
            case 5:
                return BlockTags.INCORRECT_FOR_DIAMOND_TOOL;
            case 6:
                return BlockTags.INCORRECT_FOR_NETHERITE_TOOL;

            default:
                return BlockTags.INCORRECT_FOR_WOODEN_TOOL;
        }
    }

    @Deprecated
    @Override
    default float getAttackDamage() {
        return getCompatAttackDamage();
    }

    @Deprecated
    @Override
    default float getMiningSpeedMultiplier() {
        return getCompatMiningSpeedMultiplier();
    }

    @Deprecated
    @Override
    default Ingredient getRepairIngredient() {
        return getCompatRepairIngredient();
    }

    @Deprecated
    @Override
    default int getDurability() {
        return getCompatDurability();
    }

    @Deprecated
    @Override
    default int getEnchantability() {
        return getCompatEnchantability();
    }
}