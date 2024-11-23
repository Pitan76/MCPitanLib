package net.pitan76.mcpitanlib.midohra.server;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.MessageType;
import net.minecraft.server.BannedIpList;
import net.minecraft.server.BannedPlayerList;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.Whitelist;
import net.pitan76.mcpitanlib.api.entity.Player;
import net.pitan76.mcpitanlib.api.text.TextComponent;
import net.pitan76.mcpitanlib.api.util.TextUtil;
import net.pitan76.mcpitanlib.midohra.world.ServerWorld;
import net.pitan76.mcpitanlib.midohra.world.World;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PlayerManager {
    private final net.minecraft.server.PlayerManager playerManager;
    
    protected PlayerManager(net.minecraft.server.PlayerManager playerManager) {
        this.playerManager = playerManager;
    }
    
    public static PlayerManager of(net.minecraft.server.PlayerManager playerManager) {
        return new PlayerManager(playerManager);
    }

    public static PlayerManager of(MinecraftServer server) {
        return of(server.getPlayerManager());
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
    
    public net.minecraft.server.PlayerManager getRaw() {
        return playerManager;
    }
    
    public net.minecraft.server.PlayerManager toMinecraft() {
        return getRaw();
    }

    public void broadcast(TextComponent message, boolean overlay) {
        if (overlay)
            getRaw().broadcastChatMessage(message.getText(), MessageType.SYSTEM, UUID.randomUUID());
        else
            getRaw().broadcastChatMessage(message.getText(), MessageType.CHAT, UUID.randomUUID());
    }

    public void broadcast(TextComponent message) {
        broadcast(message, false);
    }

    public void broadcast(String message, boolean overlay) {
        if (overlay)
            getRaw().broadcastChatMessage(TextUtil.literal(message), MessageType.SYSTEM, UUID.randomUUID());
        else
            getRaw().broadcastChatMessage(TextUtil.literal(message), MessageType.CHAT, UUID.randomUUID());
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
        return new Player(getRaw().getPlayer(name));
    }
    
    public List<Player> getPlayersByIP(String ip) {
        List<Player> players = new ArrayList<>();
        for (net.minecraft.entity.player.PlayerEntity p: getRaw().getPlayersByIp(ip)) {
            players.add(new Player(p));
        }
        return players;
    }
    
    public List<Player> getPlayers() {
        List<Player> players = new ArrayList<>();
        for (PlayerEntity p : getRaw().getPlayerList()) {
            players.add(new Player(p));
        }
        return players;
    }
    
    public boolean isExistByUUID(UUID uuid) {
        return getRaw().getPlayer(uuid) != null;
    }

    public boolean isExistByName(String name) {
        return getRaw().getPlayer(name) != null;
    }

    public boolean isExistByIP(String ip) {
        return !getRaw().getPlayersByIp(ip).isEmpty();
    }

    public boolean hasPlayerByUUID(UUID uuid) {
        return getRaw().getPlayer(uuid) != null;
    }

    public boolean hasPlayerByName(String name) {
        return getRaw().getPlayer(name) != null;
    }

    public boolean hasPlayerByIP(String ip) {
        return !getRaw().getPlayersByIp(ip).isEmpty();
    }
    
    public int getCurrentPlayerCount() {
        return getRaw().getCurrentPlayerCount();
    }
    
    public int getMaxPlayerCount() {
        return getRaw().getMaxPlayerCount();
    }
    
    public boolean isWhitelistEnabled() {
        return getRaw().isWhitelistEnabled();
    }

    public void setWhitelistEnabled(boolean enabled) {
        getRaw().setWhitelistEnabled(enabled);
    }

    public Whitelist getWhitelist() {
        return getRaw().getWhitelist();
    }

    public void reloadWhitelist() {
        getRaw().reloadWhitelist();
    }
    
    public BannedIpList getBannedIpList() {
        return getRaw().getIpBanList();
    }
    
    public BannedPlayerList getBannedPlayerList() {
        return getRaw().getUserBanList();
    }
}
