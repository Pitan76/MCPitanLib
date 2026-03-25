package net.pitan76.mcpitanlib.api.util.client;

import com.mojang.blaze3d.platform.Window;

public class WindowUtil {

    public static Window getWindow() {
        return ClientUtil.getWindow();
    }

    public static int getWindowScaledWidth() {
        return getWindow().getGuiScaledWidth();
    }

    public static int getWindowScaledHeight() {
        return getWindow().getGuiScaledHeight();
    }

    public static int getWindowWidth() {
        return getWindow().getScreenWidth();
    }

    public static int getWindowHeight() {
        return getWindow().getScreenHeight();
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
