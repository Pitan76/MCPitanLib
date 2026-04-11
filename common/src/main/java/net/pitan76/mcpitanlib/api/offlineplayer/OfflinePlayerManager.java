package net.pitan76.mcpitanlib.api.offlineplayer;

import net.pitan76.easyapi.FileControl;
import net.pitan76.easyapi.config.JsonConfig;
import net.pitan76.mcpitanlib.api.entity.Player;
import net.pitan76.mcpitanlib.api.util.PlatformUtil;
import net.pitan76.mcpitanlib.midohra.server.PlayerManager;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class OfflinePlayerManager {
    public static OfflinePlayerManager INSTANCE = new OfflinePlayerManager();
    public static final File DEFAULT_FILE = new File(PlatformUtil.getConfigFolderAsFile(), "mcpitanlib/offlineplayer.json");

    private final List<OfflinePlayer> offlinePlayers = new ArrayList<>();

    protected OfflinePlayerManager() {
        if (INSTANCE == null)
            INSTANCE = this;
        load();
    }

    public File getFile() {
        return DEFAULT_FILE;
    }

    public void load() {
        if (FileControl.fileExists(getFile())) {
            JsonConfig config = new JsonConfig(getFile());
            config.configMap.forEach((key, value) -> offlinePlayers.add(new OfflinePlayer(key, (String) value)));
        }
    }

    public void save() {
        if (!FileControl.fileExists(getFile().getParentFile())) {
            getFile().getParentFile().mkdirs();
        }
        JsonConfig config = new JsonConfig();
        offlinePlayers.forEach(offlinePlayer -> config.set(offlinePlayer.uuid, offlinePlayer.name));
        config.save(getFile(), true);
    }

    public OfflinePlayer getPlayer(String uuid) {
        for (OfflinePlayer offlinePlayer : offlinePlayers) {
            if (offlinePlayer.uuid.equals(uuid)) {
                return offlinePlayer;
            }
        }
        return null;
    }

    public OfflinePlayer getPlayerByName(String name) {
        for (OfflinePlayer offlinePlayer : offlinePlayers) {
            if (offlinePlayer.name.equals(name)) {
                return offlinePlayer;
            }
        }
        return null;
    }

    public void addPlayer(OfflinePlayer offlinePlayer) {
        offlinePlayers.add(offlinePlayer);
    }

    public void removePlayer(OfflinePlayer offlinePlayer) {
        offlinePlayers.remove(offlinePlayer);
    }

    public void addPlayer(String uuid, String name) {
        if (containsPlayerByUUID(uuid))
            return;
        addPlayer(new OfflinePlayer(uuid, name));
    }

    public void removePlayer(String uuid) {
        removePlayer(getPlayer(uuid));
    }

    public boolean containsPlayerByUUID(String uuid) {
        return getPlayer(uuid) != null;
    }

    public boolean containsPlayerByName(String name) {
        return getPlayerByName(name) != null;
    }

    public boolean containsPlayer(OfflinePlayer offlinePlayer) {
        return offlinePlayers.contains(offlinePlayer);
    }

    public boolean containsPlayer(UUID uuid) {
        return containsPlayerByUUID(uuid.toString());
    }

    public static Optional<Player> getOnlinePlayer(UUID uuid, PlayerManager playerManager) {
        if (playerManager.hasPlayerByUUID(uuid))
            return Optional.of(playerManager.getPlayerByUUID(uuid));

        return Optional.empty();
    }

    public static Optional<Player> getOnlinePlayerByName(String name, PlayerManager playerManager) {
        if (playerManager.hasPlayerByName(name))
            return Optional.of(playerManager.getPlayerByName(name));

        return Optional.empty();
    }

    public static Optional<Player> getOnlinePlayerByUUID(String uuid, PlayerManager playerManager) {
        return getOnlinePlayer(UUID.fromString(uuid), playerManager);
    }

    public static Optional<Player> getOnlinePlayer(OfflinePlayer offlinePlayer, PlayerManager playerManager) {
        return getOnlinePlayer(UUID.fromString(offlinePlayer.uuid), playerManager);
    }

    public UUID getUUID(String name) {
        return UUID.fromString(getUUIDasString(name));
    }

    public String getName(UUID uuid) {
        for (OfflinePlayer offlinePlayer : offlinePlayers) {
            if (offlinePlayer.uuid.equals(uuid.toString())) {
                return offlinePlayer.name;
            }
        }
        return null;
    }

    public String getUUIDasString(String name) {
        for (OfflinePlayer offlinePlayer : offlinePlayers) {
            if (offlinePlayer.name.equals(name)) {
                return offlinePlayer.uuid;
            }
        }
        return null;
    }

    public List<OfflinePlayer> getOfflinePlayers() {
        return offlinePlayers;
    }

    public void clear() {
        offlinePlayers.clear();
    }

    public void reload() {
        clear();
        load();
    }

    public void saveAndReload() {
        save();
        reload();
    }

    public void saveAndReloadAsync() {
        new Thread(() -> {
            save();
            reload();
        }).start();
    }

    public void saveAsync() {
        new Thread(this::save).start();
    }

    public void reloadAsync() {
        new Thread(this::reload).start();
    }

    public void clearAsync() {
        new Thread(this::clear).start();
    }

    public void saveAndReloadAsync(Runnable runnable) {
        new Thread(() -> {
            save();
            reload();
            runnable.run();
        }).start();
    }

    public void saveAsync(Runnable runnable) {
        new Thread(() -> {
            save();
            runnable.run();
        }).start();
    }

    public void reloadAsync(Runnable runnable) {
        new Thread(() -> {
            reload();
            runnable.run();
        }).start();
    }

    public void clearAsync(Runnable runnable) {
        new Thread(() -> {
            clear();
            runnable.run();
        }).start();
    }

    public void saveAndReload(Runnable runnable) {
        save();
        reload();
        runnable.run();
    }

    public void save(Runnable runnable) {
        save();
        runnable.run();
    }

    public void reload(Runnable runnable) {
        reload();
        runnable.run();
    }

    public void clear(Runnable runnable) {
        clear();
        runnable.run();
    }
}
