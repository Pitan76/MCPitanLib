package net.pitan76.mcpitanlib.api.offlineplayer;

import net.pitan76.mcpitanlib.api.entity.Player;
import net.pitan76.mcpitanlib.midohra.server.PlayerManager;

import java.util.Optional;
import java.util.UUID;

public class OfflinePlayer {
    public String uuid;
    public String name;

    public OfflinePlayer(String uuid, String name) {
        this.uuid = uuid;
        this.name = name;
    }

    public String getUUIDasString() {
        return uuid;
    }

    public String getName() {
        return name;
    }

    public UUID getUUID() {
        return UUID.fromString(uuid);
    }

    public Optional<Player> getOnlinePlayer(PlayerManager playerManager) {
        return OfflinePlayerManager.getOnlinePlayer(this, playerManager);
    }

    public boolean isOnline(PlayerManager playerManager) {
        return playerManager.hasPlayerByUUID(getUUID());
    }
}
