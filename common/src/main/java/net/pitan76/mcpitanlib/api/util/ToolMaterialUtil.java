package net.pitan76.mcpitanlib.api.util;

import net.minecraft.recipe.Ingredient;
import net.pitan76.mcpitanlib.api.item.tool.CompatibleToolMaterial;

public class ToolMaterialUtil {
    public CompatibleToolMaterial create(int miningLevel, int durability, float miningSpeed, float attackDamage, int enchantability, Ingredient repairIngredient) {
        return new CompatibleToolMaterial() {
            @Override
            public int getCompatMiningLevel() {
                return miningLevel;
            }

            @Override
            public int getCompatDurability() {
                return durability;
            }

            @Override
            public float getCompatMiningSpeedMultiplier() {
                return miningSpeed;
            }

            @Override
            public Ingredient getCompatRepairIngredient() {
                return repairIngredient;
            }

            @Override
            public float getCompatAttackDamage() {
                return attackDamage;
            }

            @Override
            public int getCompatEnchantability() {
                return enchantability;
            }
        };
    }
}
