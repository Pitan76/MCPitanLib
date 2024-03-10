package net.pitan76.mcpitanlib.api.item;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public interface CompatibleArmorMaterial {
    int getDurability(ArmorEquipmentType type);

    int getProtection(ArmorEquipmentType type);

    int getEnchantability();

    SoundEvent getEquipSound();

    Ingredient getRepairIngredient();

    /**
     * @return the name of the material
     */
    default String getName() {
        return getId().getPath();
    }

    /**
     * @return the id of the material
     */
    Identifier getId();

    float getToughness();

    float getKnockbackResistance();

    @Deprecated
    default ArmorMaterial build() {
        return new ArmorMaterial() {
            @Override
            public int getDurability(ArmorItem.Type type) {
                return CompatibleArmorMaterial.this.getDurability(ArmorEquipmentType.of(type));
            }

            @Override
            public int getProtection(ArmorItem.Type type) {
                return CompatibleArmorMaterial.this.getProtection(ArmorEquipmentType.of(type));
            }

            @Override
            public int getEnchantability() {
                return CompatibleArmorMaterial.this.getEnchantability();
            }

            @Override
            public SoundEvent getEquipSound() {
                return CompatibleArmorMaterial.this.getEquipSound();
            }

            @Override
            public Ingredient getRepairIngredient() {
                return CompatibleArmorMaterial.this.getRepairIngredient();
            }

            @Override
            public String getName() {
                return CompatibleArmorMaterial.this.getName();
            }

            @Override
            public float getToughness() {
                return CompatibleArmorMaterial.this.getToughness();
            }

            @Override
            public float getKnockbackResistance() {
                return CompatibleArmorMaterial.this.getKnockbackResistance();
            }
        };
    }
}
