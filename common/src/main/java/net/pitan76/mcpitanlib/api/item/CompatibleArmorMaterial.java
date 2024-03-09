package net.pitan76.mcpitanlib.api.item;

import net.minecraft.entity.EquipmentSlot;
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
    default RegistryEntry<ArmorMaterial> build() {
        if (CompatibleArmorItem.CACHE.containsKey(this)) {
            return CompatibleArmorItem.CACHE.get(this);
        }

        RegistryEntry<ArmorMaterial> entry = register(getId(), Util.make(new EnumMap<>(ArmorItem.Type.class), (map) -> {
                    map.put(ArmorItem.Type.HELMET, this.getProtection(ArmorEquipmentType.HEAD));
                    map.put(ArmorItem.Type.CHESTPLATE, this.getProtection(ArmorEquipmentType.CHEST));
                    map.put(ArmorItem.Type.LEGGINGS, this.getProtection(ArmorEquipmentType.LEGS));
                    map.put(ArmorItem.Type.BOOTS, this.getProtection(ArmorEquipmentType.FEET));

                    map.put(ArmorItem.Type.BODY, this.getProtection(ArmorEquipmentType.BODY));
                }), getEnchantability(), SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, getToughness(), getKnockbackResistance(),
                this::getRepairIngredient
        );
        CompatibleArmorItem.CACHE.put(this, entry);
        return entry;
    }

    private static RegistryEntry<ArmorMaterial> register(Identifier id, EnumMap<ArmorItem.Type, Integer> defense, int enchantability, RegistryEntry<SoundEvent> equipSound, float toughness, float knockbackResistance, Supplier<Ingredient> repairIngredient) {
        List<ArmorMaterial.Layer> list = List.of(new ArmorMaterial.Layer(id));
        return register(id, defense, enchantability, equipSound, toughness, knockbackResistance, repairIngredient, list);
    }

    private static RegistryEntry<ArmorMaterial> register(Identifier id, EnumMap<ArmorItem.Type, Integer> defense, int enchantability, RegistryEntry<SoundEvent> equipSound, float toughness, float knockbackResistance, Supplier<Ingredient> repairIngredient, List<ArmorMaterial.Layer> layers) {
        EnumMap<ArmorItem.Type, Integer> enumMap = new EnumMap<>(ArmorItem.Type.class);
        ArmorItem.Type[] var9 = ArmorItem.Type.values();

        for (ArmorItem.Type type : var9) {
            enumMap.put(type, defense.get(type));
        }

        return Registry.registerReference(Registries.ARMOR_MATERIAL, id, new ArmorMaterial(enumMap, enchantability, equipSound, repairIngredient, layers, toughness, knockbackResistance));
    }
}
