package net.pitan76.mcpitanlib.api.item;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.equipment.EquipmentType;
import org.jetbrains.annotations.Nullable;

public class ArmorEquipmentType {

    public static ArmorEquipmentType HEAD = new ArmorEquipmentType(EquipmentSlot.HEAD, EquipmentType.HELMET);
    public static ArmorEquipmentType CHEST = new ArmorEquipmentType(EquipmentSlot.CHEST, EquipmentType.CHESTPLATE);
    public static ArmorEquipmentType LEGS = new ArmorEquipmentType(EquipmentSlot.LEGS, EquipmentType.LEGGINGS);
    public static ArmorEquipmentType FEET = new ArmorEquipmentType(EquipmentSlot.FEET, EquipmentType.BOOTS);

    // New type for animals from 1.20.5
    public static ArmorEquipmentType BODY = new ArmorEquipmentType(EquipmentSlot.BODY, EquipmentType.BODY);

    protected final EquipmentSlot slot;
    protected final EquipmentType type;

    protected ArmorEquipmentType(EquipmentSlot slot, EquipmentType type) {
        this.slot = slot;
        this.type = type;
    }

    @Deprecated
    public EquipmentSlot getSlot() {
        return slot;
    }

    @Deprecated
    public EquipmentType getType() {
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
    public static ArmorEquipmentType of(EquipmentType type) {
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
