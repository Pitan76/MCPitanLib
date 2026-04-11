package net.pitan76.mcpitanlib.api.item;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.equipment.ArmorType;
import org.jetbrains.annotations.Nullable;

public class ArmorEquipmentType {
    public static ArmorEquipmentType HEAD = new ArmorEquipmentType(EquipmentSlot.HEAD, ArmorType.HELMET);
    public static ArmorEquipmentType CHEST = new ArmorEquipmentType(EquipmentSlot.CHEST, ArmorType.CHESTPLATE);
    public static ArmorEquipmentType LEGS = new ArmorEquipmentType(EquipmentSlot.LEGS, ArmorType.LEGGINGS);
    public static ArmorEquipmentType FEET = new ArmorEquipmentType(EquipmentSlot.FEET, ArmorType.BOOTS);

    // New type for animals from 1.20.5
    public static ArmorEquipmentType BODY = new ArmorEquipmentType(EquipmentSlot.BODY, ArmorType.BODY);

    protected final EquipmentSlot slot;
    protected final ArmorType type;

    protected ArmorEquipmentType(EquipmentSlot slot, ArmorType type) {
        this.slot = slot;
        this.type = type;
    }

    @Deprecated
    public EquipmentSlot getSlot() {
        return slot;
    }

    @Deprecated
    public ArmorType getType() {
        return type;
    }

    @Nullable
    public static ArmorEquipmentType of(EquipmentSlot slot) {
        switch (slot) {
            case HEAD -> {
                return HEAD;
            }
            case CHEST -> {
                return CHEST;
            }
            case LEGS -> {
                return LEGS;
            }
            case FEET -> {
                return FEET;
            }
            default -> {
                return null;
            }
        }
    }

    @Nullable
    public static ArmorEquipmentType of(ArmorType type) {
        switch (type) {
            case HELMET -> {
                return HEAD;
            }
            case CHESTPLATE -> {
                return CHEST;
            }
            case LEGGINGS -> {
                return LEGS;
            }
            case BOOTS -> {
                return FEET;
            }
            default -> {
                return null;
            }
        }
    }
}
