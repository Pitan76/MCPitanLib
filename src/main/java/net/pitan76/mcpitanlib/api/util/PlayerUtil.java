package net.pitan76.mcpitanlib.api.util;

import net.minecraft.server.MinecraftServer;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.pitan76.mcpitanlib.api.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PlayerUtil {

    public static Player getPlayerByUUID(MinecraftServer server, UUID uuid) {
        return new Player(server.getPlayerList().getPlayer(uuid));
    }

    public static Player getPlayerByName(MinecraftServer server, String name) {
        return new Player(server.getPlayerList().getPlayerByName(name));
    }

    public static List<Player> getPlayersByIP(MinecraftServer server, String ip) {
        List<Player> players = new ArrayList<>();
        for (net.minecraft.world.entity.player.Player p: server.getPlayerList().getPlayersWithAddress(ip)) {
            players.add(new Player(p));
        }
        return players;
    }

    public static List<Player> getPlayers(MinecraftServer server) {
        List<Player> players = new ArrayList<>();
        for (net.minecraft.world.entity.player.Player p : server.getPlayerList().getPlayers()) {
            players.add(new Player(p));
        }
        return players;
    }

    public static Player getPlayerByUUID(Level world, UUID uuid) {
        return getPlayerByUUID(world.getServer(), uuid);
    }

    public static Player getPlayerByName(Level world, String name) {
        return getPlayerByName(world.getServer(), name);
    }

    public static boolean isExistByUUID(MinecraftServer server, UUID uuid) {
        return server.getPlayerList().getPlayer(uuid) != null;
    }

    public static boolean isExistByUUID(Level world, UUID uuid) {
        return isExistByUUID(world.getServer(), uuid);
    }

    public static boolean isExistByName(MinecraftServer server, String name) {
        return server.getPlayerList().getPlayerByName(name) != null;
    }

    public static boolean isExistByName(Level world, String name) {
        return isExistByName(world.getServer(), name);
    }

    public static boolean isExistByIP(MinecraftServer server, String ip) {
        return !server.getPlayerList().getPlayersWithAddress(ip).isEmpty();
    }

    public static boolean isExistByIP(Level world, String ip) {
        return isExistByIP(world.getServer(), ip);
    }
    
    public static void sendMessage(Player player, String message) {
        player.sendMessage(TextUtil.literal(message));
    }

    public static float getYaw(Player player) {
        return player.getYaw();
    }

    public static float getPitch(Player player) {
        return player.getPitch();
    }

    public static BlockPos getBlockPos(Player player) {
        return player.getBlockPos();
    }

    public static Level getWorld(Player player) {
        return player.getWorld();
    }

    public static boolean isClient(Player player) {
        return player.isClient();
    }

    public static void teleport(Player player, double x, double y, double z) {
        player.teleport(x, y, z);
    }
}
