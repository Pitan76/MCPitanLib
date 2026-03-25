package net.pitan76.mcpitanlib.api.util;

import net.minecraft.server.MinecraftServer;
import net.minecraft.server.players.PlayerList;
import net.minecraft.world.level.Level;
import net.pitan76.mcpitanlib.api.entity.Player;

public class ServerUtil {
    public static MinecraftServer getServer(Level world) {
        return world.getServer();
    }

    public static String getIP(MinecraftServer server) {
        return server.getLocalIp();
    }

    public static int getPort(MinecraftServer server) {
        return server.getPort();
    }

    public static String getMotd(MinecraftServer server) {
        return server.getMotd();
    }

    public static String getServerModName(MinecraftServer server) {
        return server.getServerModName();
    }

    public static int getMaxPlayerCount(MinecraftServer server) {
        return server.getMaxPlayers();
    }

    public static int getCurrentPlayerCount(MinecraftServer server) {
        return server.getPlayerCount();
    }

    public static boolean isOnlineMode(MinecraftServer server) {
        return server.usesAuthentication();
    }

    public static boolean isServerRunning(MinecraftServer server) {
        return server.isRunning();
    }

    public static boolean isServerDedicated(MinecraftServer server) {
        return server.isDedicatedServer();
    }

    public static boolean isSingleplayer(MinecraftServer server) {
        return server.isSingleplayer();
    }

    public static PlayerList getPlayerManager(MinecraftServer server) {
        return server.getPlayerList();
    }

    public static MinecraftServer getServer(Player player) {
        return getServer(player.getWorld());
    }

    public static void execute(MinecraftServer server, Runnable runnable) {
        server.execute(runnable);
    }
}
