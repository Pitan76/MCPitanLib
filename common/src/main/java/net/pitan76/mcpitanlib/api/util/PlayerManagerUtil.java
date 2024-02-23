package net.pitan76.mcpitanlib.api.util;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.MinecraftServer;
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
}
