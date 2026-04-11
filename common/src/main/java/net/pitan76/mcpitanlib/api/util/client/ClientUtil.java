package net.pitan76.mcpitanlib.api.util.client;

import com.mojang.authlib.GameProfile;
import net.minecraft.client.Minecraft;
import net.minecraft.client.MouseHandler;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.client.renderer.item.ItemModelResolver;
import net.minecraft.client.renderer.texture.TextureManager;
import com.mojang.blaze3d.platform.Window;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.world.phys.HitResult;
import net.minecraft.util.profiling.ProfilerFiller;
import net.pitan76.mcpitanlib.api.client.option.GameOptionsWrapper;
import net.pitan76.mcpitanlib.api.entity.Player;

import java.io.File;
import java.util.Optional;

public class ClientUtil {
    public static void setScreen(Screen screen) {
        getClient().setScreen(screen);
    }

    public static Screen getScreen() {
        return getClient().screen;
    }

    public static Player getPlayer() {
        return new Player(getClientPlayer());
    }

    public static LocalPlayer getClientPlayer() {
        return getClient().player;
    }

    public static Minecraft getClient() {
        return Minecraft.getInstance();
    }

    public static Font getTextRenderer() {
        return getClient().font;
    }

    public static ItemModelResolver getItemRenderer() {
        return getClient().getItemModelResolver();
    }

    public static ResourceManager getResourceManager() {
        return getClient().getResourceManager();
    }

    public static TextureManager getTextureManager() {
        return getClient().getTextureManager();
    }

    public static ClientLevel getWorld() {
        return getClient().level;
    }

    public static GameRenderer getGameRenderer() {
        return getClient().gameRenderer;
    }

    public static Optional<Long> getTime() {
        if (getClient().level == null) return Optional.empty();
        return Optional.of(getClient().level.getGameTime());
    }

    public static long getRenderTime() {
        return getClient().getFrameTimeNs();
    }

    public static HitResult getTarget() {
        return getClient().hitResult;
    }

    public static LevelRenderer getWorldRenderer() {
        return getClient().levelRenderer;
    }

    public static File getRunDirectory() {
        return getClient().gameDirectory;
    }

    public static ProfilerFiller getProfiler() {
        return null;
    }

    public static GameProfile getGameProfile() {
        return getClient().getGameProfile();
    }

    public static Window getWindow() {
        return getClient().getWindow();
    }

    public static MouseHandler getMouse() {
        return getClient().mouseHandler;
    }

    public static boolean isInSingleplayer() {
        return getClient().isLocalServer();
    }

    public static boolean isPaused() {
        return getClient().isPaused();
    }

    public static GameOptionsWrapper getOptions() {
        return new GameOptionsWrapper(getClient().options);
    }
}
