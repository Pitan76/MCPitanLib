package net.pitan76.mcpitanlib.api.network;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;

public class ServerNetworking {
    @ExpectPlatform
    public static void send(ServerPlayerEntity player, Identifier identifier, PacketByteBuf buf) {
        throw new AssertionError();
    }

    public static void send(Iterable<ServerPlayerEntity> players, Identifier identifier, PacketByteBuf buf) {
        for (ServerPlayerEntity player : players) {
            send(player, identifier, buf);
        }
    }

    public static void sendAll(MinecraftServer server, Identifier identifier, PacketByteBuf buf) {
        send(server.getPlayerManager().getPlayerList(), identifier, buf);
    }

    @ExpectPlatform
    public static void registerReceiver(Identifier identifier, ServerNetworkHandler handler) {
        throw new AssertionError();
    }

    @FunctionalInterface
    public interface ServerNetworkHandler {
        void receive(MinecraftServer server, ServerPlayerEntity player, PacketByteBuf buf);
    }

    public static void registerS2CPayloadType(Identifier identifier) {
        // ignore
    }
}
