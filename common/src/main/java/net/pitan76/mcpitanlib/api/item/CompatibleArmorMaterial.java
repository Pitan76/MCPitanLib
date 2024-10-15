package net.pitan76.mcpitanlib.api.item;

import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;

import java.util.EnumMap;
import java.util.List;
import java.util.function.Supplier;

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
