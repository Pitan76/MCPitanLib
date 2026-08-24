package net.pitan76.mcpitanlib.api.item.tool;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.pitan76.mcpitanlib.api.util.IngredientUtil;

public interface CompatibleToolMaterial {

    int getCompatMiningLevel();

    float getCompatAttackDamage();

    float getCompatMiningSpeedMultiplier();

    default Ingredient getCompatRepairIngredient() {
        return IngredientUtil.fromTagByIdentifier(getRepairTag().location());
    }

    int getCompatDurability();

    int getCompatEnchantability();

    default TagKey<Block> getInverseTag() {

        return level2inverseTag(getCompatMiningLevel());
    }

    public static TagKey<Block> level2inverseTag(int level) {
        return CompatMiningLevel.fromLevel(level).getInverseTag();
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