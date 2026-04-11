package net.pitan76.mcpitanlib.midohra.server;

import net.minecraft.server.players.IpBanList;
import net.minecraft.server.players.UserBanList;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.players.UserWhiteList;
import net.pitan76.mcpitanlib.api.entity.Player;
import net.pitan76.mcpitanlib.api.text.TextComponent;
import net.pitan76.mcpitanlib.api.util.TextUtil;
import net.pitan76.mcpitanlib.midohra.world.ServerWorld;
import net.pitan76.mcpitanlib.midohra.world.World;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PlayerManager {
    private final net.minecraft.server.players.PlayerList playerManager;
    
    protected PlayerManager(net.minecraft.server.players.PlayerList playerManager) {
        this.playerManager = playerManager;
    }
    
    public static PlayerManager of(net.minecraft.server.players.PlayerList playerManager) {
        return new PlayerManager(playerManager);
    }

    public static PlayerManager of(MinecraftServer server) {
        return of(server.getPlayerList());
    }

    public static PlayerManager of(MCServer server) {
        return of(server.getRaw());
    }

    public static PlayerManager of(ServerWorld world) {
        return of(world.getServer());
    }

    public static PlayerManager of(World world) {
        return of(world.getServer());
    }
    
    public net.minecraft.server.players.PlayerList getRaw() {
        return playerManager;
    }
    
    public net.minecraft.server.players.PlayerList toMinecraft() {
        return getRaw();
    }

    public void broadcast(TextComponent message, boolean overlay) {
        getRaw().broadcastSystemMessage(message.getText(), overlay);
    }

    public void broadcast(TextComponent message) {
        broadcast(message, false);
    }

    public void broadcast(String message, boolean overlay) {
        getRaw().broadcastSystemMessage(TextUtil.literal(message), overlay);
    }

    public void broadcast(String message) {
        broadcast(message, false);
    }

    public void removePlayer(Player player) {
        getRaw().remove(player.getServerPlayer().get());
    }
    
    public Player getPlayerByUUID(UUID uuid) {
        return new Player(getRaw().getPlayer(uuid));
    }
    
    public Player getPlayerByName(String name) {
        return new Player(getRaw().getPlayerByName(name));
    }
    
    public List<Player> getPlayersByIP(String ip) {
        List<Player> players = new ArrayList<>();
        for (net.minecraft.world.entity.player.Player p: getRaw().getPlayersWithAddress(ip)) {
            players.add(new Player(p));
        }
        return players;
    }
    
    public List<Player> getPlayers() {
        List<Player> players = new ArrayList<>();
        for (net.minecraft.world.entity.player.Player p : getRaw().getPlayers()) {
            players.add(new Player(p));
        }
        return players;
    }
    
    public boolean isExistByUUID(UUID uuid) {
        return getRaw().getPlayer(uuid) != null;
    }

    public boolean isExistByName(String name) {
        return getRaw().getPlayerByName(name) != null;
    }

    public boolean isExistByIP(String ip) {
        return !getRaw().getPlayersWithAddress(ip).isEmpty();
    }

    public boolean hasPlayerByUUID(UUID uuid) {
        return getRaw().getPlayer(uuid) != null;
    }

    public boolean hasPlayerByName(String name) {
        return getRaw().getPlayerByName(name) != null;
    }

    public boolean hasPlayerByIP(String ip) {
        return !getRaw().getPlayersWithAddress(ip).isEmpty();
    }
    
    public int getCurrentPlayerCount() {
        return getRaw().getPlayerCount();
    }
    
    public int getMaxPlayerCount() {
        return getRaw().getMaxPlayers();
    }
    
    public boolean isWhitelistEnabled() {
        return getRaw().isUsingWhitelist();
    }

    public void setWhitelistEnabled(boolean enabled) {
        getRaw().getServer().setUsingWhitelist(enabled);
    }

    public UserWhiteList getWhitelist() {
        return getRaw().getWhiteList();
    }

    public void reloadWhitelist() {
        getRaw().reloadWhiteList();
    }
    
    public IpBanList getBannedIpList() {
        return getRaw().getIpBans();
    }
    
    public UserBanList getBannedPlayerList() {
        return getRaw().getBans();
    }
}
