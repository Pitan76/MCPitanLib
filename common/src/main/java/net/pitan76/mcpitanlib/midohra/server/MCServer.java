package net.pitan76.mcpitanlib.midohra.server;

import net.minecraft.server.MinecraftServer;
import net.pitan76.mcpitanlib.api.util.CompatIdentifier;
import net.pitan76.mcpitanlib.api.util.PersistentStateUtil;
import net.pitan76.mcpitanlib.api.util.ServerUtil;
import net.pitan76.mcpitanlib.api.util.WorldUtil;
import net.pitan76.mcpitanlib.midohra.resource.ResourceManager;
import net.pitan76.mcpitanlib.midohra.world.PersistentStateManager;
import net.pitan76.mcpitanlib.midohra.world.ServerWorld;

public class MCServer {
    private final MinecraftServer server;

    protected MCServer(MinecraftServer server) {
        this.server = server;
    }

    public static MCServer of(MinecraftServer server) {
        return new MCServer(server);
    }

    public MinecraftServer getRaw() {
        return server;
    }

    public MinecraftServer toMinecraft() {
        return getRaw();
    }

    public String getIP() {
        return ServerUtil.getIP(server);
    }

    public int getPort() {
        return ServerUtil.getPort(server);
    }

    public String getMotd() {
        return ServerUtil.getMotd(server);
    }

    public String getServerModName() {
        return ServerUtil.getServerModName(server);
    }

    public int getMaxPlayerCount() {
        return ServerUtil.getMaxPlayerCount(server);
    }

    public int getCurrentPlayerCount() {
        return ServerUtil.getCurrentPlayerCount(server);
    }

    public boolean isOnlineMode() {
        return ServerUtil.isOnlineMode(server);
    }

    public boolean isServerRunning() {
        return ServerUtil.isServerRunning(server);
    }

    public boolean isServerDedicated() {
        return ServerUtil.isServerDedicated(server);
    }

    public boolean isSingleplayer() {
        return ServerUtil.isSingleplayer(server);
    }

    public PlayerManager getPlayerManager() {
        return PlayerManager.of(ServerUtil.getPlayerManager(server));
    }

    public ServerWorld getWorld(CompatIdentifier id) {
        return ServerWorld.of(WorldUtil.getWorld(server, id));
    }

    public ServerWorld getOverworld() {
        return ServerWorld.of(WorldUtil.getOverworld(server));
    }

    public ServerWorld getNether() {
        return ServerWorld.of(WorldUtil.getNether(server));
    }

    public ServerWorld getEnd() {
        return ServerWorld.of(WorldUtil.getEnd(server));
    }

    public ResourceManager getResourceManager() {
        return ResourceManager.of(server.getResourceManager());
    }

    public void execute(Runnable runnable) {
        ServerUtil.execute(getRaw(), runnable);
    }

    public PersistentStateManager getPersistentStateManager() {
        return PersistentStateManager.of(PersistentStateUtil.getManagerFromServer(server));
    }
}
