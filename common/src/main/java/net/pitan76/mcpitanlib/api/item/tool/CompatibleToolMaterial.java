package net.pitan76.mcpitanlib.api.item.tool;

import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;

public interface CompatibleToolMaterial extends ToolMaterial {

    int getCompatMiningLevel();

    float getCompatAttackDamage();

    float getCompatMiningSpeedMultiplier();

    Ingredient getCompatRepairIngredient();

    int getCompatDurability();

    int getCompatEnchantability();

    @Deprecated
    @Override
    default int getMiningLevel() {
        return getCompatMiningLevel();
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