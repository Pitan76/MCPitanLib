package net.pitan76.mcpitanlib.api.util.entity;

import net.minecraft.entity.EquipmentSlot;
import net.pitan76.mcpitanlib.api.item.ArmorEquipmentType;

public class EquipmentSlotUtil {
    public static boolean isArmor(EquipmentSlot slot) {
        return slot == EquipmentSlot.HEAD || slot == EquipmentSlot.CHEST || slot == EquipmentSlot.LEGS || slot == EquipmentSlot.FEET;
    }

    public static boolean isMainHand(EquipmentSlot slot) {
        return slot == EquipmentSlot.MAINHAND;
    }

    public static boolean isOffHand(EquipmentSlot slot) {
        return slot == EquipmentSlot.OFFHAND;
    }

    public static boolean isWeapon(EquipmentSlot slot) {
        return slot == EquipmentSlot.MAINHAND || slot == EquipmentSlot.OFFHAND;
    }

    public static int getEntitySlotId(EquipmentSlot slot) {
        return slot.getEntitySlotId();
    }

    public static EquipmentSlot fromEntitySlotId(int id) {
        for (EquipmentSlot slot : EquipmentSlot.values()) {
            if (slot.getEntitySlotId() == id) {
                return slot;
            }
        }
        return null;
    }

    public static ArmorEquipmentType getArmorEquipmentType(EquipmentSlot slot) {
        switch (slot) {
            case HEAD:
                return ArmorEquipmentType.HEAD;
            case CHEST:
                return ArmorEquipmentType.CHEST;
            case LEGS:
                return ArmorEquipmentType.LEGS;
            case FEET:
                return ArmorEquipmentType.FEET;
            default:
                return null;
        }
    }
}
