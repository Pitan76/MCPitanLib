package net.pitan76.mcpitanlib.api.util.client.v2;

import net.minecraft.client.gui.Element;
import net.minecraft.client.gui.screen.Screen;

public class ScreenUtil extends net.pitan76.mcpitanlib.api.util.client.ScreenUtil {

    public static void setFocus(Screen screen, boolean focus) {
        screen.setFocused(focus);
    }

    public static void setFocus(Screen screen, Element element) {
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
}
