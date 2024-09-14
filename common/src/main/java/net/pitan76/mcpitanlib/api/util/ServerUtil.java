package net.pitan76.mcpitanlib.api.util;

import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;

public class ServerUtil {
    public static MinecraftServer getServer(World world) {
        return world.getServer();
    }

    public static String getIP(MinecraftServer server) {
        return server.getServerIp();
    }

    public static int getPort(MinecraftServer server) {
        return server.getServerPort();
    }

    public static String getMotd(MinecraftServer server) {
        return server.getServerMotd();
    }

    public static String getServerModName(MinecraftServer server) {
        return server.getServerModName();
    }

    public static int getMaxPlayerCount(MinecraftServer server) {
        return server.getMaxPlayerCount();
    }

    public static int getCurrentPlayerCount(MinecraftServer server) {
        return server.getCurrentPlayerCount();
    }

    public static boolean isOnlineMode(MinecraftServer server) {
        return server.isOnlineMode();
    }

    public static boolean isServerRunning(MinecraftServer server) {
        return server.isRunning();
    }

    public static boolean isServerDedicated(MinecraftServer server) {
        return server.isDedicated();
    }

    public static boolean isSingleplayer(MinecraftServer server) {
        return server.isSingleplayer();
    }
}
