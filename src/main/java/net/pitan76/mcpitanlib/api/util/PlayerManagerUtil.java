package net.pitan76.mcpitanlib.api.util;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.PlayerManager;
import net.minecraft.world.World;
import net.pitan76.mcpitanlib.api.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PlayerManagerUtil {

    /**
     * Get player by UUID
     * @param server MinecraftServer
     * @param uuid UUID
     * @return Player
     */
    public static Player getPlayerByUUID(MinecraftServer server, UUID uuid) {
        return PlayerUtil.getPlayerByUUID(server, uuid);
    }

    /**
     * Get player by name
     * @param server MinecraftServer
     * @param name String
     * @return Player
     */
    public static Player getPlayerByName(MinecraftServer server, String name) {
        return PlayerUtil.getPlayerByName(server, name);
    }

    /**
     * Get players by IP
     * @param server MinecraftServer
     * @param ip String
     * @return List<Player>
     */
    public static List<Player> getPlayersByIP(MinecraftServer server, String ip) {
        return PlayerUtil.getPlayersByIP(server, ip);
    }

    /**
     * Get players
     * @param server MinecraftServer
     * @return List<Player>
     */
    public static List<Player> getPlayers(MinecraftServer server) {
        return PlayerUtil.getPlayers(server);
    }

    /**
     * Get players
     * @param world World
     * @return List<Player>
     */
    public static List<Player> getPlayers(World world) {
        List<Player> players = new ArrayList<>();
        for (PlayerEntity p : world.getPlayers()) {
            players.add(new Player(p));
        }
        return players;
    }

    /**
     * Get player by UUID
     * @param world World
     * @param uuid UUID
     * @return Player
     */
    public static Player getPlayerByUUID(World world, UUID uuid) {
        return PlayerUtil.getPlayerByUUID(world, uuid);
    }

    /**
     * Get player by name
     * @param world World
     * @param name String
     * @return Player
     */
    public static Player getPlayerByName(World world, String name) {
        return PlayerUtil.getPlayerByName(world, name);
    }

    public static PlayerManager getPlayerManager(MinecraftServer server) {
        return ServerUtil.getPlayerManager(server);
    }

    public static PlayerManager getPlayerManager(World world) {
        return getPlayerManager(world.getServer());
    }

    public static boolean hasPlayerByUUID(PlayerManager playerManager, UUID uuid) {
        return playerManager.getPlayer(uuid) != null;
    }

    public static boolean hasPlayerByName(PlayerManager playerManager, String name) {
        return playerManager.getPlayer(name) != null;
    }

    public static boolean hasPlayerByIP(PlayerManager playerManager, String ip) {
        return !playerManager.getPlayersByIp(ip).isEmpty();
    }

    public static boolean hasPlayerByUUID(MinecraftServer server, UUID uuid) {
        return hasPlayerByUUID(getPlayerManager(server), uuid);
    }

    public static boolean hasPlayerByName(MinecraftServer server, String name) {
        return hasPlayerByName(getPlayerManager(server), name);
    }

    public static boolean hasPlayerByIP(MinecraftServer server, String ip) {
        return hasPlayerByIP(getPlayerManager(server), ip);
    }

    public static boolean hasPlayerByUUID(World world, UUID uuid) {
        return hasPlayerByUUID(getPlayerManager(world), uuid);
    }

    public static boolean hasPlayerByName(World world, String name) {
        return hasPlayerByName(getPlayerManager(world), name);
    }

    public static boolean hasPlayerByIP(World world, String ip) {
        return hasPlayerByIP(getPlayerManager(world), ip);
    }
}
