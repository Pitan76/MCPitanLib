package net.pitan76.mcpitanlib.api.item;

import net.minecraft.item.Item;
import net.minecraft.item.equipment.ArmorMaterial;
import net.minecraft.item.equipment.EquipmentType;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.pitan76.mcpitanlib.api.util.IngredientUtil;

import java.util.EnumMap;

public interface CompatibleArmorMaterial {
    int getDurability(ArmorEquipmentType type);

    int getProtection(ArmorEquipmentType type);

    int getEnchantability();

    SoundEvent getEquipSound();

    default Ingredient getRepairIngredient() {
        return IngredientUtil.fromTagByIdentifier(getRepairTag().id());
    }

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

    default TagKey<Item> getRepairTag() {
        return ItemTags.REPAIRS_IRON_ARMOR;
    }

    @Deprecated
    default ArmorMaterial build() {
        return new ArmorMaterial(0, getDefense(), getEnchantability(), RegistryEntry.of(getEquipSound()), getToughness(), getKnockbackResistance(), getRepairTag(), getId());
    }

    default EnumMap<EquipmentType, Integer> getDefense() {
        EnumMap<EquipmentType, Integer> map = new EnumMap<>(EquipmentType.class);
        map.put(EquipmentType.HELMET, this.getProtection(ArmorEquipmentType.HEAD));
        map.put(EquipmentType.CHESTPLATE, this.getProtection(ArmorEquipmentType.CHEST));
        map.put(EquipmentType.LEGGINGS, this.getProtection(ArmorEquipmentType.LEGS));
        map.put(EquipmentType.BOOTS, this.getProtection(ArmorEquipmentType.FEET));
        return map;
    }
}
