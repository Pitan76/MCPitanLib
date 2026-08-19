package net.pitan76.mcpitanlib.api.network.fabric;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.Identifier;
import net.pitan76.mcpitanlib.api.network.ClientNetworking;

public class ClientNetworkingImpl {
    public static void send(Identifier identifier, PacketByteBuf buf) {
        ClientPlayNetworking.send(identifier, buf);
    }

    public static void registerReceiver(Identifier identifier, ClientNetworking.ClientNetworkHandler handler) {
        ClientPlayNetworking.registerGlobalReceiver(identifier, (client, handler1, buf, responseSender) -> {
            PacketByteBuf newBuf = PacketByteBufs.copy(buf);
            client.execute(() -> handler.receive(client, client.player, newBuf));
        });
    }
}
