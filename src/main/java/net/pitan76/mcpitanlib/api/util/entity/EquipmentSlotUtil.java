package net.pitan76.mcpitanlib.api.util.entity;

import net.minecraft.world.entity.EquipmentSlot;
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
        if (slot == null)
            return 0;

        return slot.getId();
    }

    public static EquipmentSlot fromEntitySlotId(int id) {
        for (EquipmentSlot slot : EquipmentSlot.values()) {
            if (slot.getId() == id) {
                return slot;
            }
        }
        return EquipmentSlot.MAINHAND;
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
            case BODY:
                return ArmorEquipmentType.BODY;
            default:
                return null;
        }
    }
}
