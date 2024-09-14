package net.pitan76.mcpitanlib.api.util.client;

import net.minecraft.client.util.Window;

public class WindowUtil {

    public static Window getWindow() {
        return ClientUtil.getWindow();
    }

    public static int getWindowScaledWidth() {
        return getWindow().getScaledWidth();
    }

    public static int getWindowScaledHeight() {
        return getWindow().getScaledHeight();
    }

    public static int getWindowWidth() {
        return getWindow().getWidth();
    }

    public static int getWindowHeight() {
        return getWindow().getHeight();
    }

    public static int getWindowX() {
        return getWindow().getX();
    }

    public static int getWindowY() {
        return getWindow().getY();
    }

    public static void setTitle(String title) {
        getWindow().setTitle(title);
    }

    public static void close() {
        getWindow().close();
    }
}
