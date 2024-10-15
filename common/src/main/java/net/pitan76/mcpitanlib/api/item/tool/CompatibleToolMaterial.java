package net.pitan76.mcpitanlib.api.item.tool;

import net.minecraft.item.Item;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.tag.ItemTags;
import net.pitan76.mcpitanlib.api.tag.TagKey;

public interface CompatibleToolMaterial {

    int getCompatMiningLevel();

    float getCompatAttackDamage();

    float getCompatMiningSpeedMultiplier();

    default Ingredient getCompatRepairIngredient() {
        return Ingredient.fromTag(getRepairTag().getTagKey());
    }

    int getCompatDurability();

    int getCompatEnchantability();

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
        return (TagKey<Item>) TagKey.create(TagKey.Type.ITEM, ItemTags.STONE_TOOL_MATERIALS.getId());
    }

    default ToolMaterial build() {
        return new ToolMaterial() {
            @Override
            public int getDurability() {
                return getCompatDurability();
            }

            @Override
            public float getMiningSpeedMultiplier() {
                return getCompatMiningSpeedMultiplier();
            }

            @Override
            public float getAttackDamage() {
                return getCompatAttackDamage();
            }

            @Override
            public int getMiningLevel() {
                return getCompatMiningLevel();
            }

            @Override
            public int getEnchantability() {
                return getCompatEnchantability();
            }

            @Override
            public Ingredient getRepairIngredient() {
                return getCompatRepairIngredient();
            }
        };
    }
}