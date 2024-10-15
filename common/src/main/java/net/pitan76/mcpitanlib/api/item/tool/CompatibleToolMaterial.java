package net.pitan76.mcpitanlib.api.item.tool;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.registry.tag.TagKey;
import net.pitan76.mcpitanlib.api.util.IngredientUtil;

public interface CompatibleToolMaterial {

    int getCompatMiningLevel();

    float getCompatAttackDamage();

    float getCompatMiningSpeedMultiplier();

    default Ingredient getCompatRepairIngredient() {
        return IngredientUtil.fromTagByIdentifier(getRepairTag().id());
    }

    int getCompatDurability();

    int getCompatEnchantability();

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
    default float getAttackDamage() {
        return getCompatAttackDamage();
    }

    @Deprecated
    default float getMiningSpeedMultiplier() {
        return getCompatMiningSpeedMultiplier();
    }

    @Deprecated
    default Ingredient getRepairIngredient() {
        return getCompatRepairIngredient();
    }

    @Deprecated
    default int getDurability() {
        return getCompatDurability();
    }

    @Deprecated
    default int getEnchantability() {
        return getCompatEnchantability();
    }

    default TagKey<Item> getRepairTag() {
        return ItemTags.IRON_TOOL_MATERIALS;
    }

    default ToolMaterial build() {
        return new ToolMaterial(getInverseTag(), getCompatDurability(), getCompatMiningSpeedMultiplier(), getCompatAttackDamage(), getCompatEnchantability(), getRepairTag());
    }
}