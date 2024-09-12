package net.pitan76.mcpitanlib.api.util.client;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.texture.TextureManager;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.resource.ResourceManager;
import net.pitan76.mcpitanlib.api.entity.Player;

public class ClientUtil {
    public static void setScreen(Screen screen) {
        getClient().openScreen(screen);
    }

    public static Screen getScreen() {
        return getClient().currentScreen;
    }

    public static Player getPlayer() {
        return new Player(getClientPlayer());
    }

    public static ClientPlayerEntity getClientPlayer() {
        return getClient().player;
    }

    public static MinecraftClient getClient() {
        return MinecraftClient.getInstance();
    }

    public static TextRenderer getTextRenderer() {
        return getClient().textRenderer;
    }

    public static ItemRenderer getItemRenderer() {
        return getClient().getItemRenderer();
    }

    public static ResourceManager getResourceManager() {
        return getClient().getResourceManager();
    }

    public static TextureManager getTextureManager() {
        return getClient().getTextureManager();
    }

    public static ClientWorld getWorld() {
        return getClient().world;
    }

    public static GameRenderer getGameRenderer() {
        return getClient().gameRenderer;
    }
}
