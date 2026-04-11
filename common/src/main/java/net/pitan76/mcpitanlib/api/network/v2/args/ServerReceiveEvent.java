package net.pitan76.mcpitanlib.api.network.v2.args;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.Level;
import net.pitan76.mcpitanlib.api.entity.Player;
import net.pitan76.mcpitanlib.midohra.network.CompatPacketByteBuf;
import net.pitan76.mcpitanlib.midohra.server.MCServer;

public class ServerReceiveEvent {
    public MinecraftServer server;
    public ServerPlayer serverPlayer;
    public Player player;
    public FriendlyByteBuf buf;

    public ServerReceiveEvent(MinecraftServer server, ServerPlayer player, FriendlyByteBuf buf) {
        this.server = server;
        this.serverPlayer = player;
        this.player = new Player(player);
        this.buf = buf;
    }

    public ServerPlayer getServerPlayer() {
        return serverPlayer;
    }

    public Player getPlayer() {
        return player;
    }

    public MinecraftServer getServer() {
        return server;
    }

    public FriendlyByteBuf getBuf() {
        return buf;
    }

    public Level getWorld() {
        return getPlayer().getWorld();
    }

    public net.pitan76.mcpitanlib.midohra.world.World getMidohraWorld() {
        return net.pitan76.mcpitanlib.midohra.world.World.of(getWorld());
    }

    public MCServer getMidohraServer() {
        return MCServer.of(server);
    }

    public CompatPacketByteBuf getCompatBuf() {
        return CompatPacketByteBuf.of(buf);
    }

    public void execute(Runnable runnable) {
        server.execute(runnable);
    }
}
