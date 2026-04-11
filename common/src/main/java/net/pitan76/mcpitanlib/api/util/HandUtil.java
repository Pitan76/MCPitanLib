package net.pitan76.mcpitanlib.api.util;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.InteractionHand;

public class HandUtil {
    public static InteractionHand getOppositeHand(InteractionHand hand) {
        return hand == InteractionHand.MAIN_HAND ? InteractionHand.OFF_HAND : InteractionHand.MAIN_HAND;
    }

    public static EquipmentSlot getEquipmentSlot(InteractionHand hand) {
        return hand == InteractionHand.MAIN_HAND ? EquipmentSlot.MAINHAND : EquipmentSlot.OFFHAND;
    }

    public static InteractionHand getHand(EquipmentSlot slot) {
        return slot == EquipmentSlot.MAINHAND ? InteractionHand.MAIN_HAND : InteractionHand.OFF_HAND;
    }

    public static InteractionHand getHand(boolean mainHand) {
        return mainHand ? InteractionHand.MAIN_HAND : InteractionHand.OFF_HAND;
    }

    public static boolean isMainHand(InteractionHand hand) {
        return hand == InteractionHand.MAIN_HAND;
    }

    public static boolean isOffHand(InteractionHand hand) {
        return hand == InteractionHand.OFF_HAND;
    }

    public static boolean isMainHand(EquipmentSlot slot) {
        return slot == EquipmentSlot.MAINHAND;
    }

    public static boolean isOffHand(EquipmentSlot slot) {
        return slot == EquipmentSlot.OFFHAND;
    }
}
