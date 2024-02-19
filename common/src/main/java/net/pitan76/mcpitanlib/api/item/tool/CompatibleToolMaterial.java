package net.pitan76.mcpitanlib.api.item.tool;

import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;

public interface CompatibleToolMaterial extends ToolMaterial {
    @Deprecated
    @Override
    default int getMiningLevel() {
        return getCompatMiningLevel();
    }

    int getCompatMiningLevel();

    @Override
    float getAttackDamage();

    @Override
    float getMiningSpeedMultiplier();

    @Override
    Ingredient getRepairIngredient();

    @Override
    int getDurability();

    @Override
    int getEnchantability();
}