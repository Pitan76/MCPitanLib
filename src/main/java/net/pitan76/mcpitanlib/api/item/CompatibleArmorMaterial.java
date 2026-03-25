package net.pitan76.mcpitanlib.api.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.EquipmentAssets;
import net.minecraft.world.item.equipment.ArmorType;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.resources.ResourceKey;
import net.minecraft.core.Holder;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.resources.Identifier;
import net.pitan76.mcpitanlib.api.util.IngredientUtil;

import java.util.EnumMap;

public interface CompatibleArmorMaterial {
    int getDurability(ArmorEquipmentType type);

    int getProtection(ArmorEquipmentType type);

    int getEnchantability();

    SoundEvent getEquipSound();

    default Ingredient getRepairIngredient() {
        return IngredientUtil.fromTagByIdentifier(getRepairTag().location());
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
        return new ArmorMaterial(0, getDefense(), getEnchantability(), Holder.direct(getEquipSound()), getToughness(), getKnockbackResistance(), getRepairTag(), ResourceKey.create(EquipmentAssets.ROOT_ID, getId()));
    }

    default EnumMap<ArmorType, Integer> getDefense() {
        EnumMap<ArmorType, Integer> map = new EnumMap<>(ArmorType.class);
        map.put(ArmorType.HELMET, this.getProtection(ArmorEquipmentType.HEAD));
        map.put(ArmorType.CHESTPLATE, this.getProtection(ArmorEquipmentType.CHEST));
        map.put(ArmorType.LEGGINGS, this.getProtection(ArmorEquipmentType.LEGS));
        map.put(ArmorType.BOOTS, this.getProtection(ArmorEquipmentType.FEET));
        return map;
    }
}
