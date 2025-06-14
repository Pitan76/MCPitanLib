package net.pitan76.mcpitanlib.api.network.v2.args;

import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.world.World;
import net.pitan76.mcpitanlib.api.entity.Player;
import net.pitan76.mcpitanlib.midohra.network.CompatPacketByteBuf;
import net.pitan76.mcpitanlib.midohra.server.MCServer;

public class ServerReceiveEvent {
    public MinecraftServer server;
    public ServerPlayerEntity serverPlayer;
    public Player player;
    public PacketByteBuf buf;

    public ServerReceiveEvent(MinecraftServer server, ServerPlayerEntity player, PacketByteBuf buf) {
        this.server = server;
        this.serverPlayer = player;
        this.player = new Player(player);
        this.buf = buf;
    }

    public ServerPlayerEntity getServerPlayer() {
        return serverPlayer;
    }

    public Player getPlayer() {
        return player;
    }

    public MinecraftServer getServer() {
        return server;
    }

    public PacketByteBuf getBuf() {
        return buf;
    }

    public World getWorld() {
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
