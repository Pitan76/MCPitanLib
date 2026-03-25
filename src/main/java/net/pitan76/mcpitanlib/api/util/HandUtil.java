package net.pitan76.mcpitanlib.api.util;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.util.Hand;

public class HandUtil {
    public static Hand getOppositeHand(Hand hand) {
        return hand == Hand.MAIN_HAND ? Hand.OFF_HAND : Hand.MAIN_HAND;
    }

    public static EquipmentSlot getEquipmentSlot(Hand hand) {
        return hand == Hand.MAIN_HAND ? EquipmentSlot.MAINHAND : EquipmentSlot.OFFHAND;
    }

    public static Hand getHand(EquipmentSlot slot) {
        return slot == EquipmentSlot.MAINHAND ? Hand.MAIN_HAND : Hand.OFF_HAND;
    }

    public static Hand getHand(boolean mainHand) {
        return mainHand ? Hand.MAIN_HAND : Hand.OFF_HAND;
    }

    public static boolean isMainHand(Hand hand) {
        return hand == Hand.MAIN_HAND;
    }

    public static boolean isOffHand(Hand hand) {
        return hand == Hand.OFF_HAND;
    }

    public static boolean isMainHand(EquipmentSlot slot) {
        return slot == EquipmentSlot.MAINHAND;
    }

    public static boolean isOffHand(EquipmentSlot slot) {
        return slot == EquipmentSlot.OFFHAND;
    }
}
