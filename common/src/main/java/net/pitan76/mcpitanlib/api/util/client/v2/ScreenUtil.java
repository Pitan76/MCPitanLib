package net.pitan76.mcpitanlib.api.util.client.v2;

import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.client.gui.screens.Screen;

public class ScreenUtil extends net.pitan76.mcpitanlib.api.util.client.ScreenUtil {

    public static void setFocus(Screen screen, boolean focus) {
        screen.setFocused(focus);
    }

    public static void setFocus(Screen screen, GuiEventListener element) {
        screen.setFocused(element);
    }

    public static boolean isFocused(Screen screen) {
        return screen.isFocused();
    }

    public static void setDragging(Screen screen, boolean dragging) {
        screen.setDragging(dragging);
    }

    public static boolean isDragging(Screen screen) {
        return screen.isDragging();
    }

    public static int getWidth(Screen screen) {
        return screen.width;
    }

    public static int getHeight(Screen screen) {
        return screen.height;
    }
}
