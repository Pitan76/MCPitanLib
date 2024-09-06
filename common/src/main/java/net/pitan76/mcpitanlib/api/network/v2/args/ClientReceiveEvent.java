package net.pitan76.mcpitanlib.api.network.v2.args;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.network.PacketByteBuf;
import net.pitan76.mcpitanlib.api.entity.Player;

public class ClientReceiveEvent {
    public MinecraftClient client;
    public ClientPlayerEntity clientPlayer;
    public Player player;
    public PacketByteBuf buf;

    public ClientReceiveEvent(MinecraftClient client, ClientPlayerEntity player, PacketByteBuf buf) {
        this.client = client;
        this.clientPlayer = player;
        this.player = new Player(player);
        this.buf = buf;
    }

    public ClientPlayerEntity getClientPlayer() {
        return clientPlayer;
    }

    public Player getPlayer() {
        return player;
    }

    public MinecraftClient getClient() {
        return client;
    }

    public PacketByteBuf getBuf() {
        return buf;
    }
}
