package net.pitan76.mcpitanlib.api.network;

import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import net.pitan76.mcpitanlib.core.network.BufPayload;
import net.pitan76.mcpitanlib.core.network.ServerPlayNetworking;

public class ServerNetworking {
    public static void send(ServerPlayerEntity player, Identifier identifier, PacketByteBuf buf) {
        registerS2CPayloadType(identifier);

        BufPayload payload = new BufPayload(buf, identifier);
        ServerPlayNetworking.send(player, payload);
    }

    public static void send(Iterable<ServerPlayerEntity> players, Identifier identifier, PacketByteBuf buf) {
        registerS2CPayloadType(identifier);

        BufPayload payload = new BufPayload(buf, identifier);
        for (ServerPlayerEntity player : players) {
            ServerPlayNetworking.send(player, payload);
        }
    }

    public static void sendAll(MinecraftServer server, Identifier identifier, PacketByteBuf buf) {
        send(server.getPlayerManager().getPlayerList(), identifier, buf);
    }

    public static void registerReceiver(Identifier identifier, ServerNetworkHandler handler) {
        BufPayload.Id<BufPayload> id = BufPayload.id(identifier);
        ServerPlayNetworking.registerGlobalReceiver(id, handler);
    }

    public static void registerS2CPayloadType(Identifier identifier) {
        ServerPlayNetworking.registerS2CPayloadType(identifier);
    }

    @FunctionalInterface
    public interface ServerNetworkHandler {
        void receive(MinecraftServer server, ServerPlayerEntity player, PacketByteBuf buf);
    }
}
