package net.pitan76.mcpitanlib.api.util.client;

import net.minecraft.client.Mouse;

public class MouseUtil {

    public static Mouse getMouse() {
        return ClientUtil.getMouse();
    }

    public static double getMouseX() {
        return getMouse().getX();
    }

    public static double getMouseY() {
        return getMouse().getY();
    }

    public static boolean isCursorLocked() {
        return getMouse().isCursorLocked();
    }

    public static void lockCursor() {
        getMouse().lockCursor();
    }

    public static void unlockCursor() {
        getMouse().unlockCursor();
    }

    public static void tick() {
        getMouse().tick();
    }

    public static boolean wasLeftButtonClicked() {
        return getMouse().wasLeftButtonClicked();
    }

    public static boolean wasRightButtonClicked() {
        return getMouse().wasRightButtonClicked();
    }

    public static boolean wasMiddleButtonClicked() {
        return getMouse().wasMiddleButtonClicked();
    }
}
