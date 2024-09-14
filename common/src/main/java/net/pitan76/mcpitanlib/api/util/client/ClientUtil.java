package net.pitan76.mcpitanlib.api.util.client;

import com.mojang.authlib.GameProfile;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.Mouse;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.texture.TextureManager;
import net.minecraft.client.util.Window;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.profiler.Profiler;
import net.pitan76.mcpitanlib.api.entity.Player;

import java.io.File;
import java.util.Optional;

public class ClientUtil {
    public static void setScreen(Screen screen) {
        getClient().setScreen(screen);
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

    public static Optional<Long> getTime() {
        if (getClient().world == null) return Optional.empty();
        return Optional.of(getClient().world.getTime());
    }

    public static long getRenderTime() {
        return (long) getClient().getTickDelta();
    }

    public static HitResult getTarget() {
        return getClient().crosshairTarget;
    }

    public static WorldRenderer getWorldRenderer() {
        return getClient().worldRenderer;
    }

    public static File getRunDirectory() {
        return getClient().runDirectory;
    }

    public static Profiler getProfiler() {
        return getClient().getProfiler();
    }

    public static GameProfile getGameProfile() {
        if (getClient().player == null) return null;
        return getClient().player.getGameProfile();
    }

    public static Window getWindow() {
        return getClient().getWindow();
    }

    public static Mouse getMouse() {
        return getClient().mouse;
    }
}
