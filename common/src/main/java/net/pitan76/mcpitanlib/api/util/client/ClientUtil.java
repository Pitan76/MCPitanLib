package net.pitan76.mcpitanlib.api.util.client;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;

public class ClientUtil {
    public static void setScreen(Screen screen) {
        MinecraftClient.getInstance().setScreen(screen);
    }

    public static Screen getScreen() {
        return MinecraftClient.getInstance().currentScreen;
    }
}
