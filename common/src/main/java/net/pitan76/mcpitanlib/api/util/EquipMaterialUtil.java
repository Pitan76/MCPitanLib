package net.pitan76.mcpitanlib.api.util;

import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import net.pitan76.mcpitanlib.api.item.ArmorEquipmentType;
import net.pitan76.mcpitanlib.api.item.CompatibleArmorMaterial;

public class EquipMaterialUtil {
    public static ToolMaterial createToolMaterial(int durability, float miningSpeedMultiplier, float attackDamage, int miningLevel, int enchantability, Ingredient repairIngredient) {
        return new ToolMaterial() {
            @Override
            public int getDurability() {
                return durability;
            }

            @Override
            public float getMiningSpeedMultiplier() {
                return miningSpeedMultiplier;
            }

            @Override
            public float getAttackDamage() {
                return attackDamage;
            }

            @Override
            public int getMiningLevel() {
                return miningLevel;
            }

            @Override
            public int getEnchantability() {
                return enchantability;
            }

            @Override
            public Ingredient getRepairIngredient() {
                return repairIngredient;
            }
        };
    }

    public static int toInt(ArmorEquipmentType type) {
        switch (type.getSlot()) {
            case HEAD:
                return 0;
            case CHEST:
                return 1;
            case LEGS:
                return 2;
            case FEET:
                return 3;
            default:
                return 0;
        }
    }

    public static CompatibleArmorMaterial createArmorMaterial(int[] durability, int[] protection, int enchantability, SoundEvent equipSound, Ingredient repairIngredient, String name, float toughness, float knockbackResistance) {
        return ArmorMaterialUtil.create(name, durability, protection, enchantability, equipSound, toughness, knockbackResistance, repairIngredient);
    }
}
