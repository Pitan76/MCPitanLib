package net.pitan76.mcpitanlib.api.network.v2.args;

import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.level.Level;
import net.pitan76.mcpitanlib.api.entity.Player;
import net.pitan76.mcpitanlib.midohra.network.CompatPacketByteBuf;

public class ClientReceiveEvent {
    public Minecraft client;
    public LocalPlayer clientPlayer;
    public Player player;
    public FriendlyByteBuf buf;

    public ClientReceiveEvent(Minecraft client, LocalPlayer player, FriendlyByteBuf buf) {
        this.client = client;
        this.clientPlayer = player;
        this.player = new Player(player);
        this.buf = buf;
    }

    public LocalPlayer getClientPlayer() {
        return clientPlayer;
    }

    public Player getPlayer() {
        return player;
    }

    public Minecraft getClient() {
        return client;
    }

    public FriendlyByteBuf getBuf() {
        return buf;
    }

    public CompatPacketByteBuf getCompatBuf() {
        return CompatPacketByteBuf.of(buf);
    }

    public Level getWorld() {
        return getPlayer().getWorld();
    }

    public net.pitan76.mcpitanlib.midohra.world.World getMidohraWorld() {
        return net.pitan76.mcpitanlib.midohra.world.World.of(getWorld());
    }
}
