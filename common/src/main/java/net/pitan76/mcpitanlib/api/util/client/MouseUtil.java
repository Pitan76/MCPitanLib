package net.pitan76.mcpitanlib.api.util.client;

import net.minecraft.client.MouseHandler;

public class MouseUtil {

    public static MouseHandler getMouse() {
        return ClientUtil.getMouse();
    }

    public static double getMouseX() {
        return getMouse().xpos();
    }

    public static double getMouseY() {
        return getMouse().ypos();
    }

    public static boolean isCursorLocked() {
        return getMouse().isMouseGrabbed();
    }

    public static void lockCursor() {
        getMouse().grabMouse();
    }

    public static void unlockCursor() {
        getMouse().releaseMouse();
    }

    public static void tick() {
        getMouse().handleAccumulatedMovement();
    }

    public static boolean wasLeftButtonClicked() {
        return getMouse().isLeftPressed();
    }

    public static boolean wasRightButtonClicked() {
        return getMouse().isRightPressed();
    }

    public static boolean wasMiddleButtonClicked() {
        return getMouse().isMiddlePressed();
    }
}
