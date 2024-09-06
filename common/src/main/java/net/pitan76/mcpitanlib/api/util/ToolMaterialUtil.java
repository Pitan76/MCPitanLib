package net.pitan76.mcpitanlib.api.util;

import net.minecraft.recipe.Ingredient;
import net.pitan76.mcpitanlib.api.item.tool.CompatibleToolMaterial;

public class ToolMaterialUtil {
    // ミスでstatic化し忘れていたが、互換性のため保持しておく
    public CompatibleToolMaterial create(int miningLevel, int durability, float miningSpeed, float attackDamage, int enchantability, Ingredient repairIngredient) {
        return create2(miningLevel, durability, miningSpeed, attackDamage, enchantability, repairIngredient);
    }

    /**
     * Create CompatibleToolMaterial
     * @param miningLevel Mining Level
     * @param durability Durability
     * @param miningSpeed Mining Speed
     * @param attackDamage Attack Damage
     * @param enchantability Enchantability
     * @param repairIngredient Repair Ingredient
     * @return CompatibleToolMaterial
     */
    public static CompatibleToolMaterial create2(int miningLevel, int durability, float miningSpeed, float attackDamage, int enchantability, Ingredient repairIngredient) {
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
