package net.pitan76.mcpitanlib.api.network.fabric;

import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import net.pitan76.mcpitanlib.api.network.ServerNetworking;

public class ServerNetworkingImpl {
    public static void send(ServerPlayerEntity player, Identifier identifier, PacketByteBuf buf) {
        ServerPlayNetworking.send(player, identifier, buf);
    }

    public static void registerReceiver(Identifier identifier, ServerNetworking.ServerNetworkHandler handler) {
        ServerPlayNetworking.registerGlobalReceiver(identifier, (server, player, handler1, buf, responseSender) -> {
            PacketByteBuf newBuf = PacketByteBufs.copy(buf);
            server.execute(() -> handler.receive(server, player, newBuf));
        });
    }
}
