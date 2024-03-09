package net.pitan76.mcpitanlib.api.util;

import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.pitan76.mcpitanlib.api.item.ArmorEquipmentType;
import net.pitan76.mcpitanlib.api.item.CompatibleArmorMaterial;

public class ArmorMaterialUtil {
    public static CompatibleArmorMaterial create(String name, int[] durability, int[] protectionAmounts, int enchantability, SoundEvent equipSound, float toughness, float knockbackResistance, Ingredient repairIngredient) {
        return create(new Identifier(name), durability, protectionAmounts, enchantability, equipSound, toughness, knockbackResistance, repairIngredient);
    }
    public static CompatibleArmorMaterial create(String name, int durability, int protectionAmount, int enchantability, SoundEvent equipSound, float toughness, float knockbackResistance, Ingredient repairIngredient) {
        return create(new Identifier(name), durability, protectionAmount, enchantability, equipSound, toughness, knockbackResistance, repairIngredient);
    }

    public static CompatibleArmorMaterial create(Identifier id, int[] durability, int[] protectionAmounts, int enchantability, SoundEvent equipSound, float toughness, float knockbackResistance, Ingredient repairIngredient) {
        return new CompatibleArmorMaterial() {
            @Override
            public int getDurability(ArmorEquipmentType type) {
                switch (type.getSlot()) {
                    case HEAD:
                        return durability[0];
                    
                    case CHEST:
                        return durability[1];
                    
                    case LEGS:
                        return durability[2];

                    case FEET:
                        return durability[3];

                    default:
                        return 0;

                }
            }

            @Override
            public int getProtection(ArmorEquipmentType type) {
                switch (type.getSlot()) {
                    case HEAD:
                        return protectionAmounts[0];

                    case CHEST:
                        return protectionAmounts[1];

                    case LEGS:
                        return protectionAmounts[2];

                    case FEET:
                        return protectionAmounts[3];

                    default:
                        return 0;

                }
            }

            @Override
            public int getEnchantability() {
                return enchantability;
            }

            @Override
            public SoundEvent getEquipSound() {
                return equipSound;
            }

            @Override
            public Ingredient getRepairIngredient() {
                return repairIngredient;
            }

            @Override
            public Identifier getId() {
                return id;
            }

            @Override
            public float getToughness() {
                return toughness;
            }

            @Override
            public float getKnockbackResistance() {
                return knockbackResistance;
            }
        };
    }

    public static CompatibleArmorMaterial create(Identifier id, int durability, int protectionAmount, int enchantability, SoundEvent equipSound, float toughness, float knockbackResistance, Ingredient repairIngredient) {
        return new CompatibleArmorMaterial() {
            @Override
            public int getDurability(ArmorEquipmentType type) {
                return durability;
            }

            @Override
            public int getProtection(ArmorEquipmentType type) {
                return protectionAmount;
            }

            @Override
            public int getEnchantability() {
                return enchantability;
            }

            @Override
            public SoundEvent getEquipSound() {
                return equipSound;
            }

            @Override
            public Ingredient getRepairIngredient() {
                return repairIngredient;
            }

            @Override
            public Identifier getId() {
                return id;
            }

            @Override
            public float getToughness() {
                return toughness;
            }

            @Override
            public float getKnockbackResistance() {
                return knockbackResistance;
            }
        };
    }
}
